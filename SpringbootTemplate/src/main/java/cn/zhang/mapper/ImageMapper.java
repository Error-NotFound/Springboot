package cn.zhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import cn.zhang.model.IdCard;
import cn.zhang.model.Image;

public interface ImageMapper {
	@Insert(value="insert into image(id,name,byteContent,src,addTime) values(imageSeq.nextval,#{name},#{byteContent},#{src},sysdate)")
	void saveImage(Image image);
	@Results(id = "getImageByName", value = { 
	         @Result(property = "id", column = "id", id = true), 
	         @Result(property = "name", column = "name", id = true), 
	         @Result(property = "byteContent", column = "bytecontent", id = true),
	         @Result(property = "src", column = "src", id = true) 
	       }) 
	@Select(value="select * from (select a.id,a.name,a.bytecontent,a.src from image a where a.name=#{name} order by a.addtime desc) where rownum=1")
	Image getImageByName(String name);

}
