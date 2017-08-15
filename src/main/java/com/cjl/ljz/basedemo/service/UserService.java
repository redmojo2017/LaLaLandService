package com.cjl.ljz.basedemo.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Service;

import com.cjl.ljz.basedemo.dao.userMapper;
import com.cjl.ljz.basedemo.entity.User;
import com.cjl.ljz.basedemo.util.ParamUtils;
import com.cjl.ljz.basedemo.util.RedisCache;
import com.cjl.ljz.basedemo.util.StringUtil;
import com.cjl.ljz.basedemo.util.constant.BaseConstant;



@Service
public class UserService {
	@Resource
	private userMapper userMapper;
	public Map<String, Object> login(HttpServletRequest request) {
		String phone = ParamUtils.getParameter(request, "phone");
		String password = ParamUtils.getParameter(request, "password");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("res_code", false);
		
		User selectByUser = userMapper.selectByUser(phone);
		
		if (selectByUser!=null) {
			request.getSession().setAttribute(BaseConstant.SESSION_USER_INFO, selectByUser);
			//setUserCache(request,selectByUser);
			map.put("res_code", true);
			map.put("user", selectByUser);
		}
		return map;
	}
	/**
	 * 用户登出
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 */
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = ParamUtils.getParameter(request, BaseConstant.SESSION_USER_ID);
		// 清除用户缓存
//		RedisCache resCache=new RedisCache();
//		ValueWrapper valueWrapper = resCache.get(userId);
//		Object object = valueWrapper.get();
//		resCache.evict(object+ BaseConstant.SESSION_USER_ID);
//		resCache.evict(object + BaseConstant.SESSION_USER_INFO);
		if (StringUtil.isNotEmpty(userId)) {
			session.invalidate();
		}
	}
	/**
	 * 注册
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 */
	public void register(User user) {
		userMapper.insert(user);
	}
	/**
	 * 注册
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 */
	public Map<String, Object> getMobileUser(String mobile) {
		Map<String, Object> map=new HashMap<String, Object>();
 		User users= userMapper.selectByUser(mobile);
		if (users!=null) {
			map.put("code", "1");
			map.put("message", "号码已经注册了");
		}else {
			map.put("code", "0");
		}
		return map;
	}
	/**
	 * 设置登录用户缓存信息
	 * 
	 * @param request
	 * @param user
	 *            登录用户
	 
	private void setUserCache(HttpServletRequest request, User user) {
		String userId = user.getId();
		String cacheKey = request.getSession().getId();
		RedisCache resCache=new RedisCache();
		resCache.put(userId, cacheKey);
		resCache.put(cacheKey + BaseConstant.SESSION_USER_ID, userId);
		// 用户信息放入缓存中
		resCache.put(cacheKey + BaseConstant.SESSION_USER_INFO,user);
	}*/
}
