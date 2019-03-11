package com.project.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.exam.dao.TbOptionsMapper;
import com.project.exam.model.TbOptions;

/**
 * 
 * <p>ClassName: OptionsService</p>
 * <p>Description: 题库选项</p>
 * @Author  
 * @Date: 
 */
@Service
public class OptionsService extends PaginationBaseService<TbOptionsMapper, TbOptions, Integer>{

	/**
	 * 
	 * <p>Title: selectByQuestion</p>
	 * <p>Description: 查询某题下的所有选项</p>
	 * @param questionId
	 * @return List<TbOptions>
	 */
	public List<TbOptions> selectByQuestion(Integer questionId) {
		return this.mapper.selectByQuestion(questionId);
	}

}
