package com.xxm.parking.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.Info;
import com.xxm.parking.service.InfoService;

@Controller
@ResponseBody
public class InfoController {
	@Autowired
	InfoService infoService;
	
	@GetMapping("/infoList")
	public List<Info> getInfoList(){
		return infoService.getInfoList();
	}
	
	@GetMapping("/info/{id}")
	public Info getInfo(@PathVariable("id")int id){
		return infoService.getInfoById(id);
	}

}
