package com.xxm.parking.pojo;

import com.xxm.parking.util.TimeUtil;

import lombok.Data;

@Data
public class News {
	int id;
	long createtime;
	String title;
	String content;
	String date;
	public String getDate() {
		return TimeUtil.timeToDate(createtime);
	}
}
