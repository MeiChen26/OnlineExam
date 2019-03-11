package com.project.exam.service;

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


}
