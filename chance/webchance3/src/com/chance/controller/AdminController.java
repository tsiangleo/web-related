package com.chance.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chance.domain.Admin;
import com.chance.service.AdminService;
import com.chance.service.impl.BusinessException;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 登陆页面
	 */
	@RequestMapping("/loginUI")
	public String loginUI(){	
		return "admin/loginUI";
	}
	
	/**
	 * 首页
	 */
	@RequestMapping("/index")
	public String index(){	
		return "admin/index";
	}
	
	/**
	 * 添加页面
	 */
	@RequestMapping("/addUI")
	public String addUI(){	
		return "admin/addUI";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/add")
	public String add(ModelMap model,Admin admin){	
		try {
			adminService.add(admin);
			model.addAttribute("tipMsg", "管理员添加成功！");
			return "admin/success";
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		
	}
	
	/**
	 * 查询所有管理员列表
	 */
	@RequestMapping("/list")
	public String list(ModelMap model) {
		try {
			model.addAttribute("adminList", adminService.getAll());
			return "admin/listUI";
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
	}
	
	/**
	 * 修改页面
	 */
	@RequestMapping("/updateUI")
	public String updateUI(ModelMap model,@RequestParam("id") Integer id){
		try {
			model.addAttribute("admin", adminService.getById(id));
			return "admin/updateUI";
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(ModelMap model,@RequestParam("id") Integer id,
			@RequestParam("name") String name){
		try {
			Admin admin = adminService.getById(id);
			admin.setName(name);
			adminService.update(admin);
			model.addAttribute("tipMsg", "管理员信息更新成功！");
			return "admin/success";
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
	}

	/**
	 * 登陆
	 */
	@RequestMapping("/login")
	public String login(HttpSession session, @RequestParam("loginName") String loginName, @RequestParam("userpass") String userpass,
			ModelMap model,RedirectAttributes redirectAttributes){
		
		logger.info("AdminController loginName: " + loginName + ";userpass: ******");	
		Admin admin = new Admin();
		admin.setLoginName(loginName);
		admin.setPassword(userpass);
		try {
			session.setAttribute("admin", adminService.login(admin));
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("tipMsg", e.getMessage());//错误提示
			logger.info(e.getMessage());
			return "redirect:/admin/loginUI";
		}
		return "redirect:/home/index";//return "forward:/home/index" 与 "home/index"是一样的效果，浏览器地址没变，依然是：http://localhost:8080/webchance3/admin/login
	}

	/**
	 * 注销
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session,RedirectAttributes redirectAttributes) {
		logger.info("AdminController logout ... ");	
		// 清除session
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		session.removeAttribute("admin");
		
		session.invalidate();
		
		redirectAttributes.addFlashAttribute("tipMsg", "您已成功退出系统！");
	    //应该是最外层页面跳转到登陆页面，而不是注销按钮所在的子页面跳转到登陆页面
	    return "redirect:/admin/loginUI"; 
	
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping("/changePwdUI")
	public String changePwdUI(ModelMap model,HttpSession session){	
		return "admin/changePwdUI";
		
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/changePwd")
	public String changePwd(ModelMap model,HttpSession session,
			@RequestParam("oldPwd") String oldpwd,
			@RequestParam("newPwd") String newpwd) {	
		Admin admin = (Admin) session.getAttribute("admin");
		try {
			adminService.changepwd(admin.getId(), oldpwd, newpwd);
		} catch (BusinessException e) {
			model.addAttribute("tipMsg", e.getMessage());
			logger.info(e.getMessage());
			return "goback";	//返回上一页
		}
		return "admin/changePwdsuccessUI";
	}
	

}
