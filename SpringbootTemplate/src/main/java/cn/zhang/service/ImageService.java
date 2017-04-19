package cn.zhang.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhang.model.Image;


public interface ImageService
{
	public String uploadFile(HttpServletRequest req,HttpServletResponse res);
	public Map uploadImage(HttpServletRequest req,HttpServletResponse res);
	
	boolean saveImage(Image image);
	Image getImageByName(String name);
	Map downloadImg(String name);
	Map downloadImgByByte(String name,HttpServletRequest req);
	Map downloadImgByByte(String name,HttpServletRequest req,HttpServletResponse res);
}
