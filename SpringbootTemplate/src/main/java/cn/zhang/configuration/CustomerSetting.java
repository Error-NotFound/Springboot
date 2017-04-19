package cn.zhang.configuration;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.zhang.core.SessionListener;
import cn.zhang.core.SimpleCORSFilter;

/**
 * 加上sessionListener和 跨域listener
 * @author zcm
 *
 */
@Configuration
public class CustomerSetting {
	/**
	 * 配置过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    
	    registration.setFilter(new SimpleCORSFilter());
	    
	    registration.addUrlPatterns("/*");
	    
	//    registration.addInitParameter("paramName", "paramValue");
	    
	    registration.setOrder(1);
	    
	    return registration;
	} 
	 /**
     * 配置sesionListener
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
    	
    	ServletListenerRegistrationBean servletListenerRegistrationBean=new ServletListenerRegistrationBean();
    	
    	servletListenerRegistrationBean.setListener(new SessionListener());
    	
    	return servletListenerRegistrationBean;
    }
}
