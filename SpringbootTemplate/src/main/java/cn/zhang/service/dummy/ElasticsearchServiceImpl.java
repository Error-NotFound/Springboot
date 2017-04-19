package cn.zhang.service.dummy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import cn.zhang.mapper.AbordRelationshipMapper;
import cn.zhang.model.AbordRelationship;
import cn.zhang.service.ElasticsearchService;
import cn.zhang.sington.TransportClientSington;

@Service("elasticsearchServiceImpl")
public class ElasticsearchServiceImpl  implements ElasticsearchService{
	@Resource
	AbordRelationshipMapper dao;
	TransportClient instance=null;
	@Override
	public void getAll() {
		try {
			/*instance=TransportClientSington.getInstance();
			List<AbordRelationship> list=dao.getAll();
			BulkRequestBuilder bulkRequest=instance.prepareBulk();
			for(int i=0;i<list.size();i++){
				bulkRequest.add(instance.prepareIndex("jwy", "userInfo", i+1+"")
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("idNo",list.get(i).getIdNo())
								.field("importantIdNo",list.get(i).getImportantIdNo())
								.field("relatedPeopleName",list.get(i).getRelatedPeopleName())
								.field("relationship",list.get(i).getRelationship())
								.field("nationality",list.get(i).getNationality())
								.field("nation",list.get(i).getNation())
								.field("gender",list.get(i).getGender())
								.endObject()
								)
						);
			}
			BulkResponse bulkResponse=bulkRequest.get();
			if(bulkResponse.hasFailures()){
				System.out.println("你显示出错了");
			}*/
			
			instance=TransportClientSington.getInstance();
			long num=dao.getCount();
			long eachNum=num/100;
			long lastNum=num-num/100*99;
			
			long numArray[]=new long[100];
			for (int j = 0; j < 99; j++) {
				numArray[j]=eachNum;
			}
			List<AbordRelationship> list=new ArrayList<AbordRelationship>();
			numArray[99]=lastNum;
			for(long k=0;k<num;k++){
				list=new ArrayList<AbordRelationship>();
				long startTime=System.currentTimeMillis();
				list=dao.getAll(k, k+=eachNum);
			BulkProcessor bulkProcessor=BulkProcessor.builder(instance, new Listener() {
				@Override
				public void beforeBulk(long executionId, BulkRequest request) {
					// TODO Auto-generated method stub
				}
				@Override
				public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
					// TODO Auto-generated method stub
				}
				@Override
				public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
					// TODO Auto-generated method stub
				}
			})
					.setBulkActions(100000)
					.setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
					.setFlushInterval(TimeValue.timeValueSeconds(15))
					.setConcurrentRequests(1)
					.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMinutes(100), 3))
					.build();
			
			for(int i=0;i<list.size();i++){
				bulkProcessor.add(new IndexRequest("jwy","userInfo").source(XContentFactory
						.jsonBuilder()
						.startObject()
						.field("idNo",list.get(i).getIdNo())
						.field("importantIdNo",list.get(i).getImportantIdNo())
						.field("relatedPeopleName",list.get(i).getRelatedPeopleName())
						.field("relationship",list.get(i).getRelationship())
						.field("nationality",list.get(i).getNationality())
						.field("nation",list.get(i).getNation())
						.field("gender",list.get(i).getGender())
						.endObject()));
			}
			
			long endTime1=System.currentTimeMillis();
			System.out.println("查询和封装用时："+(endTime1-startTime)/1000+"秒");
			//flush any remaining requests
			bulkProcessor.flush();
			//close the bulkProcessor if you don't need it anymore
			bulkProcessor.close();
			//refresh your indices
			instance.admin().indices().prepareRefresh().get();
			long time2=System.currentTimeMillis();
			System.out.println("插入es用时："+(time2-endTime1) /1000+"秒");
			}
			System.out.println("插入完毕");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入出错了"+e.getMessage());
		}
	}
	public static void main(String[] args) {
		
		long num=115986711;
		long eachNum=num/12;
		long lastNum=num-num/12*11;
		
		long numArray[]=new long[12];
		for (int i = 0; i < 11; i++) {
			numArray[i]=eachNum;
		}
		numArray[11]=lastNum;
		for(long i=0,j=0;i<num;i++,j++){
			System.out.println(i+","+(i+=eachNum));
		}
		
	}
	@Override
	public long getCount() {
		return dao.getCount();
	}
}
