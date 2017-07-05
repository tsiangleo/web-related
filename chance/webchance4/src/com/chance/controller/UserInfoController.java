package com.chance.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.service.UserService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;

@Controller
public class UserInfoController {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 首页
	 */
	@RequestMapping("/user/index")
	public String index() {
		logger.info("[in index]...");
		return "user/index";
	}
	
	/**
	 * 查询页面
	 */
	@RequestMapping("/user/queryUI")
	public String queryUI() {
		logger.info("[in queryUI]...");
		return "user/queryUI";
	}
	
	/**
	 * 屏蔽/激活页面
	 */
	@RequestMapping("/user/activeUI")
	public String activeUI() {
		logger.info("[in activeUI]...");
		return "user/activeUI";
	}
	
	/**
	 * 查询用户信息
	 */
	@RequestMapping("/user/query")
	public String query(@RequestParam(value="loginName",required=false) String loginName,
			@RequestParam(value="userid",required=false) Integer userid,
			ModelMap model,RedirectAttributes redirectAttributes) {
		logger.info("[in query]:loginName:"+loginName+",userid:"+userid);
		
		if(loginName != null && !loginName.isEmpty()){
			try {
				model.addAttribute("user",userService.getUserBriefInfo(loginName));
			} catch (BusinessException e) {
				model.addAttribute("tipMsg", e.getMessage());
				logger.info(e.getMessage());
				return "goback";
			}
			return "user/briefInfoUI";
		}else if(userid != null){
			try {
				model.addAttribute("user",userService.getUserBriefInfo(userid));
			} catch (BusinessException e) {
				model.addAttribute("tipMsg", e.getMessage());
				logger.info(e.getMessage());
				return "goback";
			}
			return "user/briefInfoUI";
		}else{
			model.addAttribute("tipMsg", "查询参数不能都为空！");
			return "user/queryUI";
		}
		
	}
	
	/**
	 * 屏蔽账号
	 * <br>按用户登录名或者用户id屏蔽
	 */
	@RequestMapping("/user/noActive")
	public String setAccountNoActive(@RequestParam(value="loginName",required=false) String loginName,
			@RequestParam(value="userid",required=false) Integer userid,
			ModelMap model,RedirectAttributes redirectAttributes) {
		logger.info("[in setAccountNoActive]:loginName:"+loginName+",userid:"+userid);
		
		if(loginName != null && !loginName.isEmpty()){
			try {
				userService.setAccountNoActive(loginName);
			} catch (BusinessException e) {
				model.addAttribute("tipMsg", e.getMessage());
				logger.info(e.getMessage());
				return "goback";
			}
			model.addAttribute("tipMsg", "屏蔽成功！");
			return "user/success";
		}else if(userid != null){
			try {
				userService.setAccountNoActive(userid);
			} catch (BusinessException e) {
				model.addAttribute("tipMsg", e.getMessage());
				logger.info(e.getMessage());
				return "goback";
			}
			model.addAttribute("tipMsg", "屏蔽成功！");
			return "user/success";
		}else{
			model.addAttribute("tipMsg", "loginName和userid不能同时为空！");
			return "goback";
		}
		
	}
	
	/**
	 * 激活账号
	 * <br>按用户登录名或者用户id激活
	 */
	@RequestMapping("/user/active")
	public String setAccountActive(@RequestParam(value="loginName",required=false) String loginName,
			@RequestParam(value="userid",required=false) Integer userid,
			ModelMap model,RedirectAttributes redirectAttributes) {
		logger.info("[in setAccountActive]:loginName:"+loginName+",userid:"+userid);
		
		if(loginName != null && !loginName.isEmpty()){
			try {
				userService.setAccountActive(loginName);
			} catch (BusinessException e) {
				model.addAttribute("tipMsg", e.getMessage());
				logger.info(e.getMessage());
				return "goback";
			}
			model.addAttribute("tipMsg", "激活成功！");
			return "user/success";
		}else if(userid != null){
			try {
				userService.setAccountActive(userid);
			} catch (BusinessException e) {
				model.addAttribute("tipMsg", e.getMessage());
				logger.info(e.getMessage());
				return "goback";
			}
			model.addAttribute("tipMsg", "激活成功！");
			return "user/success";
		}else{
			model.addAttribute("tipMsg", "loginName和userid不能同时为空！");
			return "goback";
		}
		
	}
	
	/**
	 * 获取用户的其他信息
	 */
	@RequestMapping("/user/getOtherInfo")
	public String getOtherInfo(@RequestParam("userid") Integer userid,
		ModelMap model,RedirectAttributes redirectAttributes) {
		logger.info("[in getOtherInfo]:userid:"+userid);
		try {
			model.addAttribute("userOtherInfo",userService.getUserOtherInfo(userid));
			model.addAttribute("userid", userid);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}
		return "user/otherInfoUI";
	}
	/**
	 * 更新用户的其他信息
	 */
	@RequestMapping("/user/updateOtherInfo")
	public String updateOtherInfo(@RequestParam("userid") Integer userid,
			@RequestParam(value="lastLoveTime",required=false) Integer lastLoveTime,
			@RequestParam(value="attentionLimitNum",required=false) Integer attentionLimitNum,
			@RequestParam(value="loveLimitNum",required=false) Integer loveLimitNum,
			@RequestParam(value="markLimitNum",required=false) Integer markLimitNum,
			@RequestParam(value="friendLimitNum",required=false) Integer friendLimitNum,
			@RequestParam(value="sleepLoveTime",required=false) Integer sleepLoveTime,
			@RequestParam(value="createTeamLimitNum",required=false) Integer createTeamLimitNum,
			@RequestParam(value="attenTeamLimitNum",required=false) Integer attenTeamLimitNum,
			ModelMap model,RedirectAttributes redirectAttributes) throws BusinessException{
		logger.info("[in updateOtherInfo]:userid:"+userid+",lastLoveTime:"+lastLoveTime
				+",attentionLimitNum:"+attentionLimitNum+",loveLimitNum:"+loveLimitNum
				+",markLimitNum:"+markLimitNum+",friendLimitNum:"+friendLimitNum
				+",sleepLoveTime:"+sleepLoveTime+",createTeamLimitNum:"+createTeamLimitNum
				+",attenTeamLimitNum:"+attenTeamLimitNum);
		
		try {
			if (lastLoveTime != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_LASTLOVE_TIME_FILED, lastLoveTime);
			} 
			
			if (attentionLimitNum != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_ATTENTION_LIMIT_NUM_FILED, attentionLimitNum);
			} 
			
			if (loveLimitNum != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_LOVE_LIMIT_NUM_FILED, loveLimitNum);
			} 
			
			if (markLimitNum != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_MARK_LIMIT_NUM_FILED, markLimitNum);
			} 
			
			if (friendLimitNum != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_FRIEND_LIMIT_NUM_FILED, friendLimitNum);
			} 
			
			if (sleepLoveTime != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_SLEEPLOVE_TIME_FILED, sleepLoveTime);
			} 
			
			if (createTeamLimitNum != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_CREATE_TEAM_LIMIT_NUM_FILED, createTeamLimitNum);
			} 
			
			if (attenTeamLimitNum != null) {
				userService.updateUserOtherInfo(userid, MonitorConstants.OTHERINFO_ATTEND_TEAM_LIMIT_NUM_FILED, attenTeamLimitNum);
			} 
		} catch (Exception e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}	
		return "redirect:/user/getOtherInfo?userid="+userid;
	}
	}
