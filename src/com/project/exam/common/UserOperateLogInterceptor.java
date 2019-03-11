package com.project.exam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.exam.model.SysUser;


/**
 * @ClassName: UserOperateLogInterceptor
 * @Description: 用户操作日志拦截器
 * 				用于记录用户在平台中的操作信息
 * @Author  
 * @Create
 */
public class UserOperateLogInterceptor extends HandlerInterceptorAdapter{
    static final Logger log = LoggerFactory.getLogger("SystemUserOperateLog");

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    	String remoteIP = getRemoteIP(request);
    	SysUser currentUser = (SysUser)request.getSession().getAttribute("currentUser");
    	String username = (currentUser == null || currentUser.getUserName() == null) ? "unlogin" : currentUser.getUserName();
    	String requestURI = request.getRequestURI();
    	log.info("[OperateLog]Action: [" + requestURI + "] \tUser:" + username + "\tIP:" + remoteIP);
        return true;
    }
    
	/**
	 * @Title: getRemortIP
	 * @Description: 获取用户请求ip
	 * @param request
	 * @return String
	 */
	public String getRemoteIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}