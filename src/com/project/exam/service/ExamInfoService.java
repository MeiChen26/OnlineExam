package com.project.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.exam.dao.TbExamInfoMapper;
import com.project.exam.model.TbExamInfo;
@Service
public class ExamInfoService extends PaginationBaseService<TbExamInfoMapper, TbExamInfo, Integer>{

	public void batchInsert(List<TbExamInfo> examInfoList) {
		mapper.batchInsert(examInfoList);
		
	}

}
