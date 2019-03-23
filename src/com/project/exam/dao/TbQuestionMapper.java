package com.project.exam.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.project.exam.model.TbQuestion;

public interface TbQuestionMapper extends PaginationBaseMapper<TbQuestion, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(TbQuestion record);

    int insertSelective(TbQuestion record);

    TbQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbQuestion record);

    int updateByPrimaryKeyWithBLOBs(TbQuestion record);

    int updateByPrimaryKey(TbQuestion record);
    
    /**
     * 
     * <p>Title: selectNextBySubject</p>
     * <p>Description: 获取下一题</p>
     * @param subject
     * @param preId
     * @return TbQuestion
     */
    TbQuestion selectNextBySubject(@Param("subject") Integer subject, @Param("preId") Integer preId);

	List<TbQuestion> getExamQuestions(@Param("list")HashSet<Integer> set);

	int getTotalNum();

	List<TbQuestion> findAll();

	List<TbQuestion> findExamList(Map<String, Object> params);

}