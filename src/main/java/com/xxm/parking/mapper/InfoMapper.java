package com.xxm.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxm.parking.pojo.Info;


public interface InfoMapper {

	@Select("select * from info")
	List<Info> getInfos();

	@Select("select * from info where id=#{id}")
	Info getInfoById(int id);

}
