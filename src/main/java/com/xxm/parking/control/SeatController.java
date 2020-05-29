package com.xxm.parking.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.Record;
import com.xxm.parking.pojo.Seat;
import com.xxm.parking.service.PlotService;
import com.xxm.parking.service.RecordService;
import com.xxm.parking.service.SeatService;

@Controller
@ResponseBody
public class SeatController {
	@Autowired
	SeatService seatService;
	@Autowired
	PlotService plotService;
	@Autowired
	RecordService recordService;
	
	/**
	 * 选择泊位
	 * @param inMap
	 * @return
	 */
	@PostMapping("/chooseSeat")
	public Map<String,Object> beginParking(@RequestBody HashMap<String, Object> inMap){
		Map<String,Object> map = new HashMap<String,Object>();
		int plotid = Integer.parseInt((String)inMap.get("plotid"));
        String seatName= (String) inMap.get("seatName");
        int recordId =(int)inMap.get("recordId");
        int seatid =(int)inMap.get("seatid");
        
        //获取seat 的 id
//		int seatid = seatService.getSeatId(seatName, plotid);
//		System.out.println("seatid:"+seatid+",recordid:"+recordId);
		//填充停车记录和订单记录
		Record r = recordService.updateRecord(seatid, recordId);
		//修改泊位状态与车场剩余泊位数
		boolean isOk = seatService.changeSeatStatus(seatid,2);
		plotService.changeLeftSeats(plotid,-1);
		
		//返回停车记录对象和订单对象
		map.put("record", r);
		return map;
	}
	
	/**
	 * 可用车位列表
	 * @param plotId
	 * @return
	 */
	@PostMapping("/{plotId}/availableSeat")
	public List<String> getAvailableSeats(@PathVariable("plotId")int plotId){
		List<String> seats = new ArrayList<>();
		seats = seatService.getAvailableSeats(plotId);
		return seats;
	}
	
	/**
	 * 车位列表
	 * @param plotId
	 * @return
	 */
	@PostMapping("/{plotId}/seatList")
	public List<Seat> getSeats(@PathVariable("plotId")int plotId){
		List<Seat> seats = new ArrayList<>();
		seats = seatService.getSeatList(plotId);
		return seats;
	}
}
