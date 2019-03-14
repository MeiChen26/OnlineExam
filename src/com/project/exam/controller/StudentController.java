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

import com.project.exam.model.SysUser;
import com.project.exam.model.f.FrontUser;
import com.project.exam.service.CoachService;
import com.project.exam.service.SysUserService;
import com.project.exam.service.f.FrontUserService;
import com.project.utils.EncryptUtil;
import com.project.utils.PaginationInfo;
import com.project.utils.StringUtil;

/**
 * 
 * <p>ClassName: StudentController</p>
 * <p>Description: 学生管理</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
	static final Logger log = LoggerFactory.getLogger(StudentController.class);
	/**
	 * @Fields SPLITPAGE_SIZE : 学生管理分页，每页的数目
	 */
	public static final int SPLITPAGE_SIZE = 10;
	
	@Autowired
	FrontUserService frontUserService;
	
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 学员管理列表</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字，模糊查询用户名
	 * @return String
	 */
	@RequestMapping("/list")
	public String list(Model model, Integer reqPage, String keyword){
		log.info("[list] show student manage list ... ");
		try {
			//获取请求参数
			if (reqPage == null) {
				reqPage = 1;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			
			PaginationInfo<FrontUser> pageinfo = frontUserService.getPaginationData(params, reqPage, SPLITPAGE_SIZE);
			model.addAttribute("pageinfo", pageinfo);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "student/list";
	}
	
	/**
	 * <p>Title: addOrUpdate</p>
	 * <p>Description: 添加或修改学生信息页面</p>
	 * @param id 学生ID,新增学生时，该参数为空，非空时表示修改
	 * @return String
	 */
	@RequestMapping("addOrUpdate")
	public String addOrUpdate(Model model, Integer id){
		log.info("[addOrUpdate] add or update student form prepare controller .");
		if(id != null){//addUser
			FrontUser student = frontUserService.selectByPrimaryKey(id);
			model.addAttribute("student", student);
			return "student/edit";
		}else{
			return "student/add";
		}
		
	}
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: 保存学生信息，插入或更新</p>
	 * @param user 学生信息
	 * @return String
	 */
	@RequestMapping("save")
	public String save(Model model, RedirectAttributes redirectAttributes, FrontUser student){
		log.info("[save] save student info (insert or update)");
		if(student == null){
			log.error("[save] save student info error, user is null...");
		}
		try {
			if(student.getId() == null){//Insert data into database.
				student.setCreateTime(new Date());
				student.setDeleted(false);
				student.setPassword(EncryptUtil.entryptPassword("123456"));
				frontUserService.insertSelective(student);
			}else{//update user info
				
				frontUserService.updateByPrimaryKeySelective(student);
			}
			log.info("[save] save student info success...");
		} catch (Exception e) {
			log.error("[save] save student info error...");
			e.printStackTrace();
		}
		
		return "redirect:/student/list";
	}
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 根据ID删除学员</p>
	 * @param redirectAttributes
	 * @param id
	 * @return String
	 */
	@RequestMapping("delete")
	public String delete(RedirectAttributes redirectAttributes, Integer id){
		if(id == null){
			log.error("[delete] delete student error, id is null");
		}else{
			try {
				FrontUser user = new FrontUser();
				user.setId(id);
				user.setDeleted(true);
			this.frontUserService.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/student/list";
	}

}
