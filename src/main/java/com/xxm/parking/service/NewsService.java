package com.xxm.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.NewsMapper;
import com.xxm.parking.pojo.News;

@Service
public class NewsService {

	@Autowired
	NewsMapper newsMapper;
	
	public List<News> getNewsList(){
		return newsMapper.getNews();
	}
	
	public News getNewsById(int id) {
		return newsMapper.getNewById(id);
	}
}
