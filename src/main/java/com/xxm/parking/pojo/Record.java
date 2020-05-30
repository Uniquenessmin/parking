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
	private String stayTime;// 停留时长
	private String date1;
	private String date2;

	public String getStayTime() {
		if(this.endtime!=0) {
			this.stayTime = TimeUtil.dateDiff(createtime, endtime, 1);
		}else {
			this.stayTime = TimeUtil.dateDiff(createtime, System.currentTimeMillis(), 1);
		}
		
		return this.stayTime;
	}

	public String getDate1() {
		return TimeUtil.timeToDate(this.createtime);

	}

	public String getDate2() {
		return TimeUtil.timeToDate(this.endtime);
	}
}
