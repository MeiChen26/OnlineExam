package com.project.exam.service.f;


import org.springframework.stereotype.Service;

import com.project.exam.dao.f.FrontUserMapper;
import com.project.exam.model.f.FrontUser;
import com.project.exam.service.PaginationBaseService;
@Service
public class FrontUserService extends PaginationBaseService<FrontUserMapper, FrontUser, Integer>{

	public FrontUser getByCondition(FrontUser frontUser) {
		return mapper.getByCondition(frontUser);
	}
}
