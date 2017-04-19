package cn.zhang.service.dummy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.zhang.mapper.ImageMapper;
import cn.zhang.model.Image;
import cn.zhang.service.ImageService;


@Service("imageService")
public class ImageServiceImpl implements ImageService{
	@Resource
	ImageMapper imageDao;
	@Override
	public String uploadFile(HttpServletRequest req,HttpServletResponse res)  {
		// TODO Auto-generated method stub
		try {
			long startTime=System.currentTimeMillis();
			//spring提供的上传方法
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(req.getSession().getServletContext());
			multipartResolver.setResolveLazily(true);
			//检查form中是否有multipart/form-data
			if(multipartResolver.isMultipart(req)){
				//将request变成多部分的request
				MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) req;
				multiRequest.setCharacterEncoding("UTF-8");
				Enumeration<String> parameters=multiRequest.getParameterNames();
				while(parameters.hasMoreElements()){
					System.out.println(parameters.nextElement());
				}
				//获取multipart中的所有文件名
				Iterator<String> fileNames=multiRequest.getFileNames();
				while(fileNames.hasNext()){
					//一次遍历所有文件
					MultipartFile file=multiRequest.getFile(fileNames.next().toString());
					 String path="E:"+File.separator+"test"+File.separator+file.getOriginalFilename();
					if(file!=null){
						file.transferTo(new File(path));
					}
				}
			}
			long  endTime=System.currentTimeMillis();
			System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * 上传图片使用
	 */
	@Override
	public Map uploadImage(HttpServletRequest req, HttpServletResponse res) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", true);
		try {
			List<String> srcList=new ArrayList<String>();
			long startTime=System.currentTimeMillis();
			//spring提供的上传方法
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(req.getSession().getServletContext());
			multipartResolver.setResolveLazily(true);
			//检查form中是否有multipart/form-data
			if(multipartResolver.isMultipart(req)){
				//将request变成多部分的request
				MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) req;
				String tempImagesDir=multiRequest.getServletContext().getRealPath("/")+"uploadImages";
				File imagesFile=new File(tempImagesDir);	
				if(!imagesFile.exists()){
					imagesFile.mkdirs();
				}
				multiRequest.setCharacterEncoding("UTF-8");
				Enumeration<String> parameters=multiRequest.getParameterNames();
				while(parameters.hasMoreElements()){
					System.out.println("***"+parameters.nextElement());
				}
				//获取multipart中的所有文件名
				Iterator<String> fileNames=multiRequest.getFileNames();
				while(fileNames.hasNext()){
					//一次遍历所有文件
					MultipartFile file=multiRequest.getFile(fileNames.next().toString());
					String name=file.getOriginalFilename();
					byte[] bytes=file.getBytes();
					//写到物理位置
					String src=tempImagesDir+File.separator+name;
					Image imageTemp=getImageByName(name);
					srcList.add("uploadImages"+File.separator+name);
					if(file!=null){
						file.transferTo(new File(src));
						Image image=new Image(name,src,bytes);
						//图片以二进制的形式存入数据库
						if(!saveImage(image)){
							System.out.println("保存失败");
						}
					}
				}
				map.put("srcList", srcList);
			}
			long  endTime=System.currentTimeMillis();
			System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("status", false);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return map;
	}
	public Map downloadImg(String name){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", true);
		try {
			Image image=getImageByName(name);
			map.put("src", image.getSrc());
			
		} catch (Exception e) {
			map.put("status", false);
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return map;
	}
	@Override
	public Map downloadImgByByte(String name, HttpServletRequest req) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", true);
		String tempImagesDirPath=req.getServletContext().getRealPath("/")+"tempImagesDir";
		File tempImagesDir=new File(tempImagesDirPath);	
		if(!tempImagesDir.exists()){
			tempImagesDir.mkdirs();
		}
		try {
			Image image=getImageByName(name);
			//先写到本地文件，然后从数据库中读取路径传给前端
			byte bytes[]=image.getByteContent();
			OutputStream os=new FileOutputStream(new File(tempImagesDirPath+File.separator+name));
			os.write(bytes);
			os.close();
			map.put("tempSrc", "tempImagesDir\\"+name);
		} catch (Exception e) {
			map.put("status", false);
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return map;
	}
	@Override
	public Map downloadImgByByte(String name, HttpServletRequest req,HttpServletResponse res) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", true);
		String tempImagesDirPath=req.getServletContext().getRealPath("/")+"tempImagesDir";
		File tempImagesDir=new File(tempImagesDirPath);	
		if(!tempImagesDir.exists()){
			tempImagesDir.mkdirs();
		}
		try {
			Image image=getImageByName(name);
			//先写到本地文件，然后把路径传给前端
			byte bytes[]=image.getByteContent();
			OutputStream os=new FileOutputStream(new File(tempImagesDirPath+File.separator+name));
			os.write(bytes);
			os.close();
			ServletOutputStream sos=null;
			res.setContentType("image/jpeg;charset=UTF-8");
			res.setHeader("Content-disposition", "attachment;filename="+name);
			sos=res.getOutputStream();
			sos.write(bytes);
			sos.flush();
			sos.close();
			map.put("tempSrc", "tempImagesDir\\"+name);
		} catch (Exception e) {
			map.put("status", false);
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public boolean saveImage(Image image) {
		// TODO Auto-generated method stub
		try {
			imageDao.saveImage(image);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("存错过了"+e.getMessage());
			return false;
		}
	}
	
	@Override
	public Image getImageByName(String name) {
		// TODO Auto-generated method stub
		return imageDao.getImageByName(name);
	}
	
}
