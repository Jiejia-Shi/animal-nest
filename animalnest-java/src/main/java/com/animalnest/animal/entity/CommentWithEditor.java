package com.animalnest.animal.entity;

import lombok.Data;

/**
 * comment with editor
 */
@Data
public class CommentWithEditor extends Comment {
    private String userName;
    private String userProfile;

    public CommentWithEditor(Comment comment, String userName, String userProfile) {
        this.id = comment.id;
        this.userId = comment.userId;
        this.postingId = comment.postingId;
        this.words = comment.words;
        this.likesNumber = comment.likesNumber;
        this.userName = userName;
        this.userProfile = userProfile;
    }
}
