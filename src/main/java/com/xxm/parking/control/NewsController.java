package com.xxm.parking.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxm.parking.pojo.News;
import com.xxm.parking.service.NewsService;

@Controller
@ResponseBody
public class NewsController {
	@Autowired
	NewsService newsService;
	
	@GetMapping("/newsList")
	public List<News> getNewsList(){
		return newsService.getNewsList();
	}
	
	@GetMapping("/newsInfo/{id}")
	public News getNews(@PathVariable("id")int id){
		return newsService.getNewsById(id);
	}

}
