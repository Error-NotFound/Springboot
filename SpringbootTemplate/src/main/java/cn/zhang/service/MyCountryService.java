package cn.zhang.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhang.util.MyCountryClient;
import cn.zhang.webservice.testClient.Country;
import cn.zhang.webservice.testClient.GetCountryResponse;


@Service("MyCountryService")
public class MyCountryService {
	@Autowired
	MyCountryClient client;
	public String getCountrySth(String name){
		GetCountryResponse response= client.getCountrySth(name);
		Country strs=response.getCountry();
		return strs.toString();
	}
}
