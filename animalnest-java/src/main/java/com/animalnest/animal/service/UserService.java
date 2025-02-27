package com.animalnest.animal.service;

import com.animalnest.animal.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {


    public User selectById(Integer id);

    public User selectByOpenId(String openId);

    public Integer updateName(Integer id, String name);

    public Integer updateProfile(Integer id, String profile);

    public List<User> selectAll();

    public Integer insertUser(String name, String profile, String openId);
}
