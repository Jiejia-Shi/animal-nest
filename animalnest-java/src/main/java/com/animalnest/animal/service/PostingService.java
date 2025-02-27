package com.animalnest.animal.service;


import com.animalnest.animal.dao.PostingDao;
import com.animalnest.animal.entity.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostingService {

    /**
     * find all posts
     */
    public List<Posting> selectAll(Integer a, Integer b);

    /**
     * find all posts of an animal
     * @param animalId
     * @return
     */
    public List<Posting> selectByAnimalId(Integer animalId, Integer a, Integer b);

    /**
     * find all posts from a user
     * @param userId
     * @return
     */
    public List<Posting> selectByUserId(Integer userId, Integer a, Integer b);

    /**
     * change like number
     * @param postingId
     * @param changeNumber
     * @return
     */
    public Integer changeLikesNumber(Integer postingId, Integer changeNumber);

    public Integer insertPosting(String picture, String words, Integer animalId, Integer userId);

    public Integer changeCommentNumber(Integer postingId, Integer changeNumber);


}
