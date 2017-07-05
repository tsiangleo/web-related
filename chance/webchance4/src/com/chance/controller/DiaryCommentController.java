package com.chance.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.domain.DiaryComment;
import com.chance.domain.Pager;
import com.chance.service.DiaryCommentService;
import com.chance.service.impl.BusinessException;


@Controller
public class DiaryCommentController {
	private static final Logger logger = LoggerFactory.getLogger(DiaryCommentController.class);
	@Autowired
	private DiaryCommentService diaryCommentService;	
	
	/**
	 * 删除单条DiaryComment
	 */
	@RequestMapping("/diaryComment/delete")
	public String delete(@RequestParam("userid") Integer userid,
			@RequestParam("diaryid") Integer diaryid,
			@RequestParam("commentid") Integer commentid,ModelMap model){
		logger.info("[in delete]:userId:"+userid+",diaryId:"+diaryid+",commentid:"+commentid);
		
		try {
			diaryCommentService.delete(userid, diaryid, commentid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "删除成功！");
		return "diaryComment/success";
	}
	
	
	/**
	 * 显示单条DiaryComment的详细内容
	 */
	@RequestMapping("/diaryComment/detail")
	public String detail(@RequestParam("userid") Integer userid,
			@RequestParam("diaryid") Integer diaryid,
			@RequestParam("commentid") Integer commentid,ModelMap model){
		
		logger.info("[in detail]:userId:"+userid+",diaryId:"+diaryid+",commentid:"+commentid);
		try {
			model.addAttribute("diaryComment",diaryCommentService.get(userid, diaryid, commentid));
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		return "diaryComment/detailUI";
	}
	
	/**
	 * 查询 
	 * 但凡是跳转到listUI页面的，都要传递查询参数
	 * @return
	 */
	@RequestMapping("/diaryComment/query")
	public String query(HttpSession session,@RequestParam("q_username") String username,@RequestParam("q_diaryid") Integer diaryid,
			ModelMap model,RedirectAttributes redirectAttributes){
		logger.info("[in query]:username:"+username);
		
		try {
			model.addAttribute("pager", diaryCommentService.getByPager(session,new Pager<DiaryComment>(), username,diaryid));
			model.addAttribute("q_username", username);	//查询参数传递到listUI页面
			model.addAttribute("q_diaryid", diaryid);	//查询参数传递到listUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/diaryComment/index";
		}
		return "diaryComment/listUI";
	}
	
	
	/**
	 * Diary首页
	 */
	@RequestMapping("/diaryComment/index")
	public String index() {
		logger.info("[in index]...");
		return "diaryComment/listUI";
	}
	
	
	/**
	 * 首页
	 */
	@RequestMapping("/diaryComment/first")
	public String first(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username,
			@RequestParam("q_diaryid") Integer diaryid){
		
		logger.info("[in first]: totalCount:"+totalCount+",username:"+username+",diaryid:"+diaryid);
		return paging(session,model, redirectAttributes, 1, totalCount,username,diaryid);
		
	}
	
	/**
	 * 下一页
	 */
	@RequestMapping("/diaryComment/next")
	public String next(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,
			@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username,
			@RequestParam("q_diaryid") Integer diaryid){
		logger.info("in next: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username+",diaryid:"+diaryid);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username,diaryid);
		
	}
	
	/**
	 * 上一页
	 */
	@RequestMapping("/diaryComment/pre")
	public String pre(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username,
			@RequestParam("q_diaryid") Integer diaryid){
		logger.info("[in pre]: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username+",diaryid:"+diaryid);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username,diaryid);
		
	}
	
	/**
	 * 末页
	 */
	@RequestMapping("/diaryComment/last")
	public String last(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username,
			@RequestParam("q_diaryid") Integer diaryid){
		logger.info("[in last]: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username+",diaryid:"+diaryid);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username,diaryid);
		
	}
	
	/**
	 * 跳转任意页
	 */
	@RequestMapping("/diaryComment/jump")
	public String jump(HttpSession session,ModelMap model,RedirectAttributes redirectAttributes,
			@RequestParam("pageNo")int pageNo,@RequestParam("totalCount")int totalCount,
			@RequestParam("q_username") String username,
			@RequestParam("q_diaryid") Integer diaryid){
		logger.info("[in jump]: totalCount:"+totalCount+",pageNo:"+pageNo+",username:"+username+",diaryid:"+diaryid);
		return paging(session,model, redirectAttributes, pageNo, totalCount,username,diaryid);
		
	}

	/**
	 * 分页工具方法
	 * 但凡是跳转到listUnCheckedUI页面的，都要传递查询参数
	 */
	private String paging(HttpSession session,ModelMap model,
			RedirectAttributes redirectAttributes, int pageNo,
			int totalCount,String username,int diaryid) {
		
		Pager<DiaryComment> pager = new Pager<DiaryComment>();
		pager.setCurrentPage(pageNo);
		pager.setTotalCount(totalCount);
		
		try {
			logger.info("[pager in paging]:"+pager);
			model.addAttribute("pager", diaryCommentService.getByPager(session,pager, username,diaryid));
			
			model.addAttribute("q_username", username);	//查询参数传递到listUI页面
			model.addAttribute("q_diaryid", diaryid);	//查询参数传递到listUI页面
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "redirect:/diaryComment/index";
		}
		return "diaryComment/listUI";
		
	}
}
