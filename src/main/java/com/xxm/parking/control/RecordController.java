package com.xxm.parking.control;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.Plot;
import com.xxm.parking.pojo.Record;
import com.xxm.parking.pojo.Seat;
import com.xxm.parking.service.PlotService;
import com.xxm.parking.service.RecordService;
import com.xxm.parking.service.SeatService;
import com.xxm.parking.util.TimeUtil;

@Controller
@ResponseBody
public class RecordController {

	@Autowired
	RecordService recordService;
	@Autowired
	SeatService seatService;
	@Autowired
	PlotService plotService;

	/**
	 * 
	 * @param inMap
	 * @return
	 */
	@PostMapping("/start")
	public Map<String, Object> beginParking(@RequestBody HashMap<String, Object> inMap) {
		Map<String, Object> map = recordService.startParking(inMap);
		return map;
	}

	/**
	 * 当前用户正在停车
	 * 
	 * @param userid
	 * @return
	 */
	@GetMapping("/{userid}/currentRecord")
	public Map<String, Object> getNowRecord(@PathVariable int userid) {
		Record r = recordService.nowRecord(userid);
		Map<String, Object> map = new HashMap<>();

		// 已经停留时间
		long nowtime = System.currentTimeMillis();
		String pTime = TimeUtil.dateDiff(r.getCreatetime(), nowtime, 1);
		map.put("parkTime", pTime);
		// 停车场名字与泊位名
		if (r.getSeatid() != 0) {
			Seat seat = seatService.getSeat(r.getSeatid());
			Plot plot = plotService.getPlot(seat.getParkingid());
			r.setPlot(plot);
			r.setSeat(seat);
			// 临时计算车费
			double m = plot.getCharge();

			int freeTime = plot.getFreetime();
			int minute = Integer.parseInt(TimeUtil.dateDiff(r.getCreatetime(), nowtime, 3));
			int time = (minute - freeTime) > 0 ? minute - freeTime : 0;

			double tempMoney = m * time / 60.0;
			double get_double = Double.parseDouble(String.format("%.2f", tempMoney));

			map.put("tempMoney", get_double);
		}
		map.put("record", r);
		return map;
	}

	/**
	 * 获取出场按钮次数
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/buttonCount")
	public int getButtonNum(@RequestParam("recordid") int id) {
		return recordService.getButtonNum(id);
	}
	
	
	/**
	 * 出场请求处理，开门
	 * @param userid
	 * @return
	 */
	@PostMapping("/{userid}/end")
	public boolean openForEnd(@PathVariable("userid")int userid) {
		//修改停车记录，endtime，button
		long endtime = System.currentTimeMillis();
		return recordService.changeEnd(userid,endtime,-1);
	}
}
