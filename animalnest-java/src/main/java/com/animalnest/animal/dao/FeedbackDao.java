package com.animalnest.animal.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * operate feedback data
 */
@Mapper
public interface FeedbackDao {

    @Insert("insert into feedback values (null, #{userId}, #{msg})")
    public Integer insertFeedback(Integer userId, String msg);
}
