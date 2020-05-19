package com.xxm.parking.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.User;
import com.xxm.parking.service.UserService;

@Controller
@ResponseBody
public class UserController {
	
	@Autowired
	UserService userService;	
	
	//登录,验证身份跳转
	@PostMapping("/login")
	public Map<String,Object> login(@RequestBody Map<String,String> user){
		String tel =  user.get("tel");
		String passwd = user.get("passwd");
		System.out.println("tel:"+tel);
		System.out.println("passwd:"+passwd);
		Map<String,Object> map = userService.checkUser(tel,passwd);
		return map;
	}
	
	//注册
	@PostMapping("/register")
	public Map<String,Object> signUp(@RequestBody User user){
		Map<String,Object> map = userService.createUser(user);
		return map;
	}
	
	//修改密码
	@PostMapping("/changpwd")
	public Map<String, Object> changePasswd(@RequestBody HashMap<String,String> reqMap){
		Map<String, Object> state = userService.changPassword(reqMap);
		return state;
	}
	
	//修改个人信息
	@PostMapping("/changeinfo")
	public Map<String, Object> changeInfo(@RequestBody User u){
		Map<String, Object> map = userService.changeUserInfo(u);
		return map;
	}
	
	//查看个人信息
	@GetMapping("/myInfo")
	public User getInfo(@RequestBody int id) {
		User u = userService.getUser(id);
		return u;
	}
}
