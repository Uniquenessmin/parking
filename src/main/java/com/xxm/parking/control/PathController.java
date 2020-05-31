package com.xxm.parking.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xxm.parking.pojo.Record;
import com.xxm.parking.service.BSeatService;
import com.xxm.parking.service.OrderService;
import com.xxm.parking.service.RecordService;

@Controller
public class PathController {

	@Autowired
	OrderService orderService;
	@Autowired
	RecordService recordService;
	@Autowired
	BSeatService bseatService;

	@RequestMapping("/main")
	public String firstPage() {
		return "login";
	}

	@RequestMapping("/foruser")
	public String userPage() {
		return "foruser";
	}

	@RequestMapping("/manager")
	public String managerPage() {
		return "manager";
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/myinfo")
	public String myInfo() {
		return "myInfo";
	}

	@RequestMapping("/parking.html")
	public String toParkingService() {
		return "parking";
	}

	@RequestMapping("/parking/current/{id}")
	public String toParkingCurrent(@PathVariable("id") String id) {
		return "current";
	}

	@RequestMapping("/parking/future/{id}")
	public String toParkingFuture(@PathVariable("id") String id) {
		return "future";
	}

	// 某停车场的泊位列表
	@RequestMapping("/parking/current/{id}/seatList")
	public String toSeat(@PathVariable("id") String id) {
		return "seat";
	}

	// 查看正在停车的记录
	@RequestMapping("/record")
	public String toRecord(@RequestParam("userid") int id) {
		System.out.println("toRecord...userid:" + id);
		Record r = recordService.nowRecord(id);

		if (r.getSeatid() == 0) {
			return "seat";
		} else {
			return "record";
		}

	}

	// 离场界面
	@RequestMapping("/leave")
	public String leavePlot() {
		return "leave";
	}

	// 支付页面
	@RequestMapping("/{id}/payOrder")
	public String toPay(@PathVariable("id") String id) {
		return "pay";
	}

	// 出场页面
	@RequestMapping("/out")
	public String toButton() {
		return "outButton";
	}

	// 完成页面
	@RequestMapping("/finish")
	public String finish() {
		return "finish";
	}

	// 我的订单页面
	@RequestMapping("/myOrder")
	public String myOrder() {
		return "myOrder";
	}

	// 我的停车记录页面
	@RequestMapping("/myRecord")
	public String myRecord() {
		return "myRecordList";
	}

	// 我的页面
	@RequestMapping("/my")
	public String my() {
		return "my";
	}

	// 设置页面
	@RequestMapping("/settings")
	public String settings() {
		return "settings";
	}

	// 资讯页面
	@RequestMapping("/news")
	public String news() {
		return "news";
	}

	// 资讯详情页面
	@RequestMapping("/news/{id}")
	public String newsInfo(@PathVariable("id") int id) {
		return "newsInfo";
	}

	// 指南页面
	@RequestMapping("/info")
	public String toInfo() {
		return "parkstudy";
	}

	// 使用教程页面
	@RequestMapping("/how.html")
	public String infoMessage() {
		return "how.html";
	}

	//预约信息
	@RequestMapping("/{id}/book")
	public String bookMessage() {
		return "bookinfo";
	}
	
	//预约--进场
	@RequestMapping("/bookIn")
	public String bookIN() {
		return "bookin";
	}
	
	//取消预约
	@RequestMapping("/cancelBook/{id}")
	public String cancelBook(@PathVariable("id")int bseatid) {
		System.out.println(bseatid);
		boolean f = bseatService.cancel(bseatid);
		System.out.println(f);
		if(f)
			return "foruser";
		else
			return "bookinfo";
	}
	
	
}
