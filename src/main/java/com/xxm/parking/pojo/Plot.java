package com.xxm.parking.pojo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * 停车场
 * 
 * @author 许晓敏
 *
 */
@Data
public class Plot {
	
	private int id;
	private String name;
	private String address;
	private int tel;
	private int totalparkinglot;
	private int leftparkinglot;
	private BigDecimal chargerule;
	private String description;
}
