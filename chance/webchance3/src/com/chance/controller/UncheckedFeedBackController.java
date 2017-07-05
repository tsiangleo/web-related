package com.chance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.chance.domain.FeedBack;
import com.chance.service.FeedBackService;
import com.chance.service.impl.BusinessException;


@Controller
public class UncheckedFeedBackController {
	private static final Logger logger = LoggerFactory.getLogger(UncheckedFeedBackController.class);
	@Autowired
	private FeedBackService feedBackService;
	
	/**
	 * 删除一条FeedBack
	 */
	@RequestMapping("/feedBack/unchecked/delete")
	public String delete(ModelMap model,@RequestParam("feedBackid") int feedBackid, 
			@RequestParam("deleteTime") long deleteTime,
			HttpSession session){
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			feedBackService.delete(deleteTime, feedBackid, admin);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "feedBack/success";
	}
	
	/**
	 * 从服务获取最新的feedBack
	 */
	@RequestMapping("/feedBack/unchecked/checkLatest")
	public String checkLatest(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			int result = feedBackService.checkLatest();
			
			redirectAttributes.addFlashAttribute("msgFromcheckLatest", "成功获取到"+result+"条最新数据！");
			return "redirect:/feedBack/unchecked/get";	//获取最新的待处理的数据
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/feedBack/index";
		}
	}
	
	/**
	 * FeedBack首页
	 */
	@RequestMapping("/feedBack/index")
	public String index() {
		logger.info("[in index]...");
		return "feedBack/index";
	}
	
	
	
	/**
	 * 获取待处理的FeedBack
	 */
	@RequestMapping("/feedBack/unchecked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", feedBackService.getUncheckedByPager(new Pager<FeedBack>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/feedBack/index";
		}
		
		//获取了最新的数据，checkLatest传递提示信息过来
		if(model.get("msgFromcheckLatest") != null)
			model.addAttribute("tipMsg", model.get("msgFromcheckLatest"));	
		return "feedBack/listUnCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/feedBack/unchecked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		
		logger.info("[in first]: totalCount:"+totalCount+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, 1, totalCount,byUserId);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/feedBack/unchecked/next")
	public String next(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/feedBack/unchecked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/feedBack/unchecked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/feedBack/unchecked/jump")
	public String jump(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in jump]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}

	/**
	 * 分页工具方法
	 * 但凡是跳转到listUnCheckedUI页面的，都要传递查询参数
	 */
	private String paging(ModelMap model,
			RedirectAttributes redirectAttributes, int pageNo,
			int totalCount,Integer q_byUserId) {
		
		Pager<FeedBack> pager = new Pager<FeedBack>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		if(q_byUserId != null){
			//查询条件
			Map<String, Object> queries = new HashMap<String, Object>();
			queries.put("byUserId", q_byUserId);
			pager.setQueries(queries);
		}
		
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", feedBackService.getUncheckedByPager(pager));
			
			model.addAttribute("q_byUserId", q_byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/feedBack/index";
		}

		return "feedBack/listUnCheckedUI";
		
	}
}
