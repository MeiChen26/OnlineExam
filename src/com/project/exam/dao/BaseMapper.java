package com.project.exam.dao;

/**
 * @ClassName: BaseMapper
 * @Description: DAO基类
 * 			该基类仅适用于库表带有主键的类型，对于没有主键的不应该使用该基类。
 * @Author  suking
 * @Create Date: 2018年2月12日 上午10:53:28
 * @param <T>
 */
public interface BaseMapper<T, K> {
	
	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除一条数据
	 * @param id
	 * @return int 成功删除数据的条数
	 */
	int deleteByPrimaryKey(K id);

    /**
     * @Title: insert
     * @Description: 插入一条数据
     * @param record 
     * @return int 成功添加数据的条数
     */
    int insert(T record);

    /**
     * @Title: insertSelective
     * @Description: 选择性插入一条数据，空字段将不插入
     * @param record
     * @return int 成功添加数据条数
     */
    int insertSelective(T record);

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据主键获取一条数据
     * @param id 主键ID
     * @return T
     */
    T selectByPrimaryKey(K id);

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 按主键更新数据，空字段将不会更新
     * @param record
     * @return int 成功更改数据条数
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * @Title: updateByPrimaryKey
     * @Description: 按主键更新数据，空字段的值将更新为空
     * @param record
     * @return int 成功更改数据条数
     */
    int updateByPrimaryKey(T record);
	
}
