package cn.zhang.util;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import cn.zhang.webservice.testClient.GetCountryRequest;
import cn.zhang.webservice.testClient.GetCountryResponse;




public class MyCountryClient extends WebServiceGatewaySupport{
	/**
	 * 根据ip获取国家城市
	 * @param ip
	 * @return
	 */
	public GetCountryResponse getCountrySth(String name){
		
		GetCountryRequest request =new GetCountryRequest();
		request.setName(name);
	
		
		GetCountryResponse response=(GetCountryResponse)getWebServiceTemplate()
				.marshalSendAndReceive("http://192.168.0.41:8089/SpringbootTemplate/ws/countries.wsdl", request);
		
		return response;
	}

}
