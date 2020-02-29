package com.project.exam.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.dao.BaseMapper;

/**
 * @ClassName: BaseService
 * @Description: Service类基类，集成基本的增、删、改、查操作
 * 				建议主要的操作表带有主键的继承该类型，对于没有主键的可以不使用该基类
 * @Author  suking
 * @Create Date: 2018年3月17日 上午9:48:26
 * @param <M> DAO Mapper类型
 * @param <T> 表对应Model类型
 */
public class BaseService <M extends BaseMapper<T, K>, T, K> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected M mapper;
	
	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除一条数据
	 * @param id
	 * @return int 成功删除数据的条数
	 */
	public int deleteByPrimaryKey(K id){
		return mapper.deleteByPrimaryKey(id);
	}

    /**
     * @Title: insert
     * @Description: 插入一条数据
     * @param record 
     * @return int 成功添加数据的条数
     */
	public int insert(T record){
    	return mapper.insert(record);
    }

    /**
     * @Title: insertSelective
     * @Description: 选择性插入一条数据，空字段将不插入
     * @param record
     * @return int 成功添加数据条数
     */
	public int insertSelective(T record){
    	return mapper.insertSelective(record);
    }

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据主键获取一条数据
     * @param id 主键ID
     * @return T
     */
	public T selectByPrimaryKey(K id){
    	return mapper.selectByPrimaryKey(id);
    }

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 按主键更新数据，空字段将不会更新
     * @param record
     * @return int 成功更改数据条数
     */
	public int updateByPrimaryKeySelective(T record){
    	return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @Title: updateByPrimaryKey
     * @Description: 按主键更新数据，空字段的值将更新为空
     * @param record
     * @return int 成功更改数据条数
     */
	public int updateByPrimaryKey(T record){
    	return mapper.updateByPrimaryKey(record);
    }
	

}