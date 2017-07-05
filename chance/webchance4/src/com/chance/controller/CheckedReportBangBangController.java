package com.chance.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.domain.Pager;
import com.chance.domain.ReportBangBang;
import com.chance.service.ReportBangBangService;
import com.chance.service.impl.BusinessException;


@Controller
public class CheckedReportBangBangController {
	private static final Logger logger = LoggerFactory.getLogger(CheckedReportBangBangController.class);
	@Autowired
	private ReportBangBangService reportBangBangService;	
	
	/**
	 * 从本地数据库中删除一条已经处理过的ReportBangBang
	 */
	@RequestMapping("/reportBangBang/checked/delete")
	public String delete(ModelMap model,@RequestParam("reportBangBangid") int reportBangBangid){
		try {
			reportBangBangService.deleteChecked(reportBangBangid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportBangBang/success";
	}
	
	
	/**
	 * 获取已处理的ReportBangBang
	 */
	@RequestMapping("/reportBangBang/checked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", reportBangBangService.getCheckedByPager(new Pager<ReportBangBang>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportBangBang/index";
		}
		
		return "reportBangBang/listCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/reportBangBang/checked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount){
		
		logger.info("[in first]: totalCount:"+totalCount);
		return paging(model, redirectAttributes, 1, totalCount);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/reportBangBang/checked/next")
	public String next(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/reportBangBang/checked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/reportBangBang/checked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/reportBangBang/checked/jump")
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
		
		Pager<ReportBangBang> pager = new Pager<ReportBangBang>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", reportBangBangService.getCheckedByPager(pager));
		
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportBangBang/index";
		}

		return "reportBangBang/listCheckedUI";
		
	}
}
