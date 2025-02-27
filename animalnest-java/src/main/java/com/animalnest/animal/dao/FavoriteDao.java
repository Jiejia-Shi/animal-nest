package com.animalnest.animal.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * operate favorite data
 */
@Mapper
public interface FavoriteDao {

    @Select("select animal_id from favorite where user_id = #{userId}" )
    public List<Integer> selectByUserId(Integer userId);


    @Insert("insert into favorite values(null, #{userId}, #{animalId})")
    public void insertFavorite(Integer userId, Integer animalId);

    @Delete("delete from favorite where user_id=#{userId} and animal_id=#{animalId}")
    public void deleteFavorite(Integer userId, Integer animalId);


}
