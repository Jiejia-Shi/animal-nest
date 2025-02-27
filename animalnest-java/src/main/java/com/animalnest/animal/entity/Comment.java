package com.animalnest.animal.entity;


import lombok.Data;

/**
 * comment
 */
@Data
public class Comment {
    public int id;
    public int userId;
    public int postingId;
    public String words;
    public int likesNumber;
}
