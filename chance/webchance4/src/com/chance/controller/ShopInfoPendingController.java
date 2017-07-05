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

import com.chance.domain.ShopInfoPending;
import com.chance.service.ShopInfoPendingService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;

@Controller
public class ShopInfoPendingController {
	private static final Logger logger = LoggerFactory.getLogger(ShopInfoPendingController.class);
	
	@Autowired
	private ShopInfoPendingService shopInfoPendingService;

	
	/**
	 *首页
	 */
	@RequestMapping("/shopInfoPending/index")
	public String index() {
		logger.info("[in index]...");
		return "shopInfoPending/index";
	}
	
	@RequestMapping("/shopInfoPending/get")
	public String get(HttpSession session, ModelMap model){
		logger.info("[in get]");
		try {
			List<ShopInfoPending> shopInfoPendingList = shopInfoPendingService.getList(MonitorConstants.PAGE_SIZE);
			model.addAttribute("shopInfoPendingList", shopInfoPendingList);	
			
			//缓存到session中
			if (shopInfoPendingList != null && !shopInfoPendingList.isEmpty()) {
				Map<Integer,ShopInfoPending> shopInfoPendingMap = new HashMap<Integer,ShopInfoPending>();
				for(int i = 0;i <shopInfoPendingList.size();i++){
					shopInfoPendingMap.put(shopInfoPendingList.get(i).getId(), shopInfoPendingList.get(i));
				}
				session.setAttribute("shopInfoPendingMap", shopInfoPendingMap);
			}
			
			return "shopInfoPending/listUI";
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";
		}
	}
	
	/**
	 * 详细内容
	 */
	@RequestMapping("/shopInfoPending/detail")
	public String detail(ModelMap model,HttpSession session,@RequestParam("id") Integer id) {
		logger.info("[in detail] id:"+id);
		
		Map<Integer,ShopInfoPending> shopInfoPendingMap = (Map<Integer, ShopInfoPending>)session.getAttribute("shopInfoPendingMap");
		model.addAttribute("shopInfoPending", shopInfoPendingMap.get(id));
		return "shopInfoPending/detailUI";
	}
	
	/**
	 * 审核
	 */
	@RequestMapping("/shopInfoPending/audit")
	public String audit(ModelMap model,@RequestParam("id") Integer id,
			@RequestParam("pass") Integer pass,@RequestParam("reason") String reason) {
		
		logger.info("[in audit] id:"+id+",pass:"+pass+",reason:"+reason);
		
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
			shopInfoPendingService.audit(id, pass, encodeReason);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		model.addAttribute("tipMsg", "操作成功！");
		return "shopInfoPending/success";
	}
	
}
