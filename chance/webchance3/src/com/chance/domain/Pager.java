package com.chance.domain;

import java.util.List;
import java.util.Map;

import com.chance.util.MonitorConstants;

/**
 * 分页
 */

public class Pager<T> {
	
	// 排序方式
	public enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private int currentPage = 1;// 当前页码
	private int pageSize = MonitorConstants.PAGE_SIZE;// 每页记录数
	private int totalCount = 0;// 总记录数
	private int pageCount = 0;// 总页数

	private String orderBy = "time";// 排序字段
	private OrderType orderType = OrderType.desc;// 排序方式
	private List<T> dataList;// 数据List
	
	private Map<String, Object> queries; //查询条件，key为属性名，value为对应的关键字.
	private String propertyName;// 限制属性名
	private Object propertyValue;// 限制值
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int current) {
		if (current < 1) {
			this.currentPage = 1;
			return;
		}

		this.currentPage = current;
		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
		
		calculatePageCount();//每次设置新的页面大小，就自动设置页数
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		calculatePageCount();//每次设置新的记录数，就自动设置页数
	}

	private void calculatePageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
	}
	
	public Integer getPageCount() {
		return pageCount;
	}

	//总页数不能手动设置，应该由总记录数自动决定。
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Map<String, Object> getQueries() {
		return queries;
	}

	public void setQueries(Map<String, Object> queries) {
		this.queries = queries;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString() {
		return "Pager [currentPage=" + currentPage + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", pageCount=" + pageCount
				+ ", orderBy=" + orderBy + ", orderType=" + orderType
				+ ", dataList=" + dataList + ", queries=" + queries
				+ ", propertyName=" + propertyName + ", propertyValue="
				+ propertyValue + "]";
	}


}