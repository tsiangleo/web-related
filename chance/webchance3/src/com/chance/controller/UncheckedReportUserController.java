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
import com.chance.domain.ReportUser;
import com.chance.service.ReportUserService;
import com.chance.service.impl.BusinessException;


@Controller
public class UncheckedReportUserController {
	private static final Logger logger = LoggerFactory.getLogger(UncheckedReportUserController.class);
	@Autowired
	private ReportUserService reportUserService;
	
	/**
	 * 删除一条ReportUser
	 * 这个操作会产生两个动作：将本地的该条ReportUser改为Checked状态，同时向服务器发送删除这条ReportUser的消息。
	 */
	@RequestMapping("/reportUser/unchecked/delete")
	public String delete(ModelMap model,@RequestParam("reportUserid") int reportUserid, 
			@RequestParam("deleteTime") long deleteTime,
			HttpSession session){
		logger.info("[in UncheckedReportUserController.delete] reportUserid:"+reportUserid+",deleteTime："+deleteTime);
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			reportUserService.delete(deleteTime, reportUserid, admin);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportUser/success";
	}
	
	/**
	 * 从服务获取最新的reportUser
	 */
	@RequestMapping("/reportUser/unchecked/checkLatest")
	public String checkLatest(ModelMap model,RedirectAttributes redirectAttributes){
		logger.info("[in UncheckedReportUserController.checkLatest] ");
		try {
			int result = reportUserService.checkLatest();
			
			redirectAttributes.addFlashAttribute("msgFromcheckLatest", "成功获取到"+result+"条最新数据！");
			return "redirect:/reportUser/unchecked/get";	//获取最新的待处理的数据
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportUser/index";
		}
	}
	
	/**
	 * 查询 
	 * 但凡是跳转到listUnCheckedUI页面的，都要传递查询参数
	 * @return
	 */
	@RequestMapping("/reportUser/unchecked/query")
	public String query(@RequestParam("q_byUserId") int byUserId,
			ModelMap model,RedirectAttributes redirectAttributes){
		
		logger.info("[in query]:byUserId:"+byUserId);
		
		Map<String, Object> queries = new HashMap<String, Object>();
//		queries.put("byUserName", value);
		queries.put("byUserId", byUserId);
		Pager<ReportUser> pager = new Pager<ReportUser>();
		pager.setQueries(queries);
		try {
			model.addAttribute("pager", reportUserService.getUncheckedByPager(pager));
			model.addAttribute("q_byUserId", byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportUser/index";
		}
		return "reportUser/listUnCheckedUI";
	}
	
	/**
	 * 查看被举报日志的详细情况
	 */
	@RequestMapping("/reportUser/unchecked/detail")
	public String  detail(@RequestParam("id") int id, ModelMap model){
		try {
			model.addAttribute("reportUser", reportUserService.getDetail(id));
			return "reportUser/detailUI";
			
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}
	}
	
	
	
	/**
	 * ReportUser首页
	 */
	@RequestMapping("/reportUser/index")
	public String index() {
		logger.info("[in index]...");
		return "reportUser/index";
	}
	
	
	
	/**
	 * 获取待处理的ReportUser
	 */
	@RequestMapping("/reportUser/unchecked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		try {
			model.addAttribute("pager", reportUserService.getUncheckedByPager(new Pager<ReportUser>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportUser/index";
		}
		
		//获取了最新的数据，checkLatest传递提示信息过来
		if(model.get("msgFromcheckLatest") != null)
			model.addAttribute("tipMsg", model.get("msgFromcheckLatest"));	
		return "reportUser/listUnCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/reportUser/unchecked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		
		logger.info("[in first]: totalCount:"+totalCount+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, 1, totalCount,byUserId);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/reportUser/unchecked/next")
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
	@RequestMapping("/reportUser/unchecked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/reportUser/unchecked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/reportUser/unchecked/jump")
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
		
		Pager<ReportUser> pager = new Pager<ReportUser>();
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
			model.addAttribute("pager", reportUserService.getUncheckedByPager(pager));
			
			model.addAttribute("q_byUserId", q_byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportUser/index";
		}

		return "reportUser/listUnCheckedUI";
		
	}
}
