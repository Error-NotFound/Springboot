package cn.zhang.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import cn.zhang.util.MyCountryClient;
import cn.zhang.util.MyIpAddressClient;
@Configuration
public class WebServiceConfigClient {
	@Bean(name="marshaller")
	public Jaxb2Marshaller marshaller(){
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		//this package must match the package in the <generatePackage> specified in the pom.xml;
		
		marshaller.setContextPath("cn.zhang.webservice.client");
		
		return marshaller;
		
	}
	@Bean
	public MyIpAddressClient webServiceIpClient(@Qualifier(value="marshaller")Jaxb2Marshaller marshaller){
		MyIpAddressClient client=new MyIpAddressClient();
		client.setDefaultUri("http://www.webxml.com.cn/WebServices");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean(name="marshaller2")
	public Jaxb2Marshaller marshaller2(){
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		//this package must match the package in the <generatePackage> specified in the pom.xml;
		
		marshaller.setContextPath("cn.zhang.webservice.testClient");
		
		return marshaller;
		
	}
	@Bean
	public MyCountryClient webServiceIpClient2(@Qualifier(value="marshaller2")Jaxb2Marshaller marshaller){
		MyCountryClient client=new MyCountryClient();
		client.setDefaultUri("http://192.168.0.41:8089/SpringbootTemplate/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
