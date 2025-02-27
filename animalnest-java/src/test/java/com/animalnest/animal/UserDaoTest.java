package com.animalnest.animal;


import com.animalnest.animal.dao.UserDao;
import com.animalnest.animal.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testUserDao() {
        Integer n = userDao.updateProfile(1, "https://s3.bmp.ovh/imgs/2023/02/25/37ec132ef9505ae2.png");
        System.out.println(n);
    }
}
