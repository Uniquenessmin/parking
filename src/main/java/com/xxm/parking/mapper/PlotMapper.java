package com.xxm.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xxm.parking.pojo.Plot;

@Mapper
public interface PlotMapper {
	// 查询停车场列表 可按条件查询
	@Select("select * from parkings ${wheresql}")
	public List<Plot> getPlotList(@Param("wheresql") String wheresql);

	// 根据停车场id查看停车场的信息
	@Select("select * from parkings where id = #{id}")
	public Plot getPlot(@Param("id") int id);

	// 查看地图
	@Select("select map from parkings where id = #{id}")
	public String imgStr(String id);

	// 修改剩余泊位
	@Update("update parkings set leftparkinglot=leftparkinglot+#{i} where id=#{id}  ")
	public boolean changeLeftSeats(@Param("id")int plotid, @Param("i")int i);
	// 查看剩余泊位
	@Select("select leftparkinglot from parkings where id = #{id}")
	public int getLeftSeats(@Param("id") int id);

	//删除
	@Delete("delete from parkings where id = #{id}")
	public boolean delete(@Param("id")int id);

}
