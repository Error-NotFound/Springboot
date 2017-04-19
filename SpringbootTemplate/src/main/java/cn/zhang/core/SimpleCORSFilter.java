/**
 * 
 */
package cn.zhang.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hiynn 
 * @Date 2016年6月23日下午2:25:05
 * 解决跨域所需要的filter
 */
public class SimpleCORSFilter implements Filter {

	private static int count = 1;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse)response;
		
//        HttpServletRequest req = (HttpServletRequest)request;
        
		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		
//		resp.setHeader("Access-Control-Max-Age", "3600");
		
		resp.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");


        chain.doFilter(request, response);

		System.out.println("filter访问次数: "+count++);
	}

	@Override
	public void destroy() {
		
	}

}
