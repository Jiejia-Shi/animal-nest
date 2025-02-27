package com.animalnest.animal.dao;

import com.animalnest.animal.entity.Animal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * operate animal data
 */
@Mapper
public interface AnimalDao {

    @Select("select * from animal_info where id=#{id}")
    public Animal selectById(Integer id);

    @Select("select * from animal_info;")
    public List<Animal> selectAll();

}
