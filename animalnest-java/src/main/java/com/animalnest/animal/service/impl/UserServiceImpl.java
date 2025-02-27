package com.animalnest.animal.service.impl;


import com.animalnest.animal.dao.UserDao;
import com.animalnest.animal.entity.User;
import com.animalnest.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User selectById(Integer id) {
        return userDao.selectById(id);
    }

    public User selectByOpenId(String openId) {
        return userDao.selectByOpenId(openId);
    }

    public Integer updateName(Integer id, String name) {
        return userDao.updateName(id, name);
    }

    public Integer updateProfile(Integer id, String profile) {
        return userDao.updateProfile(id, profile);
    }

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public Integer insertUser(String name, String profile, String openId) {
        return insertUser(name, profile, openId);
    }
}
