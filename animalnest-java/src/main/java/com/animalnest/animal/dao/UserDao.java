package com.animalnest.animal.dao;


import com.animalnest.animal.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * operate user data (wechat)
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id=#{id}")
    public User selectById(Integer id);

    @Select("select * from user where open_id like #{openId}")
    public User selectByOpenId(String openId);

    @Select("select * from user")
    public List<User> selectAll();

    @Update("update user set name = #{name} where id = #{id}")
    public Integer updateName(Integer id, String name);

    @Update("update user set profile = #{profile} where id = #{id}")
    public Integer updateProfile(Integer id, String profile);

    @Insert("insert into user values(null, 123, #{name}, #{profile}, #{openId})")
    public Integer insertUser(String name, String profile, String openId);

}
