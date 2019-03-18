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

	public List<TbQuestion> getQuestions() {
		List<TbQuestion> list = new ArrayList<>();
		int count=mapper.getDataTotalNum(null);
		HashSet<Integer> set = new HashSet<>();
		randomSet(1, count, 50, set);
		list.addAll(mapper.getExamQuestions(set));
		return null;
	}
	
	/**
	 * 
	 * <p>Title: randomSet</p>
	 * <p>
	 * Description:
	 * 随机指定范围内N个不重复的数 
	 * 利用HashSet的特征，只能存放不同的值
	 * </p>
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n 随机数个数
	 * @param set  随机数结果集
	 */
	 public void randomSet(int min, int max, int n, HashSet<Integer> set) {  
		   if (n > (max - min + 1) || max < min) {
		       return;
		   }
		   for (int i = 0; i < n; i++) {
		       //调用Math.random()方法 
		       int num = (int) (Math.random() * (max - min)) + min;
		       set.add(num);//将不同的数存入HashSet中
		   }
		   int setSize = set.size();
		   //如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
		   if (setSize < n) {
			   randomSet(min, max, n - setSize, set);//递归
		   }
    }
}
