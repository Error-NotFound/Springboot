package cn.zhang.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.zhang.configuration.RedisCustomerPool;
import redis.clients.jedis.Jedis;

public class TestRedis2 {
	public static void main(String[] args) {
		final  Jedis jedis=RedisCustomerPool.getJedis();
		//清空數據庫
		System.out.println("清空數據庫："+jedis.flushDB());
		//key-value
		jedis.set("zhou", "z周喜雷");
		System.out.println(jedis.get("zhou"));
		
		//同時設置多個值
		jedis.mset("a","1","b","2","c","3");
		List<String> list1=jedis.mget("a","b","c");
		System.out.println(list1);
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "張三");
		map.put("age", "56");
		jedis.hmset("zhangsan", map);
		System.out.println(jedis.hmget("zhangsan", "name"));
		System.out.println(jedis.hgetAll("zhangsan"));
		
		//list鏈錶結構
		jedis.lpush("list2", "李四1");
		jedis.lpush("list2", "李四2");
		jedis.lpush("list2", "李四3"); 
		List<String> list2=jedis.lrange("list2", 0, -1);
		System.out.println("list2:"+list2);
		System.out.println("JSOBObject.toJSON:"+JSONObject.toJSON(list2));
		
		
	}
}
