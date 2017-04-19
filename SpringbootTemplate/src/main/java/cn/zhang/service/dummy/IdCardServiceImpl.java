package cn.zhang.service.dummy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.zhang.mapper.IdCardMapper;
import cn.zhang.model.IdCard;
import cn.zhang.service.IdCardService;
import cn.zhang.util.HttpHelper;


@Service("idCardServiceImpl")
public class IdCardServiceImpl implements IdCardService{
	@Resource
	IdCardMapper dao;
	public Map<String,Object> operateIdCardData() {
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> result=new HashMap<String,Object>();
		List<IdCard> list=dao.operateIdCardData();
		IdCard idCard=null;
		for (int i = 0; i < list.size(); i++) {
			idCard=list.get(i);
			map.put(idCard.getIdNo(), idCard.getAreaName());
		}
		result.put("result",map);
		return result;
	}
	
	
	@Override
	public String getRemoteAreaByIdCard(){
		String url="";
		int param[]={0,1000,2000,3000,4000,5000,6000};
		for (int i = 0; i < param.length; i++) {
			url="http://www.aa963.com/iden/shfzhmore.asp?offset="+param[i];
			try {
				String[] nos=HttpHelper.getRemoteAreaByIdCardByHttpGet(url,param[i]);
				for (String no : nos) {
					dao.addIdCardNo(no);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("暂停一会");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	
	public void addNameByIdCardNo(){
		try {
			String []nos=getAll();
			for (int i=0;i<nos.length;i++) {
				String name=getRemoteName(nos[i]);
				Date startDate=new Date();
				int count=1;
				if(null==name){
					--i;//执行上一个
					if(count++==10){//每个身份证号最多请求10次
						System.out.println(nos[i]+"获取name失败,请手动添加");
						break;
					}
					continue;
				}
				updateNameByIdCardNo(nos[i],name);
				try {
					Thread.sleep(3000);
					Date endDate=new Date();
					System.out.println("执行了"+(i+1)+"个，"+count+"次,"+nos[i]+":"+name+"耗时："+calLastedTime(startDate,endDate)+"秒");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}
	public  int calLastedTime(Date startDate,Date endDate) {
		  long a = startDate.getTime();
		  long b = endDate.getTime();
		  int c = (int)((b-a) / 1000);
		  return c;
		 }
	/**
	 * 获取所有name为null的数据
	 */
	@Override
	public String[] getAll() {
		// TODO Auto-generated method stub
		List<IdCard> list=dao.getAll();
		String idCardNos[]=new String[list.size()];
		for (int i=0;i<list.size();i++) {
			String idCardNo=list.get(i).getIdNo();//身份证号
			idCardNos[i]=idCardNo;
		}
		return idCardNos;
	}
	/**
	 * 根据代码从远程网站获取地区名称
	 * 
	 */
	@Override
	public String getRemoteName(String idCardNo) {
		// TODO Auto-generated method stub
		String url="http://www.aa963.com/iden/search.asp?id="+idCardNo;
		String name=null;
		try {
			name=HttpHelper.getRemoteAreaNameByIdCardAndHttpGet(url);
			//updateNameByIdCardNo(idCardNo,name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return name;
	}
	/**
	 * 根据身份证号码更新对应区域代码
	 */
	@Override
	public void updateNameByIdCardNo(String idAreadNo,String name) {
		dao.updateNameByIdCardNo(idAreadNo, name);
	}
	
	
}
