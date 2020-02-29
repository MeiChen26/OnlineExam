package com.project.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.utils.PaginationInfo;

/**
 * @ClassName: PaginationBaseMapper
 * @Description: DAO 分页 基类
 * 				该基类可以让开发人员方便的实现分页功能，
 * 				注意继承该类需 XXXX Mapper.xml中添加对应的实现查询方法
 * @Author  suking
 * @Create Date: 2018年2月12日 上午11:58:07
 * @param <T> 表对应Model类型
 * @param <K> 表主键对应类型
 */
public interface PaginationBaseMapper<T, K> extends BaseMapper<T, K> {
	
	/**
	 * @Title: getDataTotalNum
	 * @Description: 获取符合条件数据的总条数
	 * @param params
	 * @return int
	 */
	int getDataTotalNum(@Param("pageinfo")PaginationInfo<T> pageinfo);
	
	/**
	 * @Title: getDataList
	 * @Description: 获取分页结果集，查询条件需与getDataTotalNum一致
	 * 			注意：该方法无法自动将结果集封装到PaginationInfo<T>中，需手动封装。
	 * @param pageinfo
	 * @return List<T>
	 */
	List<T> getDataList(@Param("pageinfo")PaginationInfo<T> pageinfo);
}
