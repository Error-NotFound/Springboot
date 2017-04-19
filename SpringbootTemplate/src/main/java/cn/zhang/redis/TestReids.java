package cn.zhang.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
/**
 * jedis简单测试
 * @author zcm
 *
 */
public class TestReids {
	public static void main(String[] args) {
		Jedis jedis=null;
		try {
			jedis=new Jedis("192.168.0.41",6379);//192.168.0.41linux上的redis
			//jedis=new Jedis("192.168.1.29",6379);//192.168.1.29上的windows版本的redis
			jedis.auth("zcm");
			//清空數據庫
			System.out.println(jedis.flushDB());
			//key-value
			jedis.set("name","zcm");
			System.out.println(jedis.get("name"));
			jedis.setex("name", 3, "李四");//設置過期時間的
			for (int i = 0; i <4; i++) {
				System.out.println("等待"+i+"秒"+":"+jedis.get("name"));
				//Thread.sleep(1000);
			}
			//同时设置多个值
			jedis.mset("test1","test1_value","test2","test2_value");//多個key，value同時設置
			System.out.println(jedis.get("test1")+jedis.get("test2"));
			jedis.append("test1", "追加的内容");//根据key在對應的value后追加值
			System.out.println(jedis.get("test1"));
			List<String> list=jedis.mget("test1","test2","zcm");//获取多个key的value
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"*");
			}
			//使用hash存储，是一个String 类型的field的value
			jedis.hset("student", "name", "张三");
			jedis.hset("student", "age", "36");
			jedis.hset("student2", "name", "王五");
			System.out.println(jedis.hget("student", "name"));
			System.out.println(jedis.hget("student2", "name"));
			//批量村处置
			Map<String,String> zhouxileiMap=new HashMap<String,String>();
			zhouxileiMap.put("name", "周喜雷");
			zhouxileiMap.put("age", "23");
			zhouxileiMap.put("gender", "girl");
			System.out.println("zhouxileiMap:"+zhouxileiMap);
			jedis.hmset("student2", zhouxileiMap);
			System.out.println(jedis.hmget("student2", "name"));
			System.out.println(jedis.hmget("student2", "name","age","gender"));//一次取多个值
			System.out.println("zhouxileiMap map:"+jedis.hgetAll("student2"));//取key全部
			//lists链表结构
			jedis.lpush("list1", "张三1");//在list首部添加元素
			jedis.lpush("list1", "张三2");
			
			jedis.rpush("list1", "张三3");
			jedis.rpush("list1", "张三4");
			List<String> list2=jedis.lrange("list1", 1, 4);
			System.out.println("list1:"+jedis.lrange("list1",0,-1));
			System.out.println(JSONObject.toJSON(list2));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			jedis.close();
		}
	}
}
