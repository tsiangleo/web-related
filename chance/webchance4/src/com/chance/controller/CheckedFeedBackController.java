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
import com.chance.domain.FeedBack;
import com.chance.service.FeedBackService;
import com.chance.service.impl.BusinessException;


@Controller
public class CheckedFeedBackController {
	private static final Logger logger = LoggerFactory.getLogger(CheckedFeedBackController.class);
	@Autowired
	private FeedBackService feedBackService;	
	
	/**
	 * 从本地数据库中删除一条已经处理过的FeedBack
	 */
	@RequestMapping("/feedBack/checked/delete")
	public String delete(ModelMap model,@RequestParam("feedBackid") int feedBackid){
		try {
			feedBackService.deleteChecked(feedBackid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "feedBack/success";
	}
	
	
	/**
	 * 获取已处理的FeedBack
	 */
	@RequestMapping("/feedBack/checked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", feedBackService.getCheckedByPager(new Pager<FeedBack>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/feedBack/index";
		}
		
		return "feedBack/listCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/feedBack/checked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount){
		
		logger.info("[in first]: totalCount:"+totalCount);
		return paging(model, redirectAttributes, 1, totalCount);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/feedBack/checked/next")
	public String next(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/feedBack/checked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/feedBack/checked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo);
		return paging(model, redirectAttributes, pageNo, totalCount);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/feedBack/checked/jump")
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
		
		Pager<FeedBack> pager = new Pager<FeedBack>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", feedBackService.getCheckedByPager(pager));
		
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/feedBack/index";
		}

		return "feedBack/listCheckedUI";
		
	}
}
