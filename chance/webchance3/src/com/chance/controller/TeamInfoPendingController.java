package com.chance.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chance.domain.TeamInfoPending;
import com.chance.service.TeamInfoPendingService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;

@Controller
public class TeamInfoPendingController {
	private static final Logger logger = LoggerFactory.getLogger(TeamInfoPendingController.class);
	
	@Autowired
	private TeamInfoPendingService teamInfoPendingService;

	/**
	 *首页
	 */
	@RequestMapping("/teamInfoPending/index")
	public String index() {
		logger.info("[in index]...");
		return "teamInfoPending/index";
	}
	
	@RequestMapping("/teamInfoPending/get")
	public String get(HttpSession session, ModelMap model){
		logger.info("[in get]");
		try {
			List<TeamInfoPending> teamInfoPendingList = teamInfoPendingService.getList(MonitorConstants.PAGE_SIZE);
			model.addAttribute("teamInfoPendingList", teamInfoPendingList);	
			
			//缓存到session中
			if (teamInfoPendingList != null && !teamInfoPendingList.isEmpty()) {
				Map<Integer,TeamInfoPending> teamInfoPendingMap = new HashMap<Integer,TeamInfoPending>();
				for(int i = 0;i <teamInfoPendingList.size();i++){
					teamInfoPendingMap.put(teamInfoPendingList.get(i).getTeamId(), teamInfoPendingList.get(i));
				}
				session.setAttribute("teamInfoPendingMap", teamInfoPendingMap);
			}
			
			return "teamInfoPending/listUI";
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}
	}
	
	/**
	 * 详细内容
	 */
	@RequestMapping("/teamInfoPending/detail")
	public String detail(ModelMap model,HttpSession session,@RequestParam("teamid") Integer teamid) {
		logger.info("[in detail]teamid:"+teamid);
		
		Map<Integer,TeamInfoPending> teamInfoPendingMap = (Map<Integer, TeamInfoPending>)session.getAttribute("teamInfoPendingMap");
		model.addAttribute("teamInfoPending", teamInfoPendingMap.get(teamid));
		return "teamInfoPending/detailUI";
	}
	
	/**
	 * 审核
	 */
	@RequestMapping("/teamInfoPending/audit")
	public String audit(ModelMap model,@RequestParam("teamid") Integer teamid,
			@RequestParam("pass") Integer pass,@RequestParam("reason") String reason) {
		
		logger.info("[in audit]teamid:"+teamid+",pass:"+pass+",reason:"+reason);
		
		if(pass == 1 && (reason == null || "".equals(reason)))
			reason = "通过";
		if(pass == 2 && (reason == null || "".equals(reason)))
			reason = "不通过";
		
		String encodeReason = null;
		try {
			encodeReason = java.net.URLEncoder.encode(reason,"utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("字符编码错误 ！待编码字符串为："+reason);
		}
		try {
			teamInfoPendingService.audit(teamid, pass, encodeReason);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "操作成功！");
		return "teamInfoPending/success";
	}
	
}
