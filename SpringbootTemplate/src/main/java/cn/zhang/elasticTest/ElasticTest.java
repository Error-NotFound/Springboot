package cn.zhang.elasticTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class ElasticTest {
	public static void main(String[] args) throws IOException {
		RestClient restClient = RestClient.builder(
		        new HttpHost("localhost", 9201, "http"),
		        new HttpHost("localhost", 9202, "http"),
		        new HttpHost("localhost", 9203, "http")).build();
		
		Response response = restClient.performRequest("GET", "/",
		        Collections.singletonMap("pretty", "true"));
		System.out.println(EntityUtils.toString(response.getEntity()));
		//index a document
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user", "zcm");
		map.put("age", 34);
		System.out.println(map.toString());
		HttpEntity entity2=new NStringEntity(map.toString(),ContentType.APPLICATION_JSON);
		HttpEntity entity=null;
		for (long i = 1; i < 100000000; i++) {
			entity = new NStringEntity(
			        "{\n" +
			        "    \"user\" : \"kimchy"+i+"\",\n" +
			        "    \"post_date\" : \"2017-04-06\",\n" +
			        "    \"message\" : \"trying out Elasticsearch"+i+"\"\n" +
			        "}", ContentType.APPLICATION_JSON);
			Response indexResponse = restClient.performRequest(
			        "PUT",
			        "/twitter/tweet/"+i,
			        Collections.<String, String>emptyMap(),
			        entity);
			
		}

		
     }
}
