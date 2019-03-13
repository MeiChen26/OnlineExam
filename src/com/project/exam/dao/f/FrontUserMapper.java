package com.project.exam.dao.f;

import com.project.exam.dao.PaginationBaseMapper;
import com.project.exam.model.f.FrontUser;

public interface FrontUserMapper extends PaginationBaseMapper<FrontUser, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(FrontUser record);

    int insertSelective(FrontUser record);

    FrontUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FrontUser record);

    int updateByPrimaryKey(FrontUser record);
    
    FrontUser getByCondition(FrontUser frontUser);
}