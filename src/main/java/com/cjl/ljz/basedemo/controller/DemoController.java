package com.cjl.ljz.basedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cjl.ljz.basedemo.entity.UserLottery;
import com.cjl.ljz.basedemo.service.DemoService;
import com.cjl.ljz.basedemo.util.RedisCache;

@Controller
@RequestMapping("/demo")
public class DemoController {
    
	@Resource
	private DemoService demoservice;
	
	@RequestMapping("/showDemo")
	public ModelAndView getDemo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("name")==null) {
			session.setAttribute("name", session.getId());
			System.out.println("11111111111111");
		}else {
			System.out.println("222222222222222"+session.getAttribute("name"));
		}
		UserLottery demo = demoservice.getDemo("1");
		System.out.println(demo.toString());
		ModelAndView maView =new ModelAndView("index");
		return maView;
		

		
	}
	
}
