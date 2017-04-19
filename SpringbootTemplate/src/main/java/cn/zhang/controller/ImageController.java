package cn.zhang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zhang.service.ImageService;

/**
 * 文件长传下载
 * @author zcm
 *
 */
@RestController
@RequestMapping("/imageController")
public class ImageController {
	@Resource
	public  ImageService service;
	@RequestMapping(method=RequestMethod.POST,value="uploadImage")
	public Map<String,Object> uploadImage(HttpServletRequest req,HttpServletResponse res){
		return service.uploadImage(req,res);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="uploadFile")
	public Map<String,Object> uploadFile(HttpServletRequest req,HttpServletResponse res){
		
		Map<String,Object> map=new HashMap<String,Object>();
		service.uploadFile(req,res);
		return map;
	}
	@RequestMapping(method=RequestMethod.POST,value="downloadImg")
	public Map<String,Object> downloadImg(@RequestParam("name")String name){
		
		Map<String,Object> map=new HashMap<String,Object>();
		return service.downloadImg(name);

	}
	
	@RequestMapping(method=RequestMethod.POST,value="downloadImgByByte")
	public Map<String,Object> downloadImgByByte(@RequestParam("name")String name,HttpServletRequest req){
		
		Map<String,Object> map=new HashMap<String,Object>();
		return service.downloadImgByByte(name,req);

	}
	@RequestMapping(method=RequestMethod.GET,value="downloadImgByByte3")
	public Map<String,Object> downloadImgByByte3(@RequestParam("name")String name,HttpServletRequest req,HttpServletResponse res ){
		
		Map<String,Object> map=new HashMap<String,Object>();
		return service.downloadImgByByte(name,req,res);

	}
}
