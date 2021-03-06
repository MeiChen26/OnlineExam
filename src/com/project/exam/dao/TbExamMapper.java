package com.project.exam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.project.exam.model.TbExam;

public interface TbExamMapper extends PaginationBaseMapper<TbExam, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(TbExam record);

    int insertSelective(TbExam record);

    TbExam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbExam record);

    int updateByPrimaryKey(TbExam record);

	TbExam selectByStuId(@Param("studentId")Integer studentId);

	List<TbExam> findList(Map<String, Object> params);
}