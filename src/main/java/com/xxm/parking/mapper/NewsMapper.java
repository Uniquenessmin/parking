package com.xxm.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xxm.parking.pojo.News;

@Mapper
public interface NewsMapper {
	//查询资讯列表
	@Select("select * from news")
	public List<News> getNews();
	
	//按照id获取资讯对象
	@Select("select * from news where id=#{id}")
	public News getNewById(@Param("id")int id);

}
