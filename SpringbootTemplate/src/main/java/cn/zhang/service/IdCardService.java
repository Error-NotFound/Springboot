package cn.zhang.service;

import java.util.List;
import java.util.Map;

import cn.zhang.model.IdCard;


public interface IdCardService{
	Map<String,Object> operateIdCardData();
	String getRemoteAreaByIdCard();
	
	void updateNameByIdCardNo(String IdCardNo,String name);
	
	String getRemoteName(String idCardNo);
	
	String[] getAll();
	
	void addNameByIdCardNo();
	
}
