package com.animalnest.animal.controller;

import com.animalnest.animal.common.R;
import com.animalnest.animal.entity.Admin;
import com.animalnest.animal.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private com.animalnest.animal.service.AdminService adminService;

    /**
     * login
     * @param request
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin) {


        // 1. get phone number and password
        String phone = admin.getPhone();
        String password = admin.getPassword();

        // 2. search this user
        Admin admin1 = adminService.getByPhone(phone);

        // 3. fail to login
        if(admin1 == null) {
            return R.error("fail to login");
        }
        if(!admin1.getPassword().equals(password)) {
            return R.error("fail to login");
        }

        // 4. login successfully, save user id to session
        request.getSession().setAttribute("admin", admin1.getId());
        return R.success(admin1);
    }

}
