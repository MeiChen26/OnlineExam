package com.project.exam.controller.f;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.project.exam.common.Configuration;
import com.project.exam.controller.BaseController;
import com.project.exam.model.FrontUser;
import com.project.exam.model.TbExam;
import com.project.exam.model.TbExamInfo;
import com.project.exam.model.TbQuestion;
import com.project.exam.service.ExamInfoService;
import com.project.exam.service.ExamService;
import com.project.exam.service.FrontUserService;
import com.project.exam.service.QuestionService;
import com.project.utils.ExportExcel;
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
	@Autowired
	ExamInfoService examInfoService;

	
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
		frontUser.setDeleted(false);
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
			int exam_size=Integer.valueOf(Configuration.get("exam_size"));
			model.addAttribute("exam_size", exam_size);
			request.getSession().setMaxInactiveInterval(100 * 60);//设置session失效时间
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/exam";
	}
	
	/**
	 * <p>Title: saveScore</p>
	 * <p>Description: 保存考试信息</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/saveScore")
	public String list(HttpServletRequest request,Model model, Integer examscore){
		try {
			HttpSession e=request.getSession();
			FrontUser user=(FrontUser) e.getAttribute("frontUser");
			int exam_size=Integer.valueOf(Configuration.get("exam_size"));
			List<TbExamInfo> examInfoList=new ArrayList<TbExamInfo>();
			TbExam exam=new TbExam();
			exam.setStudentId(user.getId());
			exam.setScore(examscore*2);
			exam.setUpdateTime(new Date());
			exam.setCreateTime(new Date());
			examService.insertSelective(exam);
			for(int i=1;i<=exam_size;i++) {
				TbExamInfo examInfo=new TbExamInfo();
				examInfo.setExamId(exam.getId());
				examInfo.setCreateTime(new Date());
				examInfo.setSort(i);
				examInfo.setMyanswer(request.getParameter("opt"+i));
				examInfo.setIsRight(Integer.valueOf(request.getParameter("score"+i)));
				examInfo.setQuestionId(Integer.valueOf(request.getParameter("question"+i)));
				examInfoList.add(examInfo);
			}
			examInfoService.batchInsert(examInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/f/score";
	}
	
	/**
	 * <p>Title: score</p>
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
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 答题详情</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/examInfo")
	public String examInfo(HttpServletRequest request,Model model, Integer reqPage, String examId,String isRight){
		try {
			//获取请求参数
			if (reqPage == null) {
				reqPage = 1;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			HttpSession e=request.getSession();
			FrontUser user=(FrontUser) e.getAttribute("frontUser");
			params.put("studentId", user.getId());
			if (!StringUtils.isEmpty(examId)) {
				params.put("examId", examId);
			}
			if (!StringUtils.isEmpty(isRight)) {
				params.put("isRight", isRight);
			}
			
			List<TbQuestion> questionList = questionService.findExamList(params);
			model.addAttribute("examQuestionList", questionList);
			model.addAttribute("examId", examId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/examInfo";
	}
	
	
	/**
	 * <p>Title: logout</p>
	 * <p>Description: 退出</p>
	 * @param model
	 * @param request
	 * @param frontUser
	 * @return String
	 */
	@RequestMapping(value = "logout")
	public String logout(Model model,HttpServletRequest request) {
		HttpSession e=request.getSession();
    	e.removeAttribute("frontUser");
		return "front/login";
	}
	
	/**
	 * <p>Title: exportData</p>
	 * <p>Description: 考试成绩导出</p>
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/exportData")
	public void exportData(HttpServletResponse response,Model model,String keyword,String studentId){
		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			if (!StringUtils.isEmpty(studentId)) {
				params.put("studentId", studentId);
			}
			
			List<TbExam> list = examService.findList(params);
			String title = "成绩表";
            String[] rowsName = new String[]{"序号","学号","姓名","成绩","考试时间"};
            List<Object[]>  dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < list.size(); i++) {
                TbExam exam = list.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i;
                objs[1] = exam.getStudentNo();
                objs[2] = exam.getName();
                objs[3] = exam.getScore();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(exam.getCreateTime());
                objs[4] = date;
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel("成绩",title, rowsName, dataList,response);
            ex.export();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
