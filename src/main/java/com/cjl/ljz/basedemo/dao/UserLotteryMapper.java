package com.cjl.ljz.basedemo.dao;


import com.cjl.ljz.basedemo.entity.UserLottery;

public interface UserLotteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLottery record);

    int insertSelective(UserLottery record);

    UserLottery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLottery record);

    int updateByPrimaryKey(UserLottery record);
}