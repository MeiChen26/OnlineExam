package com.project.exam.service;

import java.util.List;
import java.util.Map;

import com.project.exam.dao.PaginationBaseMapper;
import com.project.utils.PaginationInfo;

/**
 * @ClassName: PaginationBaseService
 * @Description: 分页Service基类，需要对应的Mapper继承PaginationBaseMapper<T, K>
 * @Author  suking
 * @Create Date: 2018年3月17日 上午9:48:26
 *
 * @param <M> DAO Mapper类型
 * @param <T> 表对应Model类型
 * @param <K> 表主键对应的类型
 */
public class PaginationBaseService<M extends PaginationBaseMapper<T, K>, T, K> extends BaseService<M, T, K> {
	
	/**
	 * @Title: getPaginationData
	 * @Description: 获取分页数据，params为对应的查询参数，默认每页显示10条数据
	 * @param params 分页查询参数
	 * @param reqPage 请求页码
	 * @return PaginationInfo<T> 分页信息，含查询参数和查询结果集
	 */
	public PaginationInfo<T> getPaginationData(Map<String, Object> params, Integer reqPage){
		return getPaginationData(params, reqPage, 10);
	}
	
	/**
	 * @Title: getPaginationData
	 * @Description: 获取分页数据，params为对应的查询参数
	 * @param params 分页查询参数
	 * @param reqPage 请求页码
	 * @param paginationSize 每页显示数据的条数
	 * @return PaginationInfo<T> 分页数据集，包含3类数据，1.分页信息，请求页，数据总数，总页数等；2.查询参数；3.查询到的结果集
	 */
	public PaginationInfo<T> getPaginationData(Map<String, Object> params, Integer reqPage, Integer paginationSize){
		if(reqPage == null || reqPage < 0) {
			reqPage = 1;
		}
		if(paginationSize == null || paginationSize <= 0) {
			throw new IllegalArgumentException("paginationSize is null or invalidate.");
		}
		PaginationInfo<T> pageinfo = new PaginationInfo<T>();
		pageinfo.setPaginationSize(paginationSize);//获取分页数量
		pageinfo.setCurrentPage(reqPage);//设置当前页
		pageinfo.putParams(params);//将参数封装到分页信息中，需在查询之前将参数封装进去（必需）。
		
		//获取符合条件的分页的总数
		int number = mapper.getDataTotalNum(pageinfo);
		pageinfo.setTotalNo(number);//该操作必须有，mapper无法将数据信息自动封装到PaginationInfo<T> 对象中
		
		if(number > 0) {
			List<T> sysUserList = mapper.getDataList(pageinfo);
			pageinfo.setResultList(sysUserList);//该操作必须有，mapper无法将数据信息自动封装到PaginationInfo<T> 对象中
		}
		return pageinfo;
	}
}
