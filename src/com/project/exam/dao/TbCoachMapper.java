package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.TbCoach;

public interface TbCoachMapper extends PaginationBaseMapper<TbCoach, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(TbCoach record);

    int insertSelective(TbCoach record);

    TbCoach selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCoach record);

    int updateByPrimaryKey(TbCoach record);

    /**
     * 
     * <p>Title: selectAll</p>
     * <p>Description: 获取所有教练</p>
     * @return List<TbCoach>
     */
	List<TbCoach> selectAll();
}