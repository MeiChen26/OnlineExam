package com.project.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.exam.model.SysUser;

/**
 * <p>ClassName: SystemController</p>
 * <p>Description: 系统功能相关Controller</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController{
	static final Logger log = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * <p>Title: loginpage</p>
	 * <p>Description: 登录页，登录验证等逻辑有Shiro管理</p>
	 * @return String
	 */
	@RequestMapping("loginpage")
	public String loginpage(Model model, HttpServletRequest request){
		log.info("[loginpage] login page ... ");
		
		//校验，当前用户是否初始化
		Subject subject = SecurityUtils.getSubject();
    	if(subject != null && subject.isAuthenticated()){//如果登陆认证成功
    		return "redirect:/system/home";
    	}
		String exceptionClassName  = (String)request.getAttribute("shiroLoginFailure");
		if(StringUtils.isNotBlank(exceptionClassName)) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("nameWarn", "账号不存在");
	        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
	        	model.addAttribute("pwdWarn", "密码错误");
	        } else if("randomCodeError".equals(exceptionClassName)){
	        	model.addAttribute("codeWarn", "验证码错误");
            }else {
	        	model.addAttribute("pwdWarn", "未知错误 ");// 最终在异常处理器生成未知错误
	        }
		}
		return "system/loginpage";
	}
	
	/**
	 * <p>Title: home 首页</p>
	 * <p>Description: 首页显示 </p>
	 * @param model
	 * @return String
	 */
	@RequestMapping({"home",""})
	public String home(Model model, HttpServletRequest request){
		log.info("[home] home page ... ");
		return "redirect:/student/list";
	}
	
	/**
	 * <p>Title: unauthorized</p>
	 * <p>Description: 无权限跳转页面</p>
	 * @return String
	 */
	@RequestMapping("unauthorized")
	public String unauthorized(){
		log.info("[unauthorized] unauthorized page ... ");
		return "system/unauthorized";
	}
	
}
