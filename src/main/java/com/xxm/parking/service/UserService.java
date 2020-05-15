package com.xxm.parking.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.UserMapper;
import com.xxm.parking.pojo.User;
import com.xxm.parking.util.KeyUtil;

/**
 * 
 * @author 许晓敏xxm
 * 
 * 用户业务层
 *
 */
@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 注册新用户
	 * @param u 用户信息
	 * @return map 操作信息
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public Map<String, Object> createUser(User u) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userMapper.find(u.getTel());
		User u2 = userMapper.findByNum(u.getPlatenumber());
		if (user != null) {
			map.put("state", 2);
			map.put("msg", "该账号已存在！");
		} else if (u2 != null) {
			map.put("state", 2);
			map.put("msg", "该车牌号已绑定！");
		} else {

			// 密码加密
			u.setPasswd(KeyUtil.encodeByMd5(u.getPasswd()));

			boolean isOk = userMapper.create(u);
			if (isOk) {
				map.put("state", 1);
				map.put("msg", "注册成功！");
				map.put("user", u);

			} else {
				map.put("state", 2);
				map.put("msg", "注册失败！");
			}
		}
		return map;
	}

	/**
	 * 验证用户信息
	 * @param u
	 * @return
	 */
	public Map<String,Object> checkUser(String tel,String passwd)  {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(tel);
		User user = null;
		user = userMapper.find(tel);
		if(user == null) {
			map.put("state", 2);
			map.put("msg","该账号还未注册") ;
		}else {
			//验证密码
			if(KeyUtil.checkpassword(passwd, user.getPasswd())) {
				map.put("state", 1);
				map.put("msg","验证成功") ;
				map.put("user", user);
			}else {
				map.put("state", 2);
				map.put("msg","密码错误") ;
			}
		}
		return map;	
	}	

	/**
	 * 修改用户信息
	 * @param u
	 * @return
	 */
	public Map<String, Object> changeUserInfo(User u) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tel = userMapper.findById(u.getId()).getTel();
		if (!tel.equals( u.getTel()) && null != userMapper.find(u.getTel())) {
			// 电话修改,检查是否已经存在
			// 已经存在
			map.put("state", 2);
			map.put("msg", "修改失败，该电话已用于另一个账户");
		}else {
			userMapper.change(u);
			map.put("state", 1);
			map.put("msg", "修改成功");
		}
		return map;
	}

	/**
	 * 密码修改
	 * @param passwd
	 * @return
	 */
	public Map<String, Object> changPassword(HashMap<String,String> message){
		Map<String, Object> map = new HashMap<String,Object>();
		String id = message.get("id");
		String oldpasswd = message.get("oldpasswd");
		String newpasswd = message.get("newpasswd");
		String passwd = userMapper.getpwd(id);
		if(passwd.equals(oldpasswd)) {
			//匹配成功，更换密码
			userMapper.changepwd(id, KeyUtil.encodeByMd5(newpasswd));
			map.put("state",1);
			map.put("msg", "密码修改成功");
			
		}else {
			map.put("state",2);
			map.put("msg", "原密码错误");
		}
		return map;
	}
	
	/**
	 * 根据id获取当前用户信息
	 * @param id
	 * @return
	 */
	public User getUser(int id) {
		User u = userMapper.findById(id);
		return u;
	}
}
