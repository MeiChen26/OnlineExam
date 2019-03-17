package com.project.exam.service;


import org.springframework.stereotype.Service;

import com.project.exam.dao.FrontUserMapper;
import com.project.exam.model.FrontUser;
@Service
public class FrontUserService extends PaginationBaseService<FrontUserMapper, FrontUser, Integer>{

	public FrontUser getByCondition(FrontUser frontUser) {
		return mapper.getByCondition(frontUser);
	}
}
