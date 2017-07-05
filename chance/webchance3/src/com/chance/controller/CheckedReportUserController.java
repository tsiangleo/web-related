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
import com.chance.domain.ReportUser;
import com.chance.service.ReportUserService;
import com.chance.service.impl.BusinessException;


@Controller
public class CheckedReportUserController {
	private static final Logger logger = LoggerFactory.getLogger(CheckedReportUserController.class);
	@Autowired
	private ReportUserService reportUserService;	
	
	/**
	 * 从本地数据库中删除一条已经处理过的ReportUser
	 */
	@RequestMapping("/reportUser/checked/delete")
	public String delete(ModelMap model,@RequestParam("reportUserid") int reportUserid){
		try {
			reportUserService.deleteChecked(reportUserid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportUser/success";
	}
	
	
	/**
	 * 获取已处理的ReportUser
	 */
	@RequestMapping("/reportUser/checked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", reportUserService.getCheckedByPager(new Pager<ReportUser>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportUser/index";
		}
		
		return "reportUser/listCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/reportUser/checked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount){
		
		logger.info("[in first]: totalCount:"+totalCount);
		return paging(model, redirectAttributes, 1, totalCount);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/reportUser/checked/next")
	public String next(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/reportUser/checked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/reportUser/checked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/reportUser/checked/jump")
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
		
		Pager<ReportUser> pager = new Pager<ReportUser>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", reportUserService.getCheckedByPager(pager));
		
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportUser/index";
		}

		return "reportUser/listCheckedUI";
		
	}
}
