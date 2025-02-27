package com.animalnest.animal.dao;

import com.animalnest.animal.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * operate comment data
 */
@Mapper
public interface CommentDao {

    @Select("select * from comment where posting_id = #{postingId}")
    public List<Comment> selectByPostingId(Integer postingId);

    @Insert("insert into comment values(null, #{userId}, #{postingId}, #{words}, 0)")
    public Integer insertComment(Integer userId, Integer postingId, String words);
}
