package com.animalnest.animal.service;


import com.animalnest.animal.dao.LikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LikeService {

    /**
     * find user's liked posts
     * @param userId
     * @return
     */
    public List<Integer> selectByUserId(Integer userId);

    /**
     * add like
     * @param userId
     * @param postingId
     * @return
     */
    public Integer insertLike(Integer userId, Integer postingId);

    /**
     * delete like
     * @param userId
     * @param postingId
     * @return
     */
    public Integer deleteLike(Integer userId, Integer postingId);


}
