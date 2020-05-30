package com.xxm.parking.pojo;
import com.xxm.parking.util.TimeUtil;

import lombok.Data;

@Data
public class BSeat {

	private int id;
	private int seatid;
	private int userid;
	private long booktime;
	private String date;
	private Seat seat;
	
	
	public String getDate() {
		return TimeUtil.timeToDate(booktime);
	}
}
