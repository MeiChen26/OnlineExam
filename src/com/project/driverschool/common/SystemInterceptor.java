package com.project.exam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.exam.model.SysUser;

/**
 * @ClassName: SystemInterceptor
 * @Description: 初始化用户数据
 * 	拦截器拦截过程：
 * 		1. 拦截除登录也的所有请求
 * 		2. 判断Shiro是否登录验证成功
 * 		3. 如果验证成功，判断用户数据是否初始化
 * 		4. 如果未初始化，初始化用户数据
 * @Author  
 * @Create 
 */
public class SystemInterceptor extends HandlerInterceptorAdapter{
    static final Logger log = LoggerFactory.getLogger(SystemInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    	log.debug("[preHandle] check if user initialized.");
        
    	//校验，当前用户是否初始化
    	Subject subject = SecurityUtils.getSubject();
    	if(subject != null && subject.isAuthenticated()){//如果登陆认证成功
    		HttpSession session = request.getSession();
    		SysUser currentUser = (SysUser)session.getAttribute("currentUser");
    		if(currentUser == null){//未初始化,初始化时，添加登录日志
    			SysUser loginUser = (SysUser)subject.getPrincipal();
    			if(loginUser == null){
    				subject.logout();
    			}
    			session.setAttribute("currentUser", loginUser);
    			
    			
    		}
    	}
        return true;
    }

	
}