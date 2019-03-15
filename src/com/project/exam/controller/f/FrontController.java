package com.project.exam.controller.f;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.project.exam.controller.BaseController;
import com.project.exam.model.TbQuestion;
import com.project.exam.model.f.FrontUser;
import com.project.exam.service.ExamService;
import com.project.exam.service.QuestionService;
import com.project.exam.service.f.FrontUserService;
/**
 * 前台controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/f")
public class FrontController extends BaseController{
		
	@Autowired
	FrontUserService frontUserService;
	@Autowired
	ExamService examService;
	@Autowired
	QuestionService questionService;

	
	@RequestMapping(value = "index")
	public String index(Model model) {		
		return "front/index";
	}
	
	@RequestMapping(value = "toLogin")
	public String toLogin() {
		return "front/login";
	}

	@RequestMapping(value = "login")
	public String login(Model model,HttpServletRequest request,FrontUser frontUser) {
		frontUser = frontUserService.getByCondition(frontUser);
		HttpSession e=request.getSession();
    	if (frontUser != null) {
    		e.setAttribute("frontUser", frontUser);
    		return "front/selfCenter";
			} else {
			model.addAttribute("message", "用户名或密码错误");
			return "front/login";
		}	
		

	}
	@RequestMapping(value = "toRegister")
	public String toRegister() {
		return "front/register";
	}
	
	@RequestMapping(value = "register")
	@ResponseBody
	public String register(Model model,FrontUser frontUser) {
		frontUser.setCreateTime(new Date());
		try {
			frontUserService.insert(frontUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "注册失败");
			return "fail";
		}
		model.addAttribute("message", "注册成功");
		return "success";

	}
	
	@RequestMapping(value = "selfCenter")
	public String selfCenter() {
		return "front/selfCenter";
	}
	
	@RequestMapping(value = "saveFrontUser")
	@ResponseBody
	public String saveFrontUser(Model model,FrontUser frontUser) {
		try {
			frontUserService.updateByPrimaryKeySelective(frontUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "修改失败");
			return "fail";
		}
		model.addAttribute("message", "修改成功");
		return "success";
	}
	
	@RequestMapping(value = "onlineExam")
	public String onlineExam() {
		return "front/selfCenter";
	}
	
	/**
	 * 
	 * <p>Title: getExamQuestion</p>
	 * <p>Description: 随机抽取50道题</p>
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("getExamQuestion")
	public Map<String, Object> getExamQuestion(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		try {
			String uid = request.getParameter("uid");
			if(StringUtils.isBlank(uid)) {
				map.put("code", "001");
				map.put("reason", "缺少参数");
				return map;
			}
			
			List<TbQuestion> list = this.questionService.getQuestion();
			Map<String, Object> result = new HashMap<>();
			result.put("questionlist", list);
			map.put("code", "000");
			map.put("result", result);
		} catch (Exception e) {
			map.put("code", "001");
			map.put("reason", "");
			e.printStackTrace();
		}
		return map;
	}
}
