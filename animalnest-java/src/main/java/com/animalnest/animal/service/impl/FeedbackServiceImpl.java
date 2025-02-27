package com.animalnest.animal.service.impl;

import com.animalnest.animal.dao.FeedbackDao;
import com.animalnest.animal.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public Integer insertFeedback(int userId, String msg) {
        return feedbackDao.insertFeedback(userId, msg);
    }
}
