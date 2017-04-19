package cn.zhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import cn.zhang.model.IdCard;

public interface IdCardMapper {
	@Select("select a.no idNo,a.name areaName from id_card_by_remote a")
	public List<IdCard> operateIdCardData();
	
	
	@Insert(value="insert into id_card_by_remote values(seq_id_card_by_remote.nextval,#{cardNo},null)")
	void addIdCardNo(String cardNo);
	
	@Select("select a.id id,a.no idNo from id_card_by_remote a where a.name is null")
	public List<IdCard> getAll();
	
	@Update(value="update id_card_by_remote a set a.name=#{1} where a.no=#{0}")
	@Transactional
	void updateNameByIdCardNo(String idAreadNo,String name);
	
	
}
