package cn.zhang.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zhang.service.ElasticsearchService;

@RestController
@RequestMapping("/elasticsearchController")
public class ElasticsearchController {
	@Resource
	ElasticsearchService service;
	@RequestMapping(value="insert",method=RequestMethod.GET)
	public void insert(){
		service.getAll();
	}
}
