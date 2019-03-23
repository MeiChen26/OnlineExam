package com.project.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.exam.model.TbExamInfo;

public interface TbExamInfoMapper extends PaginationBaseMapper<TbExamInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(TbExamInfo record);

    int insertSelective(TbExamInfo record);

    TbExamInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbExamInfo record);

    int updateByPrimaryKey(TbExamInfo record);
    
    void batchInsert(@Param("examInfoList")List<TbExamInfo> examInfoList);
}