package com.xxm.parking.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.BSeat;
import com.xxm.parking.pojo.Plot;
import com.xxm.parking.pojo.Seat;
import com.xxm.parking.service.BSeatService;
import com.xxm.parking.service.PlotService;
import com.xxm.parking.service.SeatService;

@Controller
@ResponseBody
public class BSeatController {

	@Autowired
	BSeatService bseatService;
	@Autowired
	SeatService seatService;
	@Autowired
	PlotService plotService;

	/**
	 * 获取预约数量
	 * 
	 * @param userid
	 * @return
	 */
	@GetMapping("/{id}/bookCount")
	public int getBookCount(@PathVariable("id") int userid) {
		return bseatService.getBookCount(userid);
	}

	/**
	 * 预定车位
	 * 
	 * @param map
	 * @return
	 */
	@PostMapping("/bookSeat")
	public boolean bookSeat(@RequestBody Map<String, Object> map) {
		int userid = Integer.parseInt((String) map.get("userid"));
		int plotid = Integer.parseInt((String) map.get("plotid"));
		int seatid = (int) map.get("seatid");
		long booktime = (long) map.get("btime");
		boolean isOk1 = bseatService.bookSeat(userid, seatid, booktime);

		// 修改泊位状态与车场剩余泊位数
		boolean isOk2 = seatService.changeSeatStatus(seatid, 2);
		boolean isOk3=plotService.changeLeftSeats(plotid, -1);
		return isOk1 && isOk2 && isOk3;

	}

	/**
	 * 查看预约信息
	 * 
	 * @param userid
	 * @return
	 */
	@GetMapping("/{id}/bookinfo")
	public Map<String, Object> getBook(@PathVariable("id") int userid) {
		BSeat bseat = bseatService.getBook(userid);
		Map<String, Object> map = new HashMap<String, Object>();
		if (bseat != null) {
			Seat seat = seatService.getSeat(bseat.getSeatid());
			bseat.setSeat(seat);
			Plot plot = plotService.getPlot(seat.getParkingid());
			map.put("seat", seat);
			map.put("plot", plot);
			map.put("bseat", bseat);
			map.put("state", 1);
		} else {
			map.put("state", 2);
		}
		return map;
	}

}
