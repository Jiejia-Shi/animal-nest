package com.animalnest.animal.entity;


import lombok.Data;


/**
 * posting
 */
@Data
public class Posting {
    protected Long id;
    protected String picture;
    protected String words;
    protected Long animalId;
    protected Long userId;
    protected Integer likesNumber;
    protected Integer commentNumber;
}
