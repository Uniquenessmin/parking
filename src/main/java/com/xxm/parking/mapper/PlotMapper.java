package com.xxm.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xxm.parking.pojo.Plot;

@Mapper
public interface PlotMapper {
	//查询停车场列表 可按条件查询
	@Select("select * from parkings ${wheresql}")
	public List<Plot> getPlotList(@Param("wheresql")String wheresql);
	
	//根据停车场id查看停车场的信息
	@Select("select * from parkings where id = #{id}")
	public Plot getPlot();
	
	//查看地图
	@Select("select map from parkings where id = #{id}")
	public String imgStr(String id);
	
	
}
