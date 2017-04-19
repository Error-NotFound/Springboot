package cn.zhang.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.zhang.util.SessionMap;
/**
 * 监听session的创建和销毁，并打印当前系统在线人数
 * @author zcm
 *
 */
public class SessionListener implements HttpSessionListener{
	public SessionListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		SessionMap.addSession(session);
		
		ServletContext sc=session.getServletContext();
		/**
		 * 防止同时修改此变量
		 */
		synchronized (sc) {
			int accessCount=0;
			String account=(String) sc.getAttribute("accessCount");
			if(account!=null&&!"".equals(account)){
				accessCount=Integer.parseInt(account);
			}
			accessCount++;
			sc.setAttribute("accessCount", accessCount+"");
			session.setAttribute("accessCount", account+"");
			System.out.println("访问成功，当前在线人数："+accessCount);
		}
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		SessionMap.removeSession(session);
		ServletContext sc=session.getServletContext();
		
		synchronized (sc) {
			int accessCount=1;
			String account=(String) sc.getAttribute("accessCount");
			if(account!=null&&!"".equals(account)){
				accessCount=Integer.parseInt(account);
			}
			accessCount--;
			sc.setAttribute("accessCount", accessCount+"");
			session.setAttribute("accessCount", accessCount+"");
			System.out.println("退出成功，当前在线人数："+accessCount);
		}
	}
}
