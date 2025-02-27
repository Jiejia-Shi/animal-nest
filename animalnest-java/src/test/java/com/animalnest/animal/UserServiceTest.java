package com.animalnest.animal;

import com.animalnest.animal.entity.User;
import com.animalnest.animal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testUserService() {
        User user = userService.selectById(1);
        User user2 = userService.selectByOpenId("ofscN5T8qWKTW7EGDM6Bn4NM1J6Y");
        System.out.println(user2);
    }


}
