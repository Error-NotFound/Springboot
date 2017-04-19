package cn.zhang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.zhang.service.IdCardService;

@RestController
@RequestMapping("/idCardController")
public class IdCardController {
	@Resource
	IdCardService idService;
	@RequestMapping(value="getJsonIdCard",method=RequestMethod.GET)
	/**
	 * 获取json格式的身份证号和区域名称
	 * @return
	 */
	public Map<String,Object> getJsonIdCard(){
		Map<String,Object> map=new HashMap<String,Object>();
		map=idService.operateIdCardData();
		return map;
	}
	/**
	 * 根据传入的6位身份证号查询所对应的地区
	 * @param idCard
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="operateIdCardData",method=RequestMethod.GET)
	public String operateIdCardData(@RequestParam("idCard")String idCard,HttpServletRequest request) throws IOException{
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"config"+File.separator+"idCardArea.json";
		File file=new File(path);
		Reader reader=new InputStreamReader(new FileInputStream(file),"utf-8");
		char chars[]=new char[1000];
		int len;
		StringBuilder sb=new StringBuilder();
		while((len=reader.read(chars))!=-1){
			sb.append(new String(chars,0,len));
		}
		JSONObject jsonObject=JSONObject.parseObject(sb.toString());
		JSONObject jsonResult=(JSONObject) jsonObject.get("result");
		if(idCard.length()>=6){
			idCard=idCard.substring(0, 6);
		}
		if(idCard.length()<6){
			return "数字小于6位!!!";
		}
		return jsonResult.get(idCard)+"";
	}
	/**
	 * 从远程网站获取全国的全市区代码并插入
	 * @return
	 */
	@RequestMapping(value="getRemoteAreaByIdCard",method=RequestMethod.GET)
	public String getRemoteAreaByIdCard(){
		this.idService.getRemoteAreaByIdCard();
		return null;
	}
	/**
	 * 从本地数据库获取地域代码然后从远程获取对应的名称并插入数据库 即：根据身份证前六位更新区域名称
	 * @return
	 */
	@RequestMapping(value="updateNameByIdCardNo",method=RequestMethod.GET)
	public String updateNameByIdCardNo(){
		this.idService.addNameByIdCardNo();
		return null;
	}
	
	public static void main(String[] args) {
		String str="1234";
		System.out.println(str.substring(0,6));
	}
	
	
}
