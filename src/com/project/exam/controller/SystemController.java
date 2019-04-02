package com.project.exam.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.project.exam.model.SysUser;
import com.project.exam.service.SysUserService;
import com.project.utils.EncryptUtil;
import com.project.utils.StringUtil;

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
	
	@Autowired
	SysUserService userService;
	
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
	 * <p>Title: toSetPwd</p>
	 * <p>Description: 跳到修改密码页面</p>
	 * @param model
	 * @return String
	 */
	@RequestMapping("/toSetPwd")
	public String toSetPwd(Model model,Integer userId){
			if(StringUtil.isNotEmpty(userId)) {
				SysUser user = userService.selectByPrimaryKey(userId);
				
				model.addAttribute("user", user);
			}else {
				model.addAttribute("user", getCurrentUser());
			}
			return "system/setPwd";
	}

	/**
	 * <p>Title: setPwd</p>
	 * <p>Description: 保存用户信息</p>
	 * @param user 用户信息
	 * @param opassword 原密码 String类型
	 * @return String
	 */
	@RequestMapping(value = "/setPwd",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String setPwd(Model model,  SysUser user, String opassword){
		log.info("[setPwd] setPwd");
		Map<String,String> resultMap=new HashMap<String,String>();
		if(user == null){
			resultMap.put("flag", "0");
			resultMap.put("message", "未收到数据");
			return JSON.toJSONString(resultMap);
		}
		try {
			SysUser user1=userService.selectByPrimaryKey(user.getId());
			if(user1!=null) {
//				if(EncryptUtil.validatePassword(opassword, user1.getPassword())) {
					user.setPassword(EncryptUtil.entryptPassword(user.getPassword()));
					userService.updateByPrimaryKeySelective(user);
					resultMap.put("flag", "1");
					resultMap.put("message", "密码修改成功");
					return JSON.toJSONString(resultMap);
//				}else {
//					resultMap.put("flag", "0");
//					resultMap.put("message", "原密码错误");
//					return JSON.toJSONString(resultMap);
//				}
			}else {
				resultMap.put("flag", "0");
				resultMap.put("message", "用户不存在，请先登录");
				return JSON.toJSONString(resultMap);
			}
			
		} catch (Exception e) {
			
			resultMap.put("flag", "0");
			resultMap.put("message", "保存失败");
			return JSON.toJSONString(resultMap);
		}
		
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
