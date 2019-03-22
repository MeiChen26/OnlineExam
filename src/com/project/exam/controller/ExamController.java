package com.project.exam.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.exam.model.TbExam;
import com.project.exam.service.ExamService;
import com.project.exam.service.SysUserService;
import com.project.utils.ExportExcel;
import com.project.utils.PaginationInfo;

/**
 * 
 * <p>ClassName: ExamController</p>
 * <p>Description: 考试成绩管理</p>
 * @Author  
 * @Date: 
 */
@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {
	static final Logger log = LoggerFactory.getLogger(ExamController.class);
	/**
	 * @Fields SPLITPAGE_SIZE : 用户管理分页，每页的数目
	 */
	public static final int SPLITPAGE_SIZE = 10;
	
	@Autowired
	ExamService examService;
	@Autowired
	SysUserService userService;
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 考试成绩管理列表</p>
	 * @param reqPage 请求页，页码
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/list")
	public String list(Model model, Integer reqPage, String keyword){
		log.info("[list] show exam manage list ... ");
		try {
			//获取请求参数
			if (reqPage == null) {
				reqPage = 1;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			
			PaginationInfo<TbExam> pageinfo = examService.getPaginationData(params, reqPage, SPLITPAGE_SIZE);
			model.addAttribute("pageinfo", pageinfo);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exam/list";
	}
	
	/**
	 * <p>Title: addOrUpdate</p>
	 * <p>Description: 添加或修改考试成绩信息页面</p>
	 * @param id 考试成绩ID,新增考试成绩时，该参数为空，非空时表示修改
	 * @return String
	 */
	@RequestMapping("addOrUpdate")
	public String addOrUpdate(Model model, Integer id){
		log.info("[addOrUpdate] add or update user form prepare controller .");
		model.addAttribute("stuList", this.userService.selectAll());
		if(id != null){//addUser
			TbExam exam = examService.selectByPrimaryKey(id);
			model.addAttribute("exam", exam);
		}else{
		}
		return "exam/add";
		
	}
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: 保存考试成绩信息，插入或更新</p>
	 * @param exam 考试成绩信息
	 * @return String
	 */
	@RequestMapping("save")
	public String save(Model model, RedirectAttributes redirectAttributes, TbExam exam){
		log.info("[save] save exam info (insert or update)");
		if(exam == null){
			log.error("[save] save exam info error, user is null...");
		}
		try {
			if(exam.getId() == null){//Insert data into database.
				exam.setCreateTime(new Date());
				examService.insertSelective(exam);
			}else{//update user info
				exam.setUpdateTime(new Date());
				examService.updateByPrimaryKeySelective(exam);
			}
			log.info("[save] save exam info success...");
		} catch (Exception e) {
			log.error("[save] save exam info error...");
			e.printStackTrace();
		}
		
		return "redirect:/exam/list";
	}
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 根据ID删除考试成绩</p>
	 * @param redirectAttributes
	 * @param id
	 * @return String
	 */
	@RequestMapping("delete")
	public String delete(RedirectAttributes redirectAttributes, Integer id){
		if(id == null){
			log.error("[delete] delete exam error, id is null");
		}else{
			try {
				this.examService.deleteByPrimaryKey(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/exam/list";
	}
	
	@RequestMapping(value = "getExamByStuId")
	@ResponseBody
	public void check(HttpServletResponse response, Integer stuId) {
		Map<String, Object> result = new HashMap<>();
		try {
			TbExam exam = this.examService.selectByStuId(stuId);
			result.put("exam", exam);
			responseWriterObject(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			responseWriterFail(response, "获取失败");
		}
	}
	
	/**
	 * 
	 * <p>Title: myexam</p>
	 * <p>Description: 我的成绩</p>
	 * @param model
	 * @param id
	 * @return String
	 */
	@RequestMapping("myexam")
	public String myexam(Model model){
		log.info("[myexam] show student`s exam .");
		try {
			Integer studentId = getCurrentUserId();
			model.addAttribute("exam", this.examService.selectByStuId(studentId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exam/my";
	}
	
	/**
	 * <p>Title: list</p>
	 * <p>Description: 考试成绩到处</p>
	 * @param keyword 查询关键字
	 * @return String
	 */
	@RequestMapping("/exportData")
	@ResponseBody
	public String exportData(HttpServletResponse response,Model model,String keyword){
		log.info("[list] export exam... ");
		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			
			List<TbExam> list = examService.findList(params);
			String title = "scoreData";
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
            ExportExcel ex = new ExportExcel(title, rowsName, dataList,response);
            ex.export();
		} catch (Exception e) {
			e.printStackTrace();
			return "{result:fail}";
		}
		return "{result:success}";
	}
	
	
}
