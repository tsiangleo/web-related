package com.chance.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportDiary;
import com.chance.service.ReportDiaryService;
import com.chance.service.impl.BusinessException;


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
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", reportDiaryService.getCheckedByPager(new Pager<ReportDiary>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportDiary/index";
		}
		
		return "reportDiary/listCheckedUI";
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
		
		Pager<ReportDiary> pager = new Pager<ReportDiary>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", reportDiaryService.getCheckedByPager(pager));
		
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportDiary/index";
		}

		return "reportDiary/listCheckedUI";
		
	}
}
