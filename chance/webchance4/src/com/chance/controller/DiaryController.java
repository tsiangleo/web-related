package com.chance.controller;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.domain.Pager;
import com.chance.domain.Diary;
import com.chance.service.DiaryService;
import com.chance.service.impl.BusinessException;


@Controller
public class DiaryController {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	@Autowired
	private DiaryService diaryService;	
	
	/**
	 * 删除单条Diary
	 * <br>向服务获取发送删除单条Diary请求
	 */
	@RequestMapping("/diary/delete")
	public String delete(@RequestParam("diaryid")Integer diaryid,
			@RequestParam("userid")Integer userid,ModelMap model){
		logger.info("[in delete]:diaryid:"+diaryid+",userid:"+userid);
		try {
			diaryService.delete(userid,diaryid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "diary/success";
	}
	
	/**
	 * 推送Diary
	 */
	@RequestMapping("/diary/pushDiary")
	public String pushDiary(@RequestParam("pushDiaryDesc")String pushDiaryDesc,
			@RequestParam("pushDiaryPwd")String pushDiaryPwd,
			@RequestParam("pushDiaryType")Integer pushDiaryType,
			@RequestParam("diaryid")Integer diaryid,
			@RequestParam("userid")Integer userid,ModelMap model){
		
		String encodeDesc = null;
		logger.info("[in pushDiary]:pushDiaryDesc:"+pushDiaryDesc+",pushDiaryType:"+pushDiaryType
				+",diaryid:"+diaryid+",userid:"+userid);
		
		try {
			encodeDesc = java.net.URLEncoder.encode(pushDiaryDesc,"utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("字符编码错误 !"+e.getMessage());
			model.addAttribute("tipMsg", "输入的内容格式有误！请重新输入！");
			return "goback";	//返回上一页
		}
		try {
			diaryService.pushDiary(userid, diaryid, pushDiaryType, encodeDesc, pushDiaryPwd);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "推送成功！");
		return "diary/success";

	}

	
	/**
	 * 显示单条Diary的详细内容
	 * <br>从服务获取单条Diary信息
	 */
	@RequestMapping("/diary/detail")
	public String detail(@RequestParam("userId") Integer userId,
			@RequestParam("diaryId") Integer diaryId,ModelMap model){
		logger.info("[in detail]:userId:"+userId+",diaryId:"+diaryId);
		try {
			model.addAttribute("diary",diaryService.get(userId, diaryId));
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		return "diary/detailUI";
	}
	
	
	
	/**
	 * 查询 
	 * 但凡是跳转到listUI页面的，都要传递查询参数
	 * @return
	 */
	@RequestMapping("/diary/query")
	public String query(HttpSession session,@RequestParam("q_username") String username,
			ModelMap model,RedirectAttributes redirectAttributes){
		logger.info("[in query]:username:"+username);
		
		try {
			model.addAttribute("pager", diaryService.getByPager(session,new Pager<Diary>(), username));
			model.addAttribute("q_username", username);	//查询参数传递到listUnCheckedUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/diary/index";
		}
		return "diary/listUI";
	}
	
	
	/**
	 * Diary首页
	 */
	@RequestMapping("/diary/index")
	public String index() {
		logger.info("[in index]...");
		return "diary/listUI";
	}
	
	
	/**
	 * 首页
	 */
	@RequestMapping("/diary/first")
	public String first(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username){
		logger.info("[in first]: totalCount:"+totalCount+",username:"+username);
		return paging(session,model, redirectAttributes, 1, totalCount,username);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/diary/next")
	public String next(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/diary/pre")
	public String pre(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/diary/last")
	public String last(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/diary/jump")
	public String jump(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username){
		logger.info("[in jump]: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username);
		
	}

	/**
	 * 分页工具方法
	 * 但凡是跳转到listUnCheckedUI页面的，都要传递查询参数
	 */
	private String paging(HttpSession session,ModelMap model,
			RedirectAttributes redirectAttributes, int pageNo,
			int totalCount,String username) {
		
		Pager<Diary> pager = new Pager<Diary>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", diaryService.getByPager(session,pager, username));
			
			model.addAttribute("q_username", username);	//查询参数传递到listUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/diary/index";
		}
		return "diary/listUI";
		
	}
}
