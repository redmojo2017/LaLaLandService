package com.cjl.ljz.basedemo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserLottery implements Serializable {
	
	  private static final long serialVersionUID = 1L;

    @Override
	public String toString() {
		return "UserLottery [id=" + id + ", userId=" + userId + ", count=" + count + ", addTime=" + addTime
				+ ", updateTime=" + updateTime + ", period=" + period + "]";
	}

	private Integer id;

    private Integer userId;

    private Integer count;

    private Date addTime;

    private Date updateTime;

    private Integer period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}