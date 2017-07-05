package com.chance.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportDiary;
import com.chance.domain.Pager.OrderType;
import com.chance.service.ReportDiaryService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;


@Controller
public class CheckedReportDiaryController {
	private static final Logger logger = LoggerFactory.getLogger(CheckedReportDiaryController.class);
	@Autowired
	private ReportDiaryService reportDiaryService;	
	
	/**
	 * 从本地数据库中删除一条已经处理过的ReportDiary
	 */
	@RequestMapping("/reportDiary/checked/delete")
	public String delete(ModelMap model,@RequestParam("reportDiaryid") int reportDiaryid){
		try {
			reportDiaryService.deleteChecked(reportDiaryid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportDiary/success";
	}
	
	
	/**
	 * 获取已处理的ReportDiary
	 */
	@RequestMapping("/reportDiary/checked/get")
	@ResponseBody
	public Pager2<ReportDiary>  get(ModelMap model,RedirectAttributes redirectAttributes){
//		ListData<ReportDiary> result = new ListData<ReportDiary>();
//		try {
//			Pager<ReportDiary> pager = reportDiaryService.getCheckedByPager(new Pager<ReportDiary>());
//			result.setTotal(pager.getTotalCount());
//			result.setRows(pager.getDataList());
//			return 	result;
//		} catch (BusinessException e) {
//			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
//			logger.info(e.getMessage());
//			return result;
//		}
		
		try {
			Pager<ReportDiary> pager = reportDiaryService.getCheckedByPager(new Pager<ReportDiary>());
			
			Pager2<ReportDiary> pager2 = new Pager2<ReportDiary>();
			pager2.setTotal(pager.getTotalCount());
			pager2.setRows(pager.getDataList());
			pager2.setCurrentPage(pager.getCurrentPage());
			pager2.setOrderBy(pager.getOrderBy());
			pager2.setPageCount(pager.getPageCount());
			pager2.setPageSize(pager.getPageSize());
			pager2.setPropertyName(pager.getPropertyName());
			pager2.setPropertyValue(pager.getPropertyValue());
			pager2.setQueries(pager.getQueries());
			return 	pager2;
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return null;
		}
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/reportDiary/checked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount){
		
		logger.info("[in first]: totalCount:"+totalCount);
		return paging(model, redirectAttributes, 1, totalCount);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/reportDiary/checked/next")
	public String next(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/reportDiary/checked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/reportDiary/checked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/reportDiary/checked/jump")
	public String jump(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in jump]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}

	/**
	 * 分页工具方法
	 * 但凡是跳转到listCheckedUI页面的，都要传递查询参数
	 */
	private String paging(ModelMap model,
			RedirectAttributes redirectAttributes, int pageNo,
			int totalCount) {
//		
//		Pager<ReportDiary> pager = new Pager<ReportDiary>();
//		pager.setCurrentPage(pageNo);
//		pager.setTotalCount(totalCount);
//		try {
//			logger.info("[pager in paging]:"+pager);
//			model.addAttribute("pager", reportDiaryService.getCheckedByPager(pager));
//		
//		} catch (BusinessException e) {
//			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
//			logger.info(e.getMessage());
//			return "redirect:/reportDiary/index";
//		}

		return "reportDiary/listCheckedUI";
		
	}
}

class ListData<T>{
	private Integer total;
	private List<T> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}


 class Pager2<T> {
	
	// 排序方式
	public enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private int currentPage = 1;// 当前页码
	private int pageSize = MonitorConstants.PAGE_SIZE;// 每页记录数
//	private int totalCount = 0;// 总记录数
	private int pageCount = 0;// 总页数

	private String orderBy = "time";// 排序字段
	private OrderType orderType = OrderType.desc;// 排序方式
//	private List<T> dataList;// 数据List
	
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
	
//	public Integer getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(Integer totalCount) {
//		this.totalCount = totalCount;
//		calculatePageCount();//每次设置新的记录数，就自动设置页数
//	}

//	private void calculatePageCount() {
//		pageCount = totalCount / pageSize;
//		if (totalCount % pageSize > 0) {
//			pageCount ++;
//		}
//	}
	
	private void calculatePageCount() {
		pageCount = total / pageSize;
		if (total % pageSize > 0) {
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

//	public List<T> getDataList() {
//		return dataList;
//	}
//
//	public void setDataList(List<T> dataList) {
//		this.dataList = dataList;
//	}

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

	
	
	
	//-----------------
	
	
	private Integer total;
	private List<T> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
		calculatePageCount();//每次设置新的记录数，就自动设置页数
	}


}
