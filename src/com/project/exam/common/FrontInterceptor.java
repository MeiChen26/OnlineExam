package com.project.exam.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.exam.model.f.FrontUser;



public class FrontInterceptor extends HandlerInterceptorAdapter{
	 @Override
	    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
	    	
		     HttpSession session = request.getSession();
		     FrontUser frontUser=(FrontUser) session.getAttribute("frontUser");
	    	if(frontUser == null){
	    		try {
					request.getRequestDispatcher("/f/toLogin").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
	            
	            return false;  
	    	}
	        return true;
	    }

}
