package com.project.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.exam.model.TbOptions;

public interface TbOptionsMapper extends PaginationBaseMapper<TbOptions, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(TbOptions record);

    int insertSelective(TbOptions record);

    TbOptions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbOptions record);

    int updateByPrimaryKey(TbOptions record);

	List<TbOptions> selectByQuestion(@Param("questionId") Integer questionId);
	
	void deleteByQuestionId(@Param("questionId") Integer questionId);
}