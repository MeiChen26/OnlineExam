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
import com.project.exam.model.FrontUser;
import com.project.exam.model.TbExam;
import com.project.exam.model.TbQuestion;
import com.project.exam.service.ExamService;
import com.project.exam.service.FrontUserService;
import com.project.exam.service.QuestionService;
import com.project.utils.PaginationInfo;
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
		return "redirect:/f/onlineExam";
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
    		return "redirect:/f/onlineExam";
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
	
	
	/**
	 * 
	 * <p>Title: getExamQuestion</p>
	 * <p>Description: 随机抽取50道题</p>
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("onlineExam")
	public String getExamQuestion(HttpServletRequest request,Model model) {
		try {
			List<TbQuestion> list = this.questionService.getQuestions();
			model.addAttribute("questionlist", list);
			request.getSession().setMaxInactiveInterval(100 * 60);//设置session失效时间
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/exam";
	}
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 考试成绩</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/saveScore")
	public String list(HttpServletRequest request,Model model, Integer examscore){
		try {
			HttpSession e=request.getSession();
			FrontUser user=(FrontUser) e.getAttribute("frontUser");
			TbExam exam=new TbExam();
			exam.setStudentId(user.getId());
			exam.setScore(examscore*2);
			exam.setUpdateTime(new Date());
			exam.setCreateTime(new Date());
			examService.insertSelective(exam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/f/score";
	}
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 考试成绩</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/score")
	public String score(HttpServletRequest request,Model model, Integer reqPage, String keyword){
		try {
			//获取请求参数
			if (reqPage == null) {
				reqPage = 1;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			HttpSession e=request.getSession();
			FrontUser user=(FrontUser) e.getAttribute("frontUser");
			params.put("studentId", user.getId());
			PaginationInfo<TbExam> pageinfo = examService.getPaginationData(params, reqPage, SPLITPAGE_SIZE);
			model.addAttribute("pageinfo", pageinfo);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/score";
	}
}
