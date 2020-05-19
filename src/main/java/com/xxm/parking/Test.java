package com.xxm.parking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xxm.parking.control.UserController;
import com.xxm.parking.mapper.UserMapper;
import com.xxm.parking.pojo.User;
import com.xxm.parking.service.UserService;

public class Test {

	public static void main(String[] args) {
//		login();
		
	}
	public static  void   login() {
		HashMap<String,String> map = new HashMap<>();
		String tel = "13217489796";
		String passwd = "4QrcOUm6Wau+VuBX8g+IPg==";
		
		map.put("tel", "13217489796");
		map.put("passwd", "4QrcOUm6Wau+VuBX8g+IPg==");
		
		
		UserService us = new UserService();
		UserController uc = new UserController();
		Map<String, Object> m = us.checkUser(tel, passwd);
		
		System.out.println(m.toString());
		
	}
	
	public static  void   changepwd() {
		HashMap<String,String> map = new HashMap<>();
		String id = "3";
		String oldpasswd = "123456";
		String newpasswd = "123456";
		
		map.put("id", id);
		map.put("oldpasswd", oldpasswd);
		map.put("newpasswd", newpasswd);
		
		
		UserService us = new UserService();
		UserController uc = new UserController();
		Map<String, Object> m = uc.changePasswd(map);
		
		System.out.println(m.toString());
		
	}
}
