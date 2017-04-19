package cn.zhang.util;

import java.util.List;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import cn.zhang.webservice.client.ArrayOfString;
import cn.zhang.webservice.client.GetCountryCityByIp;
import cn.zhang.webservice.client.GetCountryCityByIpResponse;
import cn.zhang.webservice.client.GetGeoIPContext;
import cn.zhang.webservice.client.GetGeoIPContextResponse;

public class MyIpAddressClient extends WebServiceGatewaySupport {
	
	/**
	 * 根据ip获取国家城市
	 * @param ip
	 * @return
	 */
	public GetCountryCityByIpResponse getCountryCityByIp(String ip){
		
		GetCountryCityByIp request =new GetCountryCityByIp();
		request.setTheIpAddress(ip);
		
		GetCountryCityByIpResponse response=(GetCountryCityByIpResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx", request, 
						new SoapActionCallback("http://WebXml.com.cn/getCountryCityByIp"));
		
		return response;
	}
	/**
	 * 測試輸出結果
	 * @param response
	 */
	public void printResponse(GetCountryCityByIpResponse response){
		
		ArrayOfString aos=response.getGetCountryCityByIpResult();
		List<String> list=aos.getString();
		String[] strs=new String[list.size()];
		list.toArray(strs);
	}
	
	/**
	 * 获取地理ip
	 * @return
	 */
	public GetGeoIPContextResponse getGeoIPContext(){
		GetGeoIPContext request=new GetGeoIPContext();
		GetGeoIPContextResponse response=(GetGeoIPContextResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx", request, 
						new SoapActionCallback("http://WebXml.com.cn/getGeoIPContext"));
		return response;
	}
}