package com.project.exam.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.exam.model.TbCoach;
import com.project.exam.service.CoachService;
import com.project.utils.PaginationInfo;

/**
 * 
 * <p>ClassName: CoachController</p>
 * <p>Description: 教练管理</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/coach")
public class CoachController extends BaseController {
	static final Logger log = LoggerFactory.getLogger(CoachController.class);
	/**
	 * @Fields SPLITPAGE_SIZE : 用户管理分页，每页的数目
	 */
	public static final int SPLITPAGE_SIZE = 10;
	
	@Autowired
	CoachService coachService;
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 教练管理列表</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字，模糊查询用户名
	 * @return String
	 */
	@RequestMapping("/list")
	public String list(Model model, Integer reqPage, String keyword){
		log.info("[list] show coach manage list ... ");
		try {
			//获取请求参数
			if (reqPage == null) {
				reqPage = 1;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			
			PaginationInfo<TbCoach> pageinfo = coachService.getPaginationData(params, reqPage, SPLITPAGE_SIZE);
			model.addAttribute("pageinfo", pageinfo);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "coach/list";
	}
	
	/**
	 * <p>Title: addOrUpdate</p>
	 * <p>Description: 添加或修改教练信息页面</p>
	 * @param id 教练ID,新增教练时，该参数为空，非空时表示修改
	 * @return String
	 */
	@RequestMapping("addOrUpdate")
	public String addOrUpdate(Model model, Integer id){
		log.info("[addOrUpdate] add or update user form prepare controller .");
		
		if(id != null){//addUser
			TbCoach coach = coachService.selectByPrimaryKey(id);
			model.addAttribute("coach", coach);
			return "coach/edit";
		}else{
			return "coach/add";
		}
		
	}
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: 保存教练信息，插入或更新</p>
	 * @param coach 教练信息
	 * @return String
	 */
	@RequestMapping("save")
	public String save(Model model, RedirectAttributes redirectAttributes, TbCoach coach){
		log.info("[save] save coach info (insert or update)");
		if(coach == null){
			log.error("[save] save coach info error, user is null...");
		}
		try {
			if(coach.getId() == null){//Insert data into database.
				coach.setCreateTime(new Date());
				coachService.insertSelective(coach);
			}else{//update user info
				coachService.updateByPrimaryKeySelective(coach);
			}
			log.info("[save] save coach info success...");
		} catch (Exception e) {
			log.error("[save] save coach info error...");
			e.printStackTrace();
		}
		
		return "redirect:/coach/list";
	}
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 根据ID删除教练</p>
	 * @param redirectAttributes
	 * @param id
	 * @return String
	 */
	@RequestMapping("delete")
	public String delete(RedirectAttributes redirectAttributes, Integer id){
		if(id == null){
			log.error("[delete] delete coach error, id is null");
		}else{
			try {
				this.coachService.deleteByPrimaryKey(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/coach/list";
	}
}
