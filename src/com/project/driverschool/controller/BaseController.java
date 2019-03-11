package com.project.exam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.exam.model.SysUser;
import com.project.utils.Constant;
import com.project.utils.HttpSendObject;
import com.project.utils.JsonConvertUtil;

/**
 * <p>ClassName: BaseController</p>
 * <p>Description: Controller基类，本项目中所有的Controller均继承此方法</p>
 * @Author 
 * @Date: 
 */
public class BaseController{
	
	public static Logger logger = Logger.getLogger(BaseController.class);
	public static final String RESULT_TYPE="messageType";
	public static final String RESULT_MSG ="message";
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	
	/**
	 * @Fields SPLITPAGE_SIZE : 用户管理分页，每页的数目
	 */
	public static final int SPLITPAGE_SIZE = 10;
	
	/**
	 * <p>Title: getHttpRequest</p>
	 * <p>Description: 获取当前请求的HttpServletRequest对象</p>
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpRequest(){
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * <p>Title: getHttpResponse</p>
	 * <p>Description: 获取当前请求的HttpServletResponse对象</p>
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getHttpResponse(){
		HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder
				.getRequestAttributes()).getResponse();
		return response;
	}
	
	/**
	 * <p>Title: getCurrentUser</p>
	 * <p>Description: 获取当前用户登录信息</p>
	 * @return SysUser
	 */
	public SysUser getCurrentUser() {
		return (SysUser)getHttpSession().getAttribute("currentUser");
	}
	
	/**
	 * 
	 * <p>Title: getCurrentUserId</p>
	 * <p>Description: 获取当前登录用户id</p>
	 * @return Integer
	 */
	public Integer getCurrentUserId() {
		SysUser user = getCurrentUser();
		if(user == null)
			return null;
		else
			return user.getId();
	}
	
	/**
	 * <p>Title: getRealPath</p>
	 * <p>Description: 获取项目路径</p>
	 * @return String
	 * @see javax.servlet.ServletContext.getRealPath(String path)
	 */
	public String getRealPath(){
		return getHttpSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * <p>Title: getAttribute</p>
	 * <p>Description: 获取request中的attribute值,本方法使用泛型可以根据返回类型自适应</p>
	 * @param key
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key){
		Object object = getHttpRequest().getAttribute(key);
		if(object == null) {
			return null;
		}
		T t = null;
		try {
			t = (T)object;
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <p>Title: setAttribute</p>
	 * <p>Description: 设置request中的attribute值</p>
	 * @param key 关键字key
	 * @param value 值 value
	 */
	public void setAttribute(String key,Object value){
		 getHttpRequest().setAttribute(key,value);
	}
	
	/**
	 * <p>Title: getParamter</p>
	 * <p>Description: 获取请求参数</p>
	 * @param key 参数的名称
	 * @return String 参数的值
	 */
	public String getParamter(String key){
		return getHttpRequest().getParameter(key);
	}
	
	/**
	 * <p>Title: getParamters</p>
	 * <p>Description: 获取请求参数</p>
	 * @param key 参数的名称
	 * @return String[] 参数的值
	 */
	public String [] getParamters(String key){
		return getHttpRequest().getParameterValues(key);
	}
	
	/**
	 * <p>Title: getRequestURI</p>
	 * <p>Description: 返回请求url 路径部分，例如：
	 * First line of HTTP request 
	 * Returned Value
	 * POST /some/path.html HTTP/1.1  /some/path.html  
	 * GET http://foo.bar/a.html HTTP/1.0   /a.html  
	 * HEAD /xyz?a=b HTTP/1.1  /xyz  
	 * </p>
	 * @return String
	 */
	public String getRequestURI(){
		return getHttpRequest().getRequestURI();
	}
	
	public String getContextPath(){
		return getHttpRequest().getContextPath();
	}
	
	/**
	 * <p>Title: getHttpSession</p>
	 * <p>Description: 获取 HttpSession</p>
	 * @return HttpSession 对象
	 */
	public static HttpSession getHttpSession(){
		return getHttpRequest().getSession();
	}
	
	public static void setSessionObject(String key,Object value){
		getHttpSession().setAttribute(key,value);
	}
	
	public static Object getSessionObject(String key){
		return getHttpSession().getAttribute(key);
	}
	
	public static void  removeSessionObject(String key){
		 getHttpSession().removeAttribute(key);
	}
	
	/**
	 * <p>Title: getCookie</p>
	 * <p>Description: 获取cookie</p>
	 * @param key cookie的键值
	 * @return Object
	 */
	public static Object getCookie(String key){
	    Cookie[] cookies = getHttpRequest().getCookies();
	    if(cookies==null){
	        return null;
	    }
	    for(Cookie ck : cookies){
	        if(key.equals(ck.getName())){
	            return ck.getValue();
	        }
	    }
	    return null;
	}
	
	/**
	 * <p>Title: setCookie</p>
	 * <p>Description: 设置Cookie</p>
	 * @param key 关键字
	 * @param o cookie的值
	 * @param maxAge 期限单位：秒
	 * @param response void
	 */
	public static void setCookie(String key,String o,Integer maxAge){
	    Cookie cookies = new Cookie(key, o);
	    if(maxAge==null){
	        cookies.setMaxAge(-1);
	    }else{
	        cookies.setMaxAge(maxAge);
	    }
	    cookies.setPath("/");
	    getHttpResponse().addCookie(cookies);
	}
	
	/**
	 * <p>Title: getIpFromRequest</p>
	 * <p>Description: 获取请求的IP地址</p>
	 * @param request
	 * @return String
	 */
	public static String getIpFromRequest(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(",");
        return ips[0].trim();
    }
	
	
	/**
	 * 取得参数Map
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			paraMap.put(key, request.getParameter(key));
		}
		return paraMap;
	}
	
	
	/**
	 * 成功返回结果
	 * @param response
	 */
	protected void responseWriterObject(HttpServletResponse response, Object data){
		
		if(data instanceof JSON) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", Constant.HTTP_REQUEST_SUCCESS);
			jsonObject.put("reason", Constant.HTTP_REQUEST_SUCCESS_MSG);
			jsonObject.put("result", data);
			HttpSendObject.sendObject(jsonObject.toString(), response);
		} else {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("code", Constant.HTTP_REQUEST_SUCCESS);
			dataMap.put("reason", Constant.HTTP_REQUEST_SUCCESS_MSG);
			dataMap.put("result", data);
			HttpSendObject.sendObject(JsonConvertUtil.toFastJsonString(dataMap), response);
		}
		
	}
	
	protected Map<String, Object> responseWriterObjectReturn(Object data){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("code", Constant.HTTP_REQUEST_SUCCESS);
		dataMap.put("reason", Constant.HTTP_REQUEST_SUCCESS_MSG);
		dataMap.put("result", data);
		return dataMap;
	}
	
	/**
	 * 调用失败
	 * @param response
	 * @param msg
	 */
	protected void responseWriterFail(HttpServletResponse response, String msg){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("code", Constant.HTTP_REQUEST_FAIL);
		dataMap.put("reason", msg);
		HttpSendObject.sendObject(JsonConvertUtil.toFastJsonString(dataMap), response);
	}
	
	protected Map<String, Object> responseWriterFailReturn(String msg){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("code", Constant.HTTP_REQUEST_FAIL);
		dataMap.put("reason", msg);
		return dataMap;
	}
	
	/**
	 * 调用失败
	 * @param response
	 * @param msg
	 */
	protected void responseWriterSuccess(HttpServletResponse response, String msg){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("code", Constant.HTTP_REQUEST_SUCCESS);
		dataMap.put("reason", msg);
		HttpSendObject.sendObject(JsonConvertUtil.toFastJsonString(dataMap), response);
	}
	
	/**
	 * 设置跨域请求
	 * 
	 * <p>Title: setResponseHeader</p>
	 * <p>Description: </p>
	 * @param response void
	 */
	public void setResponseHeader(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
	}

	/***
	 * 从request流中读取参数
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws ClassNotFoundException
	 */
	public static String receivePostJson(HttpServletRequest request) throws Exception{
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        // 将资料解码
        String reqBody = sb.toString();
        return URLDecoder.decode(reqBody, "UTF-8");
    }
	
	/**
	 * 获取客户端IP
	 * 
	 * <p>Title: getIpAddr</p>
	 * <p>Description: </p>
	 * @param request
	 * @return String
	 */
	public String getIpAddr(HttpServletRequest request) {  
	       String ip = request.getHeader("x-forwarded-for");  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getHeader("Proxy-Client-IP");  
	       }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getHeader("WL-Proxy-Client-IP");  
	       }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getRemoteAddr();  
	       }  
	       return ip;  
	   }  
}