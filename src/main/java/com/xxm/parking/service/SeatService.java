package com.xxm.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.SeatMapper;
import com.xxm.parking.pojo.Seat;

@Service
public class SeatService {
	@Autowired
	SeatMapper seatMapper;

	/**
	 * 
	 * 根据泊位名与停车场id获取泊位id
	 * 
	 * @param name
	 * @param plotId
	 * @return
	 */
	public int getSeatId(String name,int plotId) {
		int seatId = seatMapper.getSeatId(name, plotId);
		return seatId;
	}

	public Seat getSeat(int seatid) {
		
		return seatMapper.getSeat(seatid);
	}

	/**
	 * 可用泊位列表
	 * @param plotId
	 * @return
	 */
	public List<String> getAvailableSeats(int plotId) {
		
		return seatMapper.getAvailableSeatList(plotId);
	}

	/**
	 * 修改泊位状态
	 * @param seatid
	 * @return
	 */
	public boolean changeSeatStatus(int seatid,int status) {
		
		return seatMapper.changeSeatStatus(seatid,status);
	}
	/**
	 * 车位列表
	 * @param plotId
	 * @return
	 */
	public List<Seat> getSeatList(int plotId) {
		// TODO Auto-generated method stub
		return seatMapper.getSeatList(plotId);
	}

	public List<Seat> getSeatList2() {
		// TODO Auto-generated method stub
		return seatMapper.getSeatList2();
	}
}
