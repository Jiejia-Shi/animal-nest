package com.animalnest.animal.service.impl;

import com.animalnest.animal.dao.LikeDao;
import com.animalnest.animal.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;

    public List<Integer> selectByUserId(Integer userId){
        return likeDao.selectByUserId(userId);
    }

    public Integer insertLike(Integer userId, Integer postingId) {
        return likeDao.insertLike(userId, postingId);
    }

    public Integer deleteLike(Integer userId, Integer postingId) {
        return likeDao.deleteLike(userId, postingId);
    }
}
