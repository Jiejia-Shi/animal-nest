package com.animalnest.animal.dao;


import com.animalnest.animal.entity.Posting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * operate post data
 */
@Mapper
public interface PostingDao {

    @Select("Select * from posting limit #{a}, #{b};")
    public List<Posting> selectAll(Integer a, Integer b);

    @Select("select * from posting where animal_id = #{animalId} limit #{a}, #{b}")
    public List<Posting> selectByAnimalId(Integer animalId, Integer a, Integer b);

    @Select("select * from posting where user_id = #{userId} limit #{a}, #{b}")
    public List<Posting> selectByUserId(Integer userId, Integer a, Integer b);

    @Update("update posting set likes_number = likes_number + #{changeNumber} where id = #{postingId}")
    public Integer changeLikesNumber(Integer postingId, Integer changeNumber);

    @Update("update posting set comment_number = comment_number + #{changeNumber} where id = #{postingId}")
    public Integer changeCommentNumber(Integer postingId, Integer changeNumber);

    @Insert("insert into posting values(null, #{picture}, #{words}, #{animalId}, #{userId}, 0, 0)")
    public Integer insertPosting(String picture, String words, Integer animalId, Integer userId);

}
