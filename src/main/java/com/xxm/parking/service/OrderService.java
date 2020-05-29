package com.xxm.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.OrderMapper;
import com.xxm.parking.pojo.Order;

@Service
public class OrderService {
	@Autowired
	OrderMapper orderMapper;
	
	public Order createOrder(Order order) {
		boolean isOk = orderMapper.createOrder(order);
		Order o  = null;
		if(isOk) {
			 o = orderMapper.getNowOrder(order.getUserid());
		}
		
		return o;
	}

	/**
	 * 正在进行的订单数量
	 * @param userid
	 * @return
	 */
	public int getNowOrderCount(int userid) {
		int num = orderMapper.getNowOrderCount(userid);
		
		return num;
	}

	/**
	 *  正在进行的订单
	 * @param userid
	 * @return
	 */
	public Order getNowOrder(int userid) {
		// TODO Auto-generated method stub
		return orderMapper.getNowOrder(userid);
	}

	/**
	 * 设置结束时间
	 * @param id
	 * @return
	 */
	public boolean changeEndtime(double fee,long endtime,int id) {
		
		return orderMapper.setEndTime(fee, endtime, id);
	}

	/**
	 * 设置订单的支付方式与状态
	 * @param userid
	 * @param i
	 * @param paytype
	 * @return
	 */
	public boolean updateOrder(int orderid, int status, int paytype) {
		return orderMapper.updateOrder(orderid,status,paytype);
	}

	public List<Order> getOrdersByUser(int userid) {
		return orderMapper.getOrderByUserid(userid);
	}
}
