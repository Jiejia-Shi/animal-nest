package com.animalnest.animal.controller;

import com.animalnest.animal.common.R;
import com.animalnest.animal.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * like a post
     * @param userId
     * @param postingId
     * @return
     */
    @PostMapping("/insert")
    public R<Integer> insertLike(Integer userId, Integer postingId) {
        Integer ins = likeService.insertLike(userId, postingId);
        if(ins != 0) return R.success(ins);
        else return R.error("fail to like this post");
    }

    /**
     * cancel like of a post
     * @param userId
     * @param postingId
     * @return
     */
    @PostMapping("/delete")
    public R<Integer> deleteLike(Integer userId, Integer postingId) {
        Integer del = likeService.deleteLike(userId, postingId);
        if(del != 0) return R.success(del);
        else return R.error("fail to cancel like");
    }
}
