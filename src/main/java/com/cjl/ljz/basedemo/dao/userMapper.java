package com.cjl.ljz.basedemo.dao;

import java.util.List;

import com.cjl.ljz.basedemo.entity.User;

public interface userMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    
    User selectByUser (String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}