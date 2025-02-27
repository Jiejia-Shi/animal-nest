package com.animalnest.animal.controller;

import com.animalnest.animal.common.R;
import com.animalnest.animal.entity.*;
import com.animalnest.animal.service.CommentService;
import com.animalnest.animal.service.PostingService;
import com.animalnest.animal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostingService postingService;

    /**
     * add comment
     * @param userId
     * @param postingId
     * @param words
     * @return
     */
    @PostMapping("/insertComment")
    public R<Integer> insertComment(Integer userId, Integer postingId, String words) {
        int n = commentService.insertComment(userId, postingId, words);
        if(n > 0) {
            postingService.changeCommentNumber(postingId, 1);
            return R.success(1);
        }
        else return R.error("fail to add commit");
    }

    /**
     * get comments of a post
     * @param page
     * @param pageSize
     * @param postingId
     * @return
     */
    @PostMapping("/selectByPostingId")
    public R<List<CommentWithEditor>> selectByPostingId(Integer page, Integer pageSize, Integer postingId) {
        List<Comment> comments = commentService.selectByPostingId(postingId);
        int n = comments.size();
        int j = (page - 1) * pageSize;
        int start = n - j;
        if(start < 0 || comments == null) return R.error("no more comments");
        List<User> users = userService.selectAll();
        List<CommentWithEditor> res = new ArrayList<>();
        for(int i = n - 1 - j; i >= 0 && pageSize > 0; i--) {
            Comment comment = comments.get(i);
            // add user information
            for(int k = 0; k < users.size(); k++) {
                if(users.get(k).getId() == comment.getUserId()) {
                    User user = users.get(k);
                    CommentWithEditor commentWithEditor = new CommentWithEditor(comment, user.getName(), user.getProfile());
                    res.add(commentWithEditor);
                    break;
                }
            }
            pageSize--;
        }
        return R.success(res);
    }
}
