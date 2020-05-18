package com.xxm.parking.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.xxm.parking.pojo.Plot;

/**
 * 泊位
 * @author cp407
 *
 */
@Mapper
public interface SeatMapper {

	//查看停车场泊位
	@Select("select * from seat where parkingid = #{parkingid}")
	public List<Plot> getSeatList(String parkingid);
	
}
