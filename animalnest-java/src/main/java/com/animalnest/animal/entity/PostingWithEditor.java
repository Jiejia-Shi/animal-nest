package com.animalnest.animal.entity;


import lombok.Data;

/**
 * posting with editor
 */
@Data
public class PostingWithEditor extends Posting {

    private String userName;
    private String userProfile;

    private boolean like;

    public PostingWithEditor(Posting posting, String userName, String userProfile, boolean like) {
        this.id = posting.id;
        this.picture = posting.picture;
        this.words = posting.words;
        this.animalId = posting.animalId;
        this.userId = posting.userId;
        this.likesNumber = posting.likesNumber;
        this.commentNumber = posting.commentNumber;

        this.userName = userName;
        this.userProfile = userProfile;
        this.like = like;
    }
}
