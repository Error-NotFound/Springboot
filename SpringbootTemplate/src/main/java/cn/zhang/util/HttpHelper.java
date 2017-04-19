package cn.zhang.util;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 不是公用类
 * @author zcm
 * 2017年3月6日上午11:00:38
 */
public class HttpHelper {
	
	public static String[] getRemoteAreaByIdCardByHttpGet(String url,int page) throws Exception{
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
        		setSocketTimeout(5000).setConnectTimeout(5000).build();
        httpGet.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                                   + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                resultStr=resultStr.replace(" ", "");
                //仅供此业务需要
                String resultByTagP[]=resultStr.split("</p>");
                String strP=resultByTagP[1];
                strP=strP.replace(" ", "");
                strP=strP.replace("\r\n", "");
            	strP=strP.replace("<divclass=\"shplink\"></div><p>", "");
            	String resultByTagA[]=strP.split("</a>");
            	String nos[]=new String[resultByTagA.length];
            	for (int i=0;i<resultByTagA.length;i++) {
            		String no=resultByTagA[i].substring(resultByTagA[i].indexOf("\">")+2);
            		nos[i]=no;
				}
            	return nos;
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
	/**
	 * 根据身份证前6位从远程网站获取名称
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getRemoteAreaNameByIdCardAndHttpGet(String url) throws Exception{
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
        		setSocketTimeout(5000).setConnectTimeout(5000).build();
        httpGet.setConfig(requestConfig);

        try {
        	
            response = httpClient.execute(httpGet, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                                   + ", url=" + url);
                return null;
            }
            
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                resultStr=resultStr.replace(" ", "");
                //仅供此业务需要
                String resultByTagTR[]=resultStr.split("</tr>");
                String result=resultByTagTR[2];
                result=result.replace("\r\n", "");
                int index=result.lastIndexOf("<tdclass=\"table\">")+17;
                result=result.substring(index);
                result=result.replace("</td>", "");
               return result;
            }
          
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
	
	public static void main(String[] args) {
		String strTest="<ahref=\"shfzh.asp?id=77\">230606";
		System.out.println(strTest.substring(strTest.indexOf("\">")+2));
		 long a = new Date().getTime();
		 String url="http://www.aa963.com/iden/shfzhmore.asp?offset="+5000;
		 try {
			 String[] nos=HttpHelper.getRemoteAreaByIdCardByHttpGet(url,5000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  long b = new Date().getTime();
		  int c = (int)((b - a) / 1000);
		  System.out.println(c);
		
		try {
		/*String urlTest="http://www.aa963.com/iden/search.asp?id=329003";
		for (int i = 0; i < 5; i++) {
			String name=HttpHelper.getRemoteAreaNameByIdCardAndHttpGet(urlTest);
			if(null==name){
				continue;
			}
		}*/
		
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str="sadf</p>cd</p>rt";
		//int a=str.lastIndexOf("</p>");
		//System.out.println(str.substring(a+4));
		String strs[]=str.split("</p>");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
	public static JSONObject httpPost(String url, Object data) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
        		setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");

        try {
        	StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
            httpPost.setEntity(requestEntity);
            
            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                                   + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");

                JSONObject result = JSON.parseObject(resultStr);
                if (result.getInteger("errcode") == 0) {
                	result.remove("errcode");
                	result.remove("errmsg");
                    return result;
                } else {
                    System.out.println("request url=" + url + ",return value=");
                    System.out.println(resultStr);
                    int errCode = result.getInteger("errcode");
                    String errMsg = result.getString("errmsg");
                   // throw new Exception();
                }
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
