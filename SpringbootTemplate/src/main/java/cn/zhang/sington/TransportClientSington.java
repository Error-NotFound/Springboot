package cn.zhang.sington;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * es内置客户端TransportClient单利模式
 * @author zcm
 * 2017年4月7日上午11:10:21
 */
public class TransportClientSington {
	private TransportClientSington() {}
	private static volatile TransportClient instance;
	public static TransportClient getInstance(){
		if(instance==null){
			//同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不在重复被创建）
			synchronized(TransportClientSington.class){
				//未初始化时，则初始化instance变量
				if(instance==null){
					//其他配置
					Settings settings=Settings.builder()
							.put("cluster.name","elasticsearch_test")
							.put("client.transport.sniff",true)
							.put("client.transport.ping_timeout", "120s")
							.build();
					instance=new PreBuiltTransportClient(settings);
					//add transport address and do something with the client
					try {
						instance.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.5.98"),9301))
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.5.98"),9302))
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.5.98"),9303));
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("连接es出错了："+e.getMessage());
					}
				}
			}
		}
		return instance;
	}
}
