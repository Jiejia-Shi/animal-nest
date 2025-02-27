package com.animalnest.animal.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FeedbackService {

    public Integer insertFeedback(int userId, String msg);
}
