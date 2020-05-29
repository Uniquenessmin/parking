package com.xxm.parking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static String dateDiff(long startTime, long endTime, int f) {

		long nd =  1000*24 * 60 * 60;// 一天的hao秒数
		long nh =  1000*60 * 60;// 一小时的hao秒数
		long nm =  1000*60;// 一分钟的hao秒
		
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		diff = endTime - startTime;
		day = diff / nd;// 计算差多少天
		hour = diff % nd / nh + day * 24;// 计算差多少小时
		min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
		//sec = diff % nd % nh % nm ;// 计算差多少秒
		// 输出结果
		StringBuffer sb = new StringBuffer();

		sb.append(day + "天").append((hour - day * 24) + "小时").append((min - day * 24 * 60) + "分钟");
		// f=1
		if(f==1) {
			return sb.toString();
		}
		else if(f==2) {
			return String.valueOf(diff/nh);
		}else {
			return String.valueOf(diff/nm);
		}
		
	}
	
	public static long dateToTime(String time1) {
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		
		 Date date = null;
		try {
			date = format.parse(time1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 //日期转时间戳（毫秒）
		 long time=date.getTime();
		 return time;
		
	}
	
	public static String timeToDate(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date(time);
		//日期格式化
		String  res = simpleDateFormat.format(date);
		return res;
	}
	
	
//	public static void main(String[] args) {
//		long time1 = 1590631847;
//		long time2 = System.currentTimeMillis();
//		String s = dateDiff(time1*1000, time2, 1);
//		System.out.println(s);
//	}
}
