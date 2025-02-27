package com.animalnest.animal.controller;

import com.animalnest.animal.common.R;
import com.animalnest.animal.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * add feedback
     * @param userId
     * @param msg
     * @return
     */
    @PostMapping("/insert")
    public R<Integer> insertFeedback(Integer userId, String msg) {
        Integer n = feedbackService.insertFeedback(userId, msg);
        if(n != 0) return R.success(n);
        else return R.error("fail to add feedback");
    }
}
