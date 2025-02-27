package com.animalnest.animal.service;
import com.animalnest.animal.entity.Admin;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService {

    /**
     * get user info by phone number
     * @param Phone
     * @return
     */
    public Admin getByPhone(String Phone);

}
