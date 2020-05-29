package com.xxm.parking.pojo;

import com.xxm.parking.util.TimeUtil;

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
	private User user;
	private Seat seat;
	private Plot plot;
	private int button;
	private String stayTime;//停留时长
	
	public String getStayTime() {
		this.stayTime = TimeUtil.dateDiff(createtime, endtime, 1);
		return this.stayTime;
	}
}
