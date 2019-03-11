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
import com.project.exam.service.CoachService;
import com.project.exam.service.SysUserService;
import com.project.utils.EncryptUtil;
import com.project.utils.PaginationInfo;
import com.project.utils.StringUtil;

/**
 * 
 * <p>ClassName: StudentController</p>
 * <p>Description: 学员管理</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
	static final Logger log = LoggerFactory.getLogger(StudentController.class);
	/**
	 * @Fields SPLITPAGE_SIZE : 用户管理分页，每页的数目
	 */
	public static final int SPLITPAGE_SIZE = 10;
	
	@Autowired
	SysUserService userService;
	@Autowired
	CoachService coachService;
	
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
			
			PaginationInfo<SysUser> pageinfo = userService.getPaginationData(params, reqPage, SPLITPAGE_SIZE);
			model.addAttribute("pageinfo", pageinfo);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "student/list";
	}
	
	/**
	 * <p>Title: addOrUpdate</p>
	 * <p>Description: 添加或修改学员信息页面</p>
	 * @param id 学员ID,新增学员时，该参数为空，非空时表示修改
	 * @return String
	 */
	@RequestMapping("addOrUpdate")
	public String addOrUpdate(Model model, Integer id){
		log.info("[addOrUpdate] add or update student form prepare controller .");
		model.addAttribute("coachList", coachService.selectAll());
		if(id != null){//addUser
			SysUser student = userService.selectByPrimaryKey(id);
			model.addAttribute("student", student);
			return "student/edit";
		}else{
			return "student/add";
		}
		
	}
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: 保存学员信息，插入或更新</p>
	 * @param user 学员信息
	 * @return String
	 */
	@RequestMapping("save")
	public String save(Model model, RedirectAttributes redirectAttributes, SysUser user){
		log.info("[save] save student info (insert or update)");
		if(user == null){
			log.error("[save] save student info error, user is null...");
		}
		try {
			if(user.getId() == null){//Insert data into database.
				user.setCreateTime(new Date());
				user.setDeleted(false);
				user.setUserName(user.getPhone());
				user.setPassword(EncryptUtil.entryptPassword("123456"));
				user.setType(2);
				userService.saveUser(user);
			}else{//update user info
				if(StringUtils.isNotBlank(user.getPhone()))
					user.setUserName(user.getPhone());
				userService.updateUser(user);
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
				SysUser user = new SysUser();
				user.setId(id);
				user.setDeleted(true);
				user.setUpdateTime(new Date());
				this.userService.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/student/list";
	}
	
	@RequestMapping("mycoach")
	public String mycoach(Model model){
		log.info("[mycoach] show student`s exam .");
		try {
			SysUser student = getCurrentUser();
			if(student.getCoachId() != null)
				model.addAttribute("coach", this.coachService.selectByPrimaryKey(student.getCoachId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "student/mycoach";
	}
	
	/**
	 * 
	 * <p>Title: updatePwd</p>
	 * <p>Description: 修改个人的密码</p>
	 * @param model
	 * @param newpwd 新密码
	 * @param oldpwd 原密码
	 * @param type 1:页面跳转；2：保存
	 * @return String
	 */
	@RequestMapping("updatePwd")
	public String updatePwd(Model model, Integer type, String newpwd, String oldpwd) {
		log.info("[updatePwd] update my password ...");
		try {
			SysUser currentUser = getCurrentUser();
			if(type == null)
				type = 1;
			if(type == 2) {
				if(StringUtil.isEmpty(newpwd)) {
					model.addAttribute("error", "请输入新密码");
				} else if(StringUtil.isEmpty(oldpwd)) {
					model.addAttribute("error", "请输入密码");
				} else {
					if(!EncryptUtil.validatePassword(oldpwd, currentUser.getPassword())) { //输入的原密码不正确
						model.addAttribute("error", "输入的原密码不正确");
					} else {
						currentUser.setPassword(EncryptUtil.entryptPassword(newpwd));
						this.userService.updateByPrimaryKeySelective(currentUser);
						model.addAttribute("error", "密码修改成功");
					}
				}
			}
			model.addAttribute("oldpwd", oldpwd);
			model.addAttribute("newpwd", newpwd);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("[updatePwd] update my password error...");
		}
		return "student/updatePwd";
	}
}
