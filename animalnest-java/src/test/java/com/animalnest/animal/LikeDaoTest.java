package com.animalnest.animal;

import com.animalnest.animal.dao.LikeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikeDaoTest {

    @Autowired
    LikeDao likeDao;

    @Test
    public void testLikeDao() {
        Integer a = likeDao.insertLike(1,6);
        //likeDao.insertLike(1,6);
        System.out.println(a);
    }
}
