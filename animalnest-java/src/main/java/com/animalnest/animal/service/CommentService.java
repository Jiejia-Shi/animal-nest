package com.animalnest.animal.service;

import com.animalnest.animal.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {
    public List<Comment> selectByPostingId(Integer postingId);

    public Integer insertComment(Integer userId, Integer postingId, String words);
}
