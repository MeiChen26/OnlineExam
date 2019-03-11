package com.project.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PaginationInfo
 * @Description: 分页工具类
 * @Author  
 * @Create Date: 
 * @param <T>  表对应Model类型
 */
public class PaginationInfo<T> {

	/**
	 * @Fields totalNum : 数据的总数
	 */
	private int totalNum;
	/**
	 * @Fields paginationSize: 分页时，每页的数据条数，默认10条
	 */
	private int paginationSize;
	/**
	 * @Fields totalPageNum : 分页的总页数
	 */
	private int totalPageNum;
	/**
	 * @Fields currentPage : 请求页的页码
	 */
	private int currentPage = 1;

	/**
	 * @Fields resultList : 结果集
	 */
	private List<T> resultList;
	
	private Map<String, Object> params;
	
	public void putParam(String key, Object value) {
		params.put(key, value);
	}
	
	public void putParams(Map<String, Object> params) {
		this.params.putAll(params);
	}
	
	public Map<String, Object> getParams(){
		return params;
	}

	public PaginationInfo() {
		params = new HashMap<String, Object>();
		paginationSize = 10;//默认分页条数10
		currentPage = 1;//默认当前页1
	}
	
	/**
	 * @Title: init @Description: 初始化数据参数
	 */
	private void init() {
		if (totalNum == 0) {// 数据总数为0时至少有一页
			totalPageNum = 1;
		} else if (totalNum % paginationSize == 0) {// 数据刚好整数页
			totalPageNum = totalNum / paginationSize;
		} else {// 数据不为整数页时，多出几条数据也应该有一页
			totalPageNum = totalNum / paginationSize + 1;
		}
		if (currentPage > totalPageNum) {
			this.currentPage = totalPageNum;
		}
	}
	
	/**
	 * @Title: getStartIndex
	 * @Description: 获取数据查询的开始索引
	 * @throws
	 */
	public int getStartIndex() {
		if (totalNum == 0) {
			return 0;
		}
		return (currentPage - 1) * paginationSize;
	}

	/**
	 * @Title: getEndIndex
	 * @Description: 获取数据查询结束索引
	 * @return int
	 */
	public int getEndIndex() {
		if (totalNum == 0) {
			return 0;
		} else if (currentPage == totalPageNum) {
			return totalNum;
		}
		return (currentPage * paginationSize);
	}
	
	/**
	 * @Title: getDataSize
	 * @Description: 需要查询数据的数量(一般来说MySql中limit 第二个参数可以直接使用paginationSize)
	 * @return int
	 */
	public int getDataSize(){
		if (totalNum == 0) {
			return 0;
		} else if (currentPage == totalPageNum) {
			return totalNum - ((currentPage - 1) * paginationSize);
		}
		return this.paginationSize;
	}

	public int getTotalNo() {
		return totalNum;
	}

	public void setTotalNo(int totalNo) {
		this.totalNum = totalNo;
		init();//重新计算分页数据信息
	}

	public int getPaginationSize() {
		return paginationSize;
	}

	public void setPaginationSize(int paginationSize) {
		this.paginationSize = paginationSize;
		init();//每当设置每页分页条数时就需要重新初始化分页信息。
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

}
