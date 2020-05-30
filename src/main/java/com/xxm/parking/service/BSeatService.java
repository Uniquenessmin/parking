package com.xxm.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.BSeatMapper;
import com.xxm.parking.pojo.BSeat;

@Service
public class BSeatService {

	@Autowired
	BSeatMapper bsMapper;
	public int getBookCount(int userid) {
		// TODO Auto-generated method stub
		return bsMapper.getBookCount(userid) ;
	}
	public boolean bookSeat(int userid,int seatid, long booktime) {
		// TODO Auto-generated method stub
		return bsMapper.bookSeat(userid,seatid,booktime);
	}
	
	public BSeat getBook(int userid) {
		
		return bsMapper.getBook(userid);
	}

}
