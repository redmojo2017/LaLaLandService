package com.cjl.ljz.basedemo.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjl.ljz.basedemo.entity.User;
import com.cjl.ljz.basedemo.service.UserService;
import com.cjl.ljz.basedemo.util.MessageUtils;
import com.cjl.ljz.basedemo.util.ParamUtils;
import com.cjl.ljz.basedemo.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/hello")
public class IndexController {
	@Autowired
	private UserService userService;
	/**
	 * 注册页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	/**
	 * 登陆页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	/**
	 * 登陆页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendMessage",produces="text/html;charset=UTF-8")
	public String sendMessage(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String mobile = ParamUtils.getParameter(request, "phone");
		Map<String, Object> param=new HashMap<String, Object>();
		String uUid = MessageUtils.getUUid();
		param.put("code", uUid);
		//错误是否能够返回给前台
		//成功发送之后限制验证码发送
		Map<String, Object> sendMessage = new HashMap<String, Object>();
		sendMessage.put("messageCode", "123123");
		sendMessage.put("errorCode", "");
		//Map<String, Object> sendMessage =MessageUtils.sendMessage(mobile, param, "龙鹏天下", "SMS_85480015");
		resultMap.put("messageCode", sendMessage.get("messageCode"));
		resultMap.put("errorCode", sendMessage.get("errorCode"));
		Object json = JSON.toJSON(resultMap);
		return json.toString();
	}
	
	
	/**
	 * 登陆
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(HttpServletRequest request) throws Exception {
		Map<String, Object> login = userService.login(request);
		ModelAndView mav = new ModelAndView("indexPage");
		mav.addObject("user", login.get("user"));
		mav.addObject("userLogin", login);
		return mav;
	}
	/**
	 * 注册
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/registers",produces="text/html;charset=UTF-8")
	public String register(HttpServletRequest request,User user) throws Exception {
		Map<String, Object> mobileUser = userService.getMobileUser(user.getPhone());
		Map<String, Object> resMap=new HashMap<String, Object>();
		if (!mobileUser.get("code").equals("1")) {
			userService.register(user);
			resMap.put("code", "true");
		}else {
			resMap.put("code", "false");
			resMap.put("message", "号码已经注册了");
		}
		Object json = JSON.toJSON(resMap);
		return json.toString();
	}


	

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changePwd")
	public Object changePwd(HttpServletRequest request) throws Exception {
		return null;
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("forward:/welcome.jsp");
		userService.logout(request);
		return mav;
	}

}
