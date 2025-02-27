package com.animalnest.animal;

import com.animalnest.animal.dao.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void testCommentDao() {
        System.out.println(commentDao.selectByPostingId(1));
    }
}
