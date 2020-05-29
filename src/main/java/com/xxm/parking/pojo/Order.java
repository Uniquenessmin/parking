package com.xxm.parking.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.xxm.parking.util.TimeUtil;

import lombok.Data;

/**
 * 
 * 订单
 * 
 * @author 许晓敏
 *
 */
@Data
public class Order {
	private int id;
	private int userid;
	private int recordid;
	private BigDecimal fee;
	private int status;
	private int paytype;
	private long createtime;
	private long endtime;
	private long updatetime;
	private Record record;
	private Plot plot;
	private Seat seat;
	private String date1;
	private String date2;
	
	public String getDate1() {
		return TimeUtil.timeToDate(this.createtime);
		
	}
	
	public String getDate2() {
		return TimeUtil.timeToDate(this.endtime);
		
	}
}
