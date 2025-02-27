package com.animalnest.animal.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * operate like data
 */
@Mapper
public interface LikeDao {

    @Select("select posting_id from posting_like where user_id = #{userId}")
    public List<Integer> selectByUserId(Integer userId);

    @Insert("insert into posting_like values(null, #{userId}, #{postingId})")
    public Integer insertLike(Integer userId, Integer postingId);

    @Delete("delete from posting_like where user_id = #{userId} and posting_id = #{postingId}")
    public Integer deleteLike(Integer userId, Integer postingId);


}


