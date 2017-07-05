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
import com.chance.domain.ReportBangBang;
import com.chance.service.BangBangService;
import com.chance.service.ReportBangBangService;
import com.chance.service.impl.BusinessException;


@Controller
public class UncheckedReportBangBangController {
	private static final Logger logger = LoggerFactory.getLogger(UncheckedReportBangBangController.class);
	@Autowired
	private ReportBangBangService reportBangBangService;
	@Autowired
	private BangBangService bangBangService;
	
	/**
	 * 同时删除一条ReportBangBang和对应的BangBang
	 */
	@RequestMapping("/reportBangBang/unchecked/deleteBoth")
	public String deleteBoth(ModelMap model,@RequestParam("userid") int userid, 
			@RequestParam("bangBangid") int bangBangid,
			@RequestParam("reportBangBangid") int reportBangBangid,
			@RequestParam("deleteTime") long deleteTime,
			HttpSession session){
		
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			reportBangBangService.deleteReportBangBangAndBangBang(deleteTime, reportBangBangid, userid, reportBangBangid, admin);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportBangBang/success";
	}
	
	
	/**
	 * 删除一条ReportBangBang
	 */
	@RequestMapping("/reportBangBang/unchecked/delete")
	public String delete(ModelMap model,@RequestParam("reportBangBangid") int reportBangBangid, 
			@RequestParam("deleteTime") long deleteTime,
			HttpSession session){
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			reportBangBangService.delete(deleteTime, reportBangBangid, admin);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportBangBang/success";
	}
	
	/**
	 * 从服务获取最新的reportBangBang
	 */
	@RequestMapping("/reportBangBang/unchecked/checkLatest")
	public String checkLatest(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			int result = reportBangBangService.checkLatest();
			
			redirectAttributes.addFlashAttribute("msgFromcheckLatest", "成功获取到"+result+"条最新数据！");
			return "redirect:/reportBangBang/unchecked/get";	//获取最新的待处理的数据
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportBangBang/index";
		}
	}
	
	/**
	 * 查询 
	 * 但凡是跳转到listUnCheckedUI页面的，都要传递查询参数
	 * @return
	 */
	@RequestMapping("/reportBangBang/unchecked/query")
	public String query(@RequestParam("q_byUserId") int byUserId,
			ModelMap model,RedirectAttributes redirectAttributes){
		
		logger.info("[in query]:byUserId:"+byUserId);
		
		Map<String, Object> queries = new HashMap<String, Object>();
		queries.put("byUserId", byUserId);
		Pager<ReportBangBang> pager = new Pager<ReportBangBang>();
		pager.setQueries(queries);
		try {
			model.addAttribute("pager", reportBangBangService.getUncheckedByPager(pager));
			model.addAttribute("q_byUserId", byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportBangBang/index";
		}
		return "reportBangBang/listUnCheckedUI";
	}
	
	/**
	 * 查看被举报日志的详细情况
	 */
	@RequestMapping("/reportBangBang/unchecked/detail")
	public String  detail(@RequestParam("userid") int userid,@RequestParam("bangBangid") int bangBangid,
			@RequestParam("reportBangBangid") int reportBangBangid,@RequestParam("deleteTime") Long deleteTime, 
			ModelMap model){
		try {
			model.addAttribute("bangBang",bangBangService.get(userid, bangBangid));
			model.addAttribute("reportBangBangid", reportBangBangid);
			model.addAttribute("deleteTime", deleteTime);
			return "reportBangBang/detailUI";
			
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}
	}
	
	
	
	/**
	 * ReportBangBang首页
	 */
	@RequestMapping("/reportBangBang/index")
	public String index() {
		logger.info("[in index]...");
		return "reportBangBang/index";
	}
	
	
	
	/**
	 * 获取待处理的ReportBangBang
	 */
	@RequestMapping("/reportBangBang/unchecked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", reportBangBangService.getUncheckedByPager(new Pager<ReportBangBang>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportBangBang/index";
		}
		
		//获取了最新的数据，checkLatest传递提示信息过来
		if(model.get("msgFromcheckLatest") != null)
			model.addAttribute("tipMsg", model.get("msgFromcheckLatest"));	
		return "reportBangBang/listUnCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/reportBangBang/unchecked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		
		logger.info("[in first]: totalCount:"+totalCount+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, 1, totalCount,byUserId);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/reportBangBang/unchecked/next")
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
	@RequestMapping("/reportBangBang/unchecked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/reportBangBang/unchecked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/reportBangBang/unchecked/jump")
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
		
		Pager<ReportBangBang> pager = new Pager<ReportBangBang>();
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
			model.addAttribute("pager", reportBangBangService.getUncheckedByPager(pager));
			
			model.addAttribute("q_byUserId", q_byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportBangBang/index";
		}

		return "reportBangBang/listUnCheckedUI";
		
	}
}
