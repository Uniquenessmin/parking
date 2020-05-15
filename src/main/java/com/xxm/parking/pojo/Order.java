package com.xxm.parking.pojo;

import java.math.BigDecimal;
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
}
