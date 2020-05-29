package com.xxm.parking.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xxm.parking.pojo.Seat;

/**
 * 泊位
 * @author cp407
 *
 */
@Mapper
public interface SeatMapper {

	//查看停车场泊位
	@Select("select * from seat where parkingid = #{parkingid}")
	public List<Seat> getSeatList(@Param("parkingid")int parkingid);
	
	//根据名字和停车场id获取泊位id
	@Select("select id from seat where name = #{name} and parkingid = #{parkingid}")
	public int getSeatId(@Param("name")String name,@Param("parkingid")int parkingid);
	
	//根据id获取泊位信息
	@Select("select * from seat where id = #{id}")
	public Seat getSeat(@Param("id")int id);

	//查看停车场可用泊位
	@Select("select name from seat where parkingid = #{plotId} and status = 1")
	public List<String> getAvailableSeatList(@Param("plotId")int plotId);

	//修改泊位状态
	@Update("update seat set status=#{status} where id = #{seatid}")
	public boolean changeSeatStatus(@Param("seatid")int seatid,@Param("status")int status);
}
