package com.xxm.parking.pojo;

import lombok.Data;

/**
 * 
 * 停车记录
 * 
 * @author 许晓敏
 * 
 */
@Data
public class Record {

	private int id;
	private int seatid;
	private int userid;
	private long createtime;
	private long endtime;
}
