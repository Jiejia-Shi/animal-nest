package com.animalnest.animal.dao;

import com.animalnest.animal.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * operate user data
 */
@Mapper
public interface AdminDao {
    @Select("select * from admin where id = #{id}")
    public Admin getById(Integer id);

    @Select("Select * from admin where phone like #{phone} ")
    public Admin getByPhone(String phone);
}
