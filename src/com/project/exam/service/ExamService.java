package com.project.exam.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.exam.dao.TbExamMapper;
import com.project.exam.model.TbExam;

/**
 * 
 * <p>ClassName: ExamService</p>
 * <p>Description: 考试成绩service</p>
 * @Author  
 * @Date: 
 */
@Service
public class ExamService extends PaginationBaseService<TbExamMapper, TbExam, Integer>{

	public TbExam selectByStuId(Integer studentId) {
		return this.mapper.selectByStuId(studentId);
	}

	public List<TbExam> findList(Map<String, Object> params) {
		return mapper.findList(params);
	}


}
