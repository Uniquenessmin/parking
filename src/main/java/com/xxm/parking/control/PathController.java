package com.xxm.parking.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

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
}
