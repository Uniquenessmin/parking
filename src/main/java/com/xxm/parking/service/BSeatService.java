package com.xxm.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.BSeatMapper;
import com.xxm.parking.pojo.BSeat;
import com.xxm.parking.pojo.Seat;

@Service
public class BSeatService {

	@Autowired
	BSeatMapper bsMapper;
	@Autowired
	SeatService seatService;
	@Autowired
	PlotService plotService;

	public int getBookCount(int userid) {
		// TODO Auto-generated method stub
		return bsMapper.getBookCount(userid);
	}

	public boolean bookSeat(int userid, int seatid, long booktime) {
		// TODO Auto-generated method stub
		return bsMapper.bookSeat(userid, seatid, booktime);
	}

	public BSeat getBook(int userid) {

		return bsMapper.getBook(userid);
	}

	public boolean cancel(int bseatid) {
		// 泊位变化，车场变化
		
		BSeat s = bsMapper.getBookById(bseatid);
		Seat seat = seatService.getSeat(s.getSeatid());
		boolean f = bsMapper.cancel(bseatid);
		// 修改泊位状态与车场剩余泊位数
		boolean isOk2 = seatService.changeSeatStatus(seat.getId(), 1);
		boolean isOk3 = plotService.changeLeftSeats(seat.getParkingid(), 1);
		return f && isOk2 && isOk3;
	}

}
