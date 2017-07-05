package com.chance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chance.service.ReportDiaryService;


@Controller
public class ReportDiaryStatisticsController {
	private static final Logger logger = LoggerFactory.getLogger(ReportDiaryStatisticsController.class);
	@Autowired
	private ReportDiaryService reportDiaryService;
	
	
	/**
	 * 被举报用户排行榜
	 */
	@RequestMapping("/reportDiary/statistics/reporteeTopList")
	public ModelAndView reporteeTopList(){
		logger.info("[in reporteeTopList]...");
		ModelAndView mv = new ModelAndView();
		mv.addObject("reporteeTopList", reportDiaryService.reporteeTopList());
		mv.setViewName("reportDiary/statisticsListUI");
		return mv;
	}
	
	/**
	 * 查询某个用户的各篇日志被举报的次数。
	 */
	@RequestMapping("/reportDiary/statistics/reportee/diaryList")
	public ModelAndView diaryList(@RequestParam("byUserId") int byUserId){
		ModelAndView mv = new ModelAndView();
		mv.addObject("diaryList", reportDiaryService.diaryList(byUserId));
		mv.addObject("byUserId", byUserId);
		mv.setViewName("reportDiary/reporteeDiaryListUI");
		return mv;
	}
	
	/**
	 * 查询某个用户的某篇日志都有哪些用户举报。
	 * @param byUserId 被举报用户id
	 * @param diaryId 被举报日志id
	 * @return  返回一个map，key为举报者的id，value为被举报次数。
	 */
	@RequestMapping("/reportDiary/statistics/reporterList")
	public ModelAndView reporterList(@RequestParam("byUserId") int byUserId,
			@RequestParam("diaryId") int diaryId){
		ModelAndView mv = new ModelAndView();
		mv.addObject("reporterList", reportDiaryService.reporterList(byUserId,diaryId));
		mv.setViewName("reportDiary/reporterListUI");
		return mv;
	}
}
