package com.xxm.parking.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xxm.parking.pojo.BSeat;

public interface BSeatMapper {

	@Select("select count(1) from bookseat where userid=#{userid}")
	int getBookCount(@Param("userid")int userid);

	@Insert("insert bookseat(userid,seatid,booktime) values(#{userid},#{seatid},#{booktime})")
	boolean bookSeat(@Param("userid")int userid, 
			
			@Param("seatid")int seatid, 
			@Param("booktime")long booktime);

	@Select("select * from bookseat where userid=#{userid}")
	BSeat getBook(@Param("userid")int userid);

}
