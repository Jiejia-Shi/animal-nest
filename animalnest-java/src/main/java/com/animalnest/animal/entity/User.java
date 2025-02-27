package com.animalnest.animal.entity;


import lombok.Data;

/**
 * user (wechat)
 */
@Data
public class User {
    private Long id;
    private String phone;
    private String name;
    private String profile;
    private String openId;
}
