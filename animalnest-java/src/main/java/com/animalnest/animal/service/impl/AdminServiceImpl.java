package com.animalnest.animal.service.impl;


import com.animalnest.animal.dao.AdminDao;
import com.animalnest.animal.entity.Admin;
import com.animalnest.animal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    public Admin getByPhone(String phone) {
        return adminDao.getByPhone(phone);
    }

}
