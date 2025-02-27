package com.animalnest.animal.service.impl;

import com.animalnest.animal.dao.PostingDao;
import com.animalnest.animal.entity.Posting;
import com.animalnest.animal.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    private PostingDao postingDao;

    public List<Posting> selectAll(Integer a, Integer b) {
        return postingDao.selectAll(a, b);
    }

    public List<Posting> selectByAnimalId(Integer animalId, Integer a, Integer b) {
        return postingDao.selectByAnimalId(animalId, a, b);
    }

    public List<Posting> selectByUserId(Integer userId, Integer a, Integer b) {
        return postingDao.selectByUserId(userId, a, b);
    }
    public Integer changeLikesNumber(Integer postingId, Integer changeNumber) {
        return postingDao.changeLikesNumber(postingId, changeNumber);
    }

    public Integer insertPosting(String picture, String words, Integer animalId, Integer userId) {
        return postingDao.insertPosting(picture, words, animalId, userId);
    }

    public Integer changeCommentNumber(Integer postingId, Integer changeNumber){
        return postingDao.changeCommentNumber(postingId, changeNumber);
    }
}
