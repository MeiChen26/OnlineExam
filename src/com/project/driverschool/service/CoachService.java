package com.project.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.exam.dao.TbCoachMapper;
import com.project.exam.model.TbCoach;

/**
 * 
 * <p>ClassName: CoachService</p>
 * <p>Description: 教练service</p>
 * @Author  
 * @Date: 
 */
@Service
public class CoachService extends PaginationBaseService<TbCoachMapper, TbCoach, Integer>{

	public List<TbCoach> selectAll() {
		return this.mapper.selectAll();
	}

}
