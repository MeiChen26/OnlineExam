package com.project.exam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.dao.TbOptionsMapper;
import com.project.exam.dao.TbQuestionMapper;
import com.project.exam.model.TbOptions;
import com.project.exam.model.TbQuestion;
import com.project.utils.StringUtil;

/**
 * 
 * <p>ClassName: QuestionService</p>
 * <p>Description: 题库</p>
 * @Author  
 * @Date: 
 */
@Service
public class QuestionService extends PaginationBaseService<TbQuestionMapper, TbQuestion, Integer>{

	@Autowired
	private TbOptionsMapper optionsMapper;
	
	/**
	 * 
	 * <p>Title: save</p>
	 * <p>Description: 题库保存</p>
	 * @param question void
	 */
	@Transactional
	public void save(TbQuestion question) {
		int i = 0;
		String answer = "";
		List<TbOptions> optionsList = question.getOptions();
		if(optionsList != null) {
			for(TbOptions op : optionsList) {
				if(StringUtil.isNotEmpty(op.getContent())) {
					if(op.getRig())
						answer += ","+op.getOpt();
				}
			}
		}
		if(StringUtil.isNotEmpty(answer))
			question.setAnswer(answer.substring(1));
		if(question.getId() == null){//Insert
			question.setCreateTime(new Date());
			i = mapper.insertSelective(question);
		}else{//update
			i = mapper.updateByPrimaryKeySelective(question);
		}
		//添加选项
		if(i > 0) {
			//先删除旧数据
			this.optionsMapper.deleteByQuestionId(question.getId());
			//循环添加新数据
			if(optionsList != null) {
				for(TbOptions op : optionsList) {
					if(StringUtil.isNotEmpty(op.getContent())) {
						op.setCreateTime(new Date());
						op.setQuestionId(question.getId());
						this.optionsMapper.insertSelective(op);
					}
				}
			}
		}
	}

	/**
	 * 
	 * <p>Title: selectNextBySubject</p>
	 * <p>Description: 获取下一题</p>
	 * @param subject
	 * @param preId
	 * @return TbQuestion
	 */
	public TbQuestion selectNextBySubject(Integer subject, Integer preId) {
		return this.mapper.selectNextBySubject(subject, preId);
	}
	
	/**
	 * 
	 * <p>Title: delete</p>
	 * <p>Description: 删除</p>
	 * @param id void
	 */
	public void delete(Integer questionId) {
		if(questionId != null) {
			this.mapper.deleteByPrimaryKey(questionId);
			this.optionsMapper.deleteByQuestionId(questionId);
		}
	}

	public List<TbQuestion> getQuestion() {
		List<TbQuestion> list = new ArrayList<>();
		
		return list;
	}
}
