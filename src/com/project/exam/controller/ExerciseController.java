package com.project.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.exam.model.TbOptions;
import com.project.exam.model.TbQuestion;
import com.project.exam.service.OptionsService;
import com.project.exam.service.QuestionService;
import com.project.utils.PaginationInfo;

/**
 * 
 * <p>ClassName: ExerciseController</p>
 * <p>Description: 练习题</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/exercise")
public class ExerciseController extends BaseController {
	static final Logger log = LoggerFactory.getLogger(ExerciseController.class);
	/**
	 * @Fields SPLITPAGE_SIZE : 用户管理分页，每页的数目
	 */
	public static final int SPLITPAGE_SIZE = 10;
	
	@Autowired
	QuestionService questionService;
	@Autowired
	OptionsService optionsService;
	
	/**
	 * 
	 * <p>Title: list</p>
	 * <p>Description: 练习题</p>
	 * @param model
	 * @param preId 上一题id
	 * @param subject 科目一或四
	 * @return String
	 */
	@RequestMapping("/doExercise")
	public String doExercise(Model model, Integer preId, Integer subject, Integer reqPage){
		log.info("[doExercise] do exercise  ... ");
		try {
			if(reqPage == null)
				reqPage = 1;
			TbQuestion question = this.questionService.selectNextBySubject(subject, preId);
			if(question == null) { //无数据时，从头开始
				question = this.questionService.selectNextBySubject(subject, null);
				reqPage = 1;
				preId = question.getId();
			}
			List<TbOptions> optionsList = this.optionsService.selectByQuestion(question.getId());
			model.addAttribute("optionsList", optionsList);
			model.addAttribute("question", question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("preId", preId);
		model.addAttribute("subject", subject);
		model.addAttribute("reqPage", reqPage);
		return "exercise/exercise";
	}
	
	/**
	 * 
	 * <p>Title: list</p>
	 * <p>Description: 练习题列表</p>
	 * @param model
	 * @param reqPage
	 * @param subject 科目二或三
	 * @return String
	 */
	@RequestMapping("/list")
	public String list(Model model, Integer reqPage, Integer subject){
		log.info("[list] show exercise list ... ");
		try {
			//获取请求参数
			if (reqPage == null) {
				reqPage = 1;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("subject", subject);
			PaginationInfo<TbQuestion> pageinfo = questionService.getPaginationData(params, reqPage, SPLITPAGE_SIZE);
			model.addAttribute("pageinfo", pageinfo);
			model.addAttribute("subject", subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exercise/list";
	}
}
