package cn.zhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.zhang.model.AbordRelationship;

public interface AbordRelationshipMapper {
	@Select(" SELECT * FROM   \r\n" + 
			"    (  \r\n" + 
			"    SELECT b.*, ROWNUM RN   \r\n" + 
			"    FROM (select rownum,a.sfzh idNo,a.zd_sfzh importantIdNo,a.xm relatedPeopleName,\r\n" + 
			"a.gx relationship,a.hj nationality,a.mz nation,a.xb gender FROM JWY_X_CRJHJSHGX a) b   \r\n" + 
			"    WHERE ROWNUM <= #{endNum}\r\n" + 
			"    )  \r\n" + 
			"    WHERE RN >= #{startNum}  ")
	List<AbordRelationship> getAll(@Param("startNum")long startNum,@Param("endNum")long endNum);
	
	@Select("select count(1) from JWY_X_CRJHJSHGX a ")
	long getCount();
}
