package com.xxm.parking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

import com.xxm.parking.mapper.PlotMapper;
import com.xxm.parking.mapper.UserMapper;
import com.xxm.parking.pojo.Plot;

@Service
public class PlotService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PlotMapper plotMapper;
	
	
	public List<Plot> getPlotList( HashMap<String,String> map){
		String name = map.get("name");
		String address = map.get("address");
		String left = map.get("left");
		//wheresql拼接
		StringBuffer sb = new StringBuffer(" where ");
		int p = 0;
		if(!StringUtils.isEmpty(name)) {
			sb.append("name = " + name);
			p++;
		}
		if(!StringUtils.isEmpty(address)) {
			if(p!=0) {
				sb.append(" and ");
			}
			sb.append(" address = " + address);
			p++;
		}
		if(!StringUtils.isEmpty(left)) {
			if(p!=0) {
				sb.append(" and ");
			}
			sb.append("left = " + left);
			p++;
		}
		String str = " ;";
		if(!sb.toString().equals(" where ")) {
			str = sb.toString();
		}
		
		List<Plot> list = plotMapper.getPlotList(str);
		
		return list;
		
	}
}
