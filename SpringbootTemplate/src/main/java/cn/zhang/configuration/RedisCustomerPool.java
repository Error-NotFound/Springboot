package cn.zhang.configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自定義redis pool
 * @author zcm
 *
 */
public class RedisCustomerPool {
	private static final String ADDRESS="192.168.0.41";
	private static int PORT=6379;
	private static int MAX_ACTIVE=100;
	private static final int MAX_IDLE = 20;
	private static final String AUTH="zcm";
	private static final int WAIT = 10000;
	private static final int TIMEOUT = 10000;
	private static boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool = null;
	/**
	 * 初始化redis連接池
	 */
	static {
		try {
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool=new JedisPool(config,ADDRESS,PORT,TIMEOUT,AUTH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 獲得jedis實例
	 * @return
	 */
	public synchronized static Jedis getJedis(){
		try {
			if(jedisPool!=null){
				Jedis jedis=jedisPool.getResource();
				return jedis;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	/**
	 * 釋放jedis資源
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
		
}
