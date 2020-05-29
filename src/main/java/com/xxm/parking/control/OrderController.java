package com.xxm.parking.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.Order;
import com.xxm.parking.pojo.Plot;
import com.xxm.parking.pojo.Record;
import com.xxm.parking.pojo.Seat;
import com.xxm.parking.service.OrderService;
import com.xxm.parking.service.PlotService;
import com.xxm.parking.service.RecordService;
import com.xxm.parking.service.SeatService;
import com.xxm.parking.util.TimeUtil;

@Controller
@ResponseBody
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	RecordService recordService;
	@Autowired
	SeatService seatService;
	@Autowired
	PlotService plotService;
	
	@PostMapping("/orderCount")
	public int getNowOrderCount(@RequestBody Map<String,Object> map) {
		System.out.println("orderCount..");
		int userid =Integer.parseInt( (String)map.get("userid"));
		return orderService.getNowOrderCount(userid);
	}
	
	@GetMapping("/orderInfo")
	public Map<String,Object> getMyOrder(@RequestParam("userid")int userid,@RequestParam("endtime")long endtime) {
		Order order = orderService.getNowOrder(userid);
		System.out.println("endtime:"+endtime);
		System.out.println("createtime:"+order.getCreatetime());
		//计算产生费用
		Record r = recordService.nowRecord(userid);
		Map<String,Object> map = new HashMap<>();
		double fee=0;
		//停车场名字与泊位名
		if(r.getSeatid()!=0) {
			Seat seat = seatService.getSeat(r.getSeatid());
			Plot plot = plotService.getPlot(seat.getParkingid());
			r.setPlot(plot);
			r.setSeat(seat);
			//计算车费
			double m = plot.getCharge();		
			int freeTime = plot.getFreetime();
			int minute =Integer.parseInt(TimeUtil.dateDiff(order.getCreatetime(), endtime, 3));
			int time = (minute-freeTime)>0?minute-freeTime:0;
		
			double tempMoney = m*time/60.0;
			 fee = Double.parseDouble(String.format("%.2f",tempMoney));

		}
		
		String strTime = TimeUtil.dateDiff(order.getCreatetime(), endtime, 1);
		map.put("parkTime", strTime);
		//修改订单信息
		boolean f = orderService.changeEndtime(fee, endtime, order.getId());
		if(f) {
			order = orderService.getNowOrder(userid);
			//order.setRecord(r);
			map.put("order", order);
			map.put("record", r);
			map.put("state", 1);
		}else {
			map.put("state", 2);
		}
		return map;
	}
	
	/**
	 * 订单支付完成
	 * @param userid
	 * @return
	 */
	@PostMapping("/{userid}/orderFinished")
	public boolean FinishOrder(@PathVariable("userid")int userid,
			@RequestParam("paytype") int paytype,
			@RequestParam("seatid") int seatid,
			@RequestParam("plotid")int plotid,
			@RequestParam("orderid")int orderid
			) {
		System.out.println("finishOrder...");
		//更新订单状态与支付方式
		boolean n1 = orderService.updateOrder(orderid,2,paytype);
		//泊位释放，车场剩余泊位+1
		boolean n2 = seatService.changeSeatStatus(seatid,1);
		boolean n3 = plotService.changeLeftSeats(plotid,1);
		if(n1&&n2&&n3) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/{userid}/orderList")
	public List<Order> getOrderListByUserid(@PathVariable("userid")int userid){
		System.out.println(" getOrderListByUserid...");
		List<Order> list = orderService.getOrdersByUser(userid);
		//中间处理,停车记录，停车场，泊位
		for(Order r:list) {
			//设置停车记录对象
			Record re = recordService.getRecordById(r.getRecordid());
			r.setRecord(re);
			//设置泊位
			Seat se = seatService.getSeat(re.getSeatid());
			r.setSeat(se);
			//设置停车场
			Plot pl = plotService.getPlot(se.getParkingid());
			r.setPlot(pl);			
		}
		return list;
	}
}
