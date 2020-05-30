package com.xxm.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.InfoMapper;
import com.xxm.parking.mapper.NewsMapper;
import com.xxm.parking.pojo.Info;
import com.xxm.parking.pojo.News;

@Service
public class InfoService {

	@Autowired
	InfoMapper infoMapper;
	
	public List<Info> getInfoList(){
		return infoMapper.getInfos();
	}
	
	public Info getInfoById(int id) {
		return infoMapper.getInfoById(id);
	}
}
