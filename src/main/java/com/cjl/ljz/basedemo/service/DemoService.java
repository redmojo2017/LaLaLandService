package com.cjl.ljz.basedemo.service;


import javax.annotation.Resource;




import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cjl.ljz.basedemo.dao.UserLotteryMapper;
import com.cjl.ljz.basedemo.entity.UserLottery;



@Service
public class DemoService{
	@Resource
    private UserLotteryMapper userLotteryMapper;
    
	@Cacheable(value="common",key="#id")
    public UserLottery getDemo(String id) {
		System.out.println("调用2212222dao方法了。。。。。。。。。。。。。。");
        return userLotteryMapper.selectByPrimaryKey(Integer.parseInt(id));
    }
    
	public void insertSelective(UserLottery user) {
		System.out.println("调用insert方法。。。。。。。。");
	}
	//@CachePut("users")//每次都会执行方法，并将结果存入指定的缓存中
	// @Caching(evict={@CacheEvict(value="shops:brief",allEntries=true)}) 表示一旦执行久刷新shhopsLbrief下的所有缓存
	//@CachePut(value="shops:update",key="#user.id")
	public void updateSelective(UserLottery user) {
		System.out.println("调用update方法。。。。。。。。");
	}

}
