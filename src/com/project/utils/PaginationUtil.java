package com.project.utils;
/**
 * <p>Title: CommonPagination.java</p>
 * <p>Description: </p>
 * <p>Copyright (C) 2016-2018, All Rights Reserved</p>
 * <p>Company: 山东华夏高科信息股份有限公司</p>
 * @Author: wuxs
 * @Date: 2018年4月29日
 * @Version V1.0
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>ClassName: CommonPagination</p>
 * <p>Description: </p>
 * @Author  
 * @Date: 
 */
public class PaginationUtil {

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
	 * @Fields totalNum : 数据的总数
	 */
	private int page = 0;

	/**
	 * @Fields resultList : 结果集
	 */
	private List<Map<String, Object>> resultList;
	
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

	public PaginationUtil() {
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
	
	public int getTotalPageNum() {
		return totalPageNum;
	}

	public List<Map<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
