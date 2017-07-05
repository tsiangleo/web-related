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
import com.chance.domain.ReportDiary;
import com.chance.service.DiaryService;
import com.chance.service.ReportDiaryService;
import com.chance.service.impl.BusinessException;


@Controller
public class UncheckedReportDiaryController {
	private static final Logger logger = LoggerFactory.getLogger(UncheckedReportDiaryController.class);
	@Autowired
	private ReportDiaryService reportDiaryService;
	@Autowired
	private DiaryService diaryService;
	
	/**
	 * 同时删除一条ReportDiary和对应的Diary
	 */
	@RequestMapping("/reportDiary/unchecked/deleteBoth")
	public String deleteBoth(ModelMap model,@RequestParam("userid") int userid, 
			@RequestParam("diaryid") int diaryid,
			@RequestParam("reportDiaryid") int reportDiaryid,
			@RequestParam("deleteTime") long deleteTime,
			HttpSession session){
		logger.info("[in UncheckedReportDiaryController.deleteBoth] diaryid:"+diaryid
				+",reportDiaryid:"+reportDiaryid+",deleteTime:"+deleteTime);
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			reportDiaryService.deleteReportDiaryAndDiary(deleteTime, reportDiaryid, userid, reportDiaryid, admin);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportDiary/success";
	}
	
	
	/**
	 * 删除一条ReportDiary
	 */
	@RequestMapping("/reportDiary/unchecked/delete")
	public String delete(HttpSession session,ModelMap model,@RequestParam("reportDiaryid") int reportDiaryid, 
			@RequestParam("deleteTime") long deleteTime){
		logger.info("[in UncheckedReportDiaryController.deleteBoth] reportDiaryid:"+reportDiaryid+",deleteTime:"+deleteTime);
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			reportDiaryService.delete(deleteTime, reportDiaryid, admin);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "reportDiary/success";
	}
	
	/**
	 * 从服务获取最新的reportDiary
	 */
	@RequestMapping("/reportDiary/unchecked/checkLatest")
	public String checkLatest(ModelMap model,RedirectAttributes redirectAttributes){
		logger.info("[in UncheckedReportDiaryController.checkLatest] ");
		try {
			int result = reportDiaryService.checkLatest();
			
			redirectAttributes.addFlashAttribute("msgFromcheckLatest", "成功获取到"+result+"条最新数据！");
			return "redirect:/reportDiary/unchecked/get";	//获取最新的待处理的数据
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportDiary/index";
		}
	}
	
	/**
	 * 查询 
	 * 但凡是跳转到listUnCheckedUI页面的，都要传递查询参数
	 * @return
	 */
	@RequestMapping("/reportDiary/unchecked/query")
	public String query(@RequestParam("q_byUserId") int byUserId,
			ModelMap model,RedirectAttributes redirectAttributes){
		
		logger.info("[in query]:byUserId:"+byUserId);
		
		Map<String, Object> queries = new HashMap<String, Object>();
//		queries.put("byUserName", value);
		queries.put("byUserId", byUserId);
		Pager<ReportDiary> pager = new Pager<ReportDiary>();
		pager.setQueries(queries);
		try {
			model.addAttribute("pager", reportDiaryService.getUncheckedByPager(pager));
			model.addAttribute("q_byUserId", byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportDiary/index";
		}
		return "reportDiary/listUnCheckedUI";
	}
	
	/**
	 * 查看被举报日志的详细情况
	 */
	@RequestMapping("/reportDiary/unchecked/detail")
	public String  detail(@RequestParam("userid") int userid,@RequestParam("diaryid") int diaryid,
			@RequestParam("reportDiaryid") int reportDiaryid,@RequestParam("deleteTime") Long deleteTime, 
			ModelMap model){
		logger.info("[in UncheckedReportDiaryController.detail] diaryid:"+diaryid+
				",reportDiaryid:"+reportDiaryid+",deleteTime:"+deleteTime);
		try {
			model.addAttribute("diary",diaryService.get(userid, diaryid));
			model.addAttribute("reportDiaryid", reportDiaryid);
			model.addAttribute("deleteTime", deleteTime);
			return "reportDiary/detailUI";
			
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}
	}
	
	
	
	/**
	 * ReportDiary首页
	 */
	@RequestMapping("/reportDiary/index")
	public String index() {
		logger.info("[in index]...");
		return "reportDiary/index";
	}
	
	
	
	/**
	 * 获取待处理的ReportDiary
	 */
	@RequestMapping("/reportDiary/unchecked/get")
	public String  get(ModelMap model,RedirectAttributes redirectAttributes){
		logger.info("[in UncheckedReportDiaryController.get] ");
		try {
			model.addAttribute("pager", reportDiaryService.getUncheckedByPager(new Pager<ReportDiary>()));	
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportDiary/index";
		}
		
		//获取了最新的数据，checkLatest传递提示信息过来
		if(model.get("msgFromcheckLatest") != null)
			model.addAttribute("tipMsg", model.get("msgFromcheckLatest"));	
		return "reportDiary/listUnCheckedUI";
	}
	

	
	/**
	 * 首页
	 */
	@RequestMapping("/reportDiary/unchecked/first")
	public String first(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		
		logger.info("[in first]: totalCount:"+totalCount+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, 1, totalCount,byUserId);
		
	}
	
	/**
	 * 下一页
	 * 为什么byUserId为Integer？参考：
	 * http://825635381.iteye.com/blog/2196911
	 */
	@RequestMapping("/reportDiary/unchecked/next")
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
	@RequestMapping("/reportDiary/unchecked/pre")
	public String pre(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/reportDiary/unchecked/last")
	public String last(ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam(value="q_byUserId",required=false) Integer byUserId){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo+",byUserId:"+byUserId);
		return paging(model, redirectAttributes, pageNo, totalCount,byUserId);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/reportDiary/unchecked/jump")
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
		
		Pager<ReportDiary> pager = new Pager<ReportDiary>();
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
			model.addAttribute("pager", reportDiaryService.getUncheckedByPager(pager));
			
			model.addAttribute("q_byUserId", q_byUserId);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/reportDiary/index";
		}

		return "reportDiary/listUnCheckedUI";
		
	}
	
	
//	/**
//	 * 获取待处理的ReportDiary v2
//	 */
//	@RequestMapping("getUncheckedv2")
//	public String  getUncheckedv2(RedirectAttributes redirectAttributes){
//		try {
//			redirectAttributes.addFlashAttribute("pager", reportDiaryService.getUncheckedByPager(new Pager<ReportDiary>()));
//		} catch (BusinessException e) {
//			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());//错误提示
//			logger.info(e.getMessage());
//			return "redirect:/reportDiary/index";
//		}
//		return "redirect:/reportDiary/listUI";	//间接跳转到listUI页面显示数据，可以设置为listUIUnchecked
//	}
//	
//	/**
//	 * 列表显示页面 结合 getUncheckedv2
//	 * @return
//	 */
//	@RequestMapping("/listUI")
//	public String loginUI(){	
//		return "reportDiary/listUI";
//		
//	}
	
}
