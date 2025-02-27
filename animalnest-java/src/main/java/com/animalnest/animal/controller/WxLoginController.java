package com.animalnest.animal.controller;

import com.animalnest.animal.common.R;
import com.animalnest.animal.entity.User;
import com.animalnest.animal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/wxlogin")
public class WxLoginController {

    @Autowired
    UserService userService;

    /**
     * update user name
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/updateUserName")
    public R<Integer> updateName(Integer id, String name) {
        Integer n = userService.updateName(id, name);
        if(n != 0) return R.success(1);
        else return R.error("faile to update user name");
    }

    /**
     * update user profile
     * @param id
     * @param profile
     * @return
     */
    @PostMapping("/updateUserProfile")
    public R<Integer> updateProfile(Integer id, String profile) {
        StringBuffer pf = new StringBuffer();
        for(int i = 0; i < profile.length(); i++) {
            if(profile.charAt(i) != '\\') pf.append(profile.charAt(i));
        }
        profile = pf.toString();
        Integer n = userService.updateProfile(id, profile);
        if(n != 0) return R.success(1);
        else return R.error("faile to update user profile");
    }

    /**
     * get user info
     * @param openId
     * @return
     */
    @PostMapping("/getUserInfo")
    public User getUserInfo(String openId) {
        User user = userService.selectByOpenId(openId);
        return user;
    }

    /**
     * login (wechat)
     * @param code
     * @return
     */
    @PostMapping("/login")
    public User login(String code) {
        System.out.println(code);
        // get user information
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpMethod method = HttpMethod.POST;
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        // todo: add your own appid and secret key
        valueMap.add("appid","");
        valueMap.add("secret","");
        valueMap.add("js_code",code);
        valueMap.add("grant_type","authorization_code");
        String responseBody = sendRequest("https://api.weixin.qq.com/sns/jscode2session",valueMap,method,headers);

        // get user openid
        String[] responseBody1 = responseBody.split(",");
        String[] responseBody2 = responseBody1[1].split(":");
        int n = responseBody2[1].length();
        String openid = responseBody2[1].substring(1, n - 2);

        // search user information based on openid
        User user = userService.selectByOpenId(openid);
        if(user != null) return user;

        // if this user does not exist, create and save a new one
        userService.insertUser("new member", "https://s3.bmp.ovh/imgs/2023/05/04/25b21a609659b540.jpg", openid);
        user = userService.selectByOpenId(openid);
        return user;
    }

    /**
     *
     * @param url
     * @param params
     * @param method
     * @param headers
     * @return
     */
    public static String sendRequest(String url, MultiValueMap<String, String> params, HttpMethod method, HttpHeaders headers) {
        RestTemplate client = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }
}
