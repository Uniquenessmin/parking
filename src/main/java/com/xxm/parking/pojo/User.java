package com.xxm.parking.pojo;

import lombok.Data;

/**
 * 
 * 用户
 * 
 * @author 许晓敏
 *
 */
@Data
public class User {

	private int id;
	private String passwd;
	private String tel;
	private String name;
	private String platenumber;
	private int identity;
		
}
