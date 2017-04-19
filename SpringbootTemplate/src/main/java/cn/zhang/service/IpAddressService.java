package cn.zhang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhang.util.MyIpAddressClient;
import cn.zhang.webservice.client.ArrayOfString;
import cn.zhang.webservice.client.GetCountryCityByIpResponse;
import cn.zhang.webservice.client.GetGeoIPContextResponse;
@Service("IpAddressService")
public class IpAddressService {
	@Autowired
	MyIpAddressClient client;
	public String getCountryCityByIp(String ip){
		GetCountryCityByIpResponse response= client.getCountryCityByIp(ip);
		ArrayOfString strs=response.getGetCountryCityByIpResult();
		List<String> list=strs.getString();
		return list.toString();
	}
	public String getGeoIPContext(){
		GetGeoIPContextResponse response= client.getGeoIPContext();
		ArrayOfString strs=response.getGetGeoIPContextResult();
		List<String> list=strs.getString();
		return list.toString();
	}
	
}
