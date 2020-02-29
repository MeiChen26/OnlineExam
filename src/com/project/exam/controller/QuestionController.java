package com.project.exam.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.exam.model.TbOptions;
import com.project.exam.model.TbQuestion;
import com.project.exam.service.OptionsService;
import com.project.exam.service.QuestionService;
import com.project.utils.PaginationInfo;

/**
 * 
 * <p>ClassName: CoachController</p>
 * <p>Description: 试题管理</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {
	static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	public static final int SPLITPAGE_SIZE = 10;
	
	@Autowired
	QuestionService questionService;
	@Autowired
	OptionsService optionsService;
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 试题列表</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字，模糊查询用户名
	 * @return String
	 */
	@RequestMapping("/list")
	public String list(Model model, Integer reqPage, Integer subject){
		log.info("[list] show question manage list ... ");
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
		return "question/list";
	}
	
	/**
	 * <p>Title: addOrUpdate</p>
	 * <p>Description: 添加或修改试题信息页面</p>
	 * @param id 试题ID,新增试题时，该参数为空，非空时表示修改
	 * @return String
	 */
	@RequestMapping("addOrUpdate")
	public String addOrUpdate(Model model, Integer id, Integer subject){
		log.info("[addOrUpdate] add or update user form prepare controller .");
		model.addAttribute("subject", subject);
		if(id != null){//addUser
			TbQuestion question = questionService.selectByPrimaryKey(id);
			model.addAttribute("question", question);
			if(question != null) {
				List<TbOptions> newOptionsList = new ArrayList<>();
				TbOptions os = null;
				for(int i = 0; i < 4; i++) {
					String c = "";
					os = new TbOptions();
					switch (i) {
						case 0: c="A";break;
						case 1: c="B";break;
						case 2: c="C";break;
						case 3: c="D";break;
					}
					os.setOpt(c);
					newOptionsList.add(os);
				}
				List<TbOptions> optionsList = optionsService.selectByQuestion(question.getId());
				for(int i = 0; i < newOptionsList.size(); i++) {
					TbOptions opt = newOptionsList.get(i);
					for(TbOptions opt1 : optionsList) {
						if(opt.getOpt().equals(opt1.getOpt())) {
							newOptionsList.get(i).setContent(opt1.getContent());
							newOptionsList.get(i).setId(opt1.getId());
							newOptionsList.get(i).setQuestionId(opt1.getQuestionId());
							newOptionsList.get(i).setRig(opt1.getRig());
						}
					}
				}
				model.addAttribute("optionsList", newOptionsList);
			}
			return "question/edit";
		}else{
			return "question/add";
		}
	}
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: 保存试题信息，插入或更新</p>
	 * @param question 试题信息
	 * @return String
	 */
	@RequestMapping("save")
	public String save(HttpServletRequest request, TbQuestion question, Integer subject, MultipartFile multipartFile){
		log.info("[save] save question info (insert or update)");
		if(question == null){
			log.error("[save] save question info error, user is null...");
		}
		try {
			if(multipartFile!=null && multipartFile.getSize() > 0) {
		        
	        	File f = null;
	        	try {
	        		System.out.println(System.getProperty("root")+"--------root");
	        		String path=System.getProperty("root")+"/assets/upload/question";
	        		String ofilename = multipartFile.getOriginalFilename();
	        		String ext = ofilename.substring(ofilename.lastIndexOf(".")+1, ofilename.length());
	        		String nowtime = System.currentTimeMillis()+"";
	        		String filename = nowtime+"."+ext;
	                f = new File(path,filename);
	                //判断路径是否存在，如果不存在就创建一个
	                if (!f.getParentFile().exists()) { 
	                    f.getParentFile().mkdirs();
	                }
	        	    multipartFile.transferTo(f);
	        	    question.setAttachment("assets/upload/question/"+filename);
	        	} catch (HttpException e) {
	        	    e.printStackTrace();
	        	} catch (IOException e) {
	        	    e.printStackTrace();
	        	}
			}
			questionService.save(question);
			log.info("[save] save question info success...");
		} catch (Exception e) {
			log.error("[save] save question info error...");
			e.printStackTrace();
		}
		
		return "redirect:/question/list?subject="+subject;
	}
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 根据ID删除试题</p>
	 * @param redirectAttributes
	 * @param id
	 * @return String
	 */
	@RequestMapping("delete")
	public String delete(RedirectAttributes redirectAttributes, Integer id, Integer subject){
		if(id == null){
			log.error("[delete] delete question error, id is null");
		}else{
			try {
				this.questionService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/question/list?subject="+subject;
	}
}
