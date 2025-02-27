package com.animalnest.animal.service.impl;

import com.animalnest.animal.dao.CommentDao;
import com.animalnest.animal.entity.Comment;
import com.animalnest.animal.service.CommentService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> selectByPostingId(Integer postingId) {
        return commentDao.selectByPostingId(postingId);
    }

    public Integer insertComment(Integer userId, Integer postingId, String words) {
        return commentDao.insertComment(userId, postingId, words);
    }
}
