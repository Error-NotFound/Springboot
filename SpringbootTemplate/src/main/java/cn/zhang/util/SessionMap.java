package cn.zhang.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


/**
 * session管理对象
 * @author zcm
 *
 */
public class SessionMap {
	private static Map<String,HttpSession> sessionMap=new HashMap<String,HttpSession>();
	/**
	 * 把session加入map
	 * @param session
	 */
	public static void addSession(HttpSession session){
		String sessionId=session.getId();
		sessionMap.put(sessionId, session);
	}
	/**
	 * 手动移除session
	 * @param session
	 */
	public static void removeSession(HttpSession session){
		String sessionId=session.getId();
		sessionMap.remove(sessionId);
	}
	/**
	 * 获取session
	 * @param sessionId
	 * @return
	 */
	public static HttpSession getSession(String sessionId){
		return sessionMap.get(sessionId);
	}
	/**
	 * 判断此session是否有效
	 * @param sessionId
	 * @return
	 */
	public static boolean isValid(String sessionId){
		HttpSession session=sessionMap.get(sessionId);
		if(session==null){
			sessionMap.remove(sessionId);
			return false;
		}
		return true;
	}
}
