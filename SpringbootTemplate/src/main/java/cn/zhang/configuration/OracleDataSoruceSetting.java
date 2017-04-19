package cn.zhang.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="oracle.datasource")
public class OracleDataSoruceSetting {
	
	/**
	 * 数据源类型（可以无此属性）
	 */
	private String type;
	
	/**
	 *数据源驱动
	 */
	private String driver; 
	
	/**
	 *数据源地址 
	 */
	private String url; 
	
	/**
	 * 用户名
	 */
	private String username; 
	
	/**
	 * 密码
	 */
	private String password; 
	
	/**
	 * 最大连接池数量
	 */
	private int maxActive; 
	
	/**
	 * 最小连接池数量
	 */
	private int minIdel; 
	
	/**
	 * 已经不再使用，配置了也没效果 *_*
	 */
	private int maxIdle; 
	
	/**
	 * 获取连接时最大等待时间，单位毫秒
	 */
	private int maxWait; 
	
	
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMinIdel() {
		return minIdel;
	}

	public void setMinIdel(int minIdel) {
		this.minIdel = minIdel;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}


	

}
