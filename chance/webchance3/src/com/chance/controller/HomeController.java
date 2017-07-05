package com.chance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping("/top")
	public String top() {	
		return "home/top";
	}
	@RequestMapping("/down")
	public String down() {	
		return "home/down";
	}
	
	@RequestMapping("/left")
	public String left(){	
		return "home/left";
	}
	@RequestMapping("/center")
	public String center(){	
		return "home/center";
	}
	@RequestMapping("/right")
	public String right() {	
		return "home/right";
	}
	@RequestMapping("/index")
	public String index(){	
		return "home/index";
	}
}
