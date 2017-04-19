package cn.zhang.elasticTest;

import java.util.Date;
import java.util.Map;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkIndexByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import cn.zhang.sington.TransportClientSington;
public class ElasticTest2 {
	private static final TransportClient instance=TransportClientSington.getInstance();
	public static void main(String[] args) {
		multiSearchApi();
	}
	static void indexApi(){
		try {
			//添加
			XContentBuilder builder = XContentFactory.jsonBuilder()
				    .startObject()
				        .field("user", "kimchy")
				        .field("postDate", new Date())
				        .field("message", "trying out Elasticsearch")
				    .endObject();
			String json=builder.string();
			IndexResponse response=instance.prepareIndex("twitter", "tweet", "1")
					.setSource(builder).get();
			String rIndex=response.getIndex();
			String rType=response.getType();
			String rId=response.getId();
			long rVersion=response.getVersion();
			RestStatus status=response.status();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("操作es出错了"+e.getMessage());
		}
	}
	static void getApi(){
		GetResponse response=instance.prepareGet("twitter", "tweet", "1").get();
		Map<String,Object> map=response.getSource();
		for(String key:map.keySet()){
			System.out.println("key :"+key+"\tval :"+map.get(key));
		}
	}
	
	static void deleteApi(){
		DeleteResponse response=instance.prepareDelete("twitter", "tweet", "1").get();
		System.out.println(response.getIndex());
	}
	static void deleByQueryApi(){
/*		BulkIndexByScrollResponse response=DeleteByQueryAction.INSTANCE.newRequestBuilder(instance)
				.filter(QueryBuilders.matchQuery("gender", "male"))
				.source("persons")
				.get();
		long deleted=response.getDeleted();
		System.out.println(deleted);*/
		
		DeleteByQueryAction.INSTANCE.newRequestBuilder(instance)
				.filter(QueryBuilders.matchQuery("gender", "male"))
				.source("persons")
				.execute(new ActionListener<BulkIndexByScrollResponse>() {
					
					@Override
					public void onResponse(BulkIndexByScrollResponse response) {
						// TODO Auto-generated method stub
						long deleted=response.getDeleted();
						System.out.println(deleted);
					}
					
					@Override
					public void onFailure(Exception e) {
						// TODO Auto-generated method stub
						System.out.println("删除失败了："+e.getMessage());
					}
				});
	}
	/**
	 * 不存在更新空
	 */
	static void updateApi(){
		try {
			UpdateRequest updateRequest=new UpdateRequest();
			updateRequest.index("index");
			updateRequest.type("type");
			updateRequest.id("1");
			updateRequest.doc(XContentFactory.jsonBuilder()
					.startObject()
					.field("gender","male")
					.endObject());
			instance.update(updateRequest).get();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 有就更新，没有就新建
	 */
	static void upsertApi(){
		try {
			IndexRequest indexRequest=new IndexRequest("index","type","6")
					.source(XContentFactory.jsonBuilder()
					.startObject()
					.field("name","Joe Smithsssss")
					.field("gender","male")
					.endObject());
			UpdateRequest updateRequest=new UpdateRequest("index","type","6")
					.doc(XContentFactory.jsonBuilder()
							.startObject()
							.field("gender","males")
							.endObject())
					.upsert(indexRequest);
			instance.update(updateRequest).get();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("出国了"+e.getMessage());
		}
	}
	static void multiGetApi(){
		try {
			MultiGetResponse multiGetItemResponses=instance.prepareMultiGet()
					.add("twitter","tweet","1")
					.add("twitter", "tweet", "2","3","4")
					.add("another", "type", "foo")
					.get();
			for(MultiGetItemResponse itemResponse:multiGetItemResponses){
				GetResponse response=itemResponse.getResponse();
				if(response.isExists()){
					String json=response.getSourceAsString();
					System.out.println(json);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("出粗了:"+e.getMessage());
		}
	}
	static void bulkApi(){
		try {
			BulkRequestBuilder bulkRequest=instance.prepareBulk();
			bulkRequest.add(instance.prepareIndex("twitter", "tweet", "1")
					.setSource(XContentFactory.jsonBuilder()
							.startObject()
							.field("user","kimchy")
							.field("postDate",new Date())
							.field("message","trying out Elasticsearch")
							.endObject()
							)
					);
			bulkRequest.add(instance.prepareIndex("twitter", "tweet", "2")
					.setSource(XContentFactory.jsonBuilder()
							.startObject()
							.field("user","kimchy")
							.field("postDate",new Date())
							.field("message","another post")
							.endObject()
							)
					);
			BulkResponse bulkResponse=bulkRequest.get();
			if(bulkResponse.hasFailures()){
				System.out.println("你显示出错了");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("你出错了"+e.getMessage());
		}
	}
	static void  bulkProcessor(){
		try {
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
					.setBulkActions(10000)
					.setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
					.setFlushInterval(TimeValue.timeValueSeconds(5))
					.setConcurrentRequests(1)
					.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMinutes(100), 3))
					.build();
			
			bulkProcessor.add(new IndexRequest("twitter","tweet","1").source(XContentFactory
					.jsonBuilder()
					.startObject()
					.field("name","Joe Smithsssss")
					.field("gender","male")
					.endObject()));
			bulkProcessor.add(new IndexRequest("twitter","tweet","2").source(XContentFactory
					.jsonBuilder()
					.startObject()
					.field("name","Joe Smithsssss")
					.field("gender","male2")
					.endObject()));
			bulkProcessor.add(new IndexRequest("twitter","tweet","3").source(XContentFactory
					.jsonBuilder()
					.startObject()
					.field("name","Joe Smithsssss")
					.field("gender","male3")
					.endObject()));
			bulkProcessor.add(new DeleteRequest("twitter","tweet","2"));
			//flush any remaining requests
			bulkProcessor.flush();
			//close the bulkProcessor if you don't need it anymore
			bulkProcessor.close();
			//refresh your indices
			instance.admin().indices().prepareRefresh().get();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("出错了："+e.getMessage());
		}finally{
			
		}
		
	}
////////////////////////////////////////////search API//////////////////////////////////////////////////////////////////
	static void searchApi(){
		SearchResponse response=instance.prepareSearch("twitter","jwy")
				.setTypes("tweet","userInfo")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("nation", "33"))
				.setFrom(0).setSize(600).setExplain(true)
				.get();
		SearchHits searchHits=response.getHits();
		SearchHit searchHitsArray[]=searchHits.getHits();
		for(SearchHit sh:searchHitsArray){
			Map<String,Object> map=sh.getSource();
			for(String key:map.keySet()){
				System.out.println("key :"+key+"\tval :"+map.get(key));
			}
		}
	}
	
	static void multiSearchApi(){
		SearchRequestBuilder srb1=instance.prepareSearch()
				.setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
		SearchRequestBuilder srb2=instance.prepareSearch()
				.setQuery(QueryBuilders.matchQuery("importantIdNo", "142602198502280028")).setSize(1);
		MultiSearchResponse sr=instance.prepareMultiSearch()
				.add(srb1)
				.add(srb2)
				.get();
		long nbHits=0;
		for(MultiSearchResponse.Item item:sr.getResponses()){
			SearchResponse response=item.getResponse();
			nbHits+=response.getHits().getTotalHits();
		}
		System.out.println(nbHits);
	}
	
}
