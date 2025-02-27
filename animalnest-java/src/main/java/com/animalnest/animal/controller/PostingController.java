package com.animalnest.animal.controller;


import com.animalnest.animal.common.R;
import com.animalnest.animal.entity.Animal;
import com.animalnest.animal.entity.Posting;
import com.animalnest.animal.entity.PostingWithEditor;
import com.animalnest.animal.entity.User;
import com.animalnest.animal.service.AnimalService;
import com.animalnest.animal.service.LikeService;
import com.animalnest.animal.service.PostingService;
import com.animalnest.animal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posting")
public class PostingController {

    @Autowired
    private PostingService postingService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private LikeService likeService;

    /**
     * add post
     * @param picture
     * @param words
     * @param animalId
     * @param userId
     * @return
     */
    @PostMapping("/insertPosting")
    public R<Integer> insertPosting(String picture, String words, Integer animalId, Integer userId) {
        StringBuffer pic = new StringBuffer();
        for(int i = 0; i < picture.length(); i++) {
            if(picture.charAt(i) != '\\') pic.append(picture.charAt(i));
        }
        picture = pic.toString();
        Integer n = postingService.insertPosting(picture, words, animalId, userId);
        if(n != 0) return R.success(1);
        else return R.error("fail to add posting");
    }

    /**
     * select post
     * @param page
     * @param pageSize
     * @return
     */
    @PostMapping("/postingInfo")
    public R<List<PostingWithEditor>> selectPosting(Integer page, Integer pageSize, Integer num, Integer userId, Integer userSearch, Integer animalId) {
        List<Animal> animals = animalService.selectAll();
        int n = animals.size();
        List<Posting> postings = new LinkedList<Posting>();
        List<Integer> likesId = likeService.selectByUserId(userId);

        int j = (page - 1) * pageSize;

        // 1. get all posts
        if(animalId != null && animalId > 0) {
            postings = postingService.selectByAnimalId(animalId, j, j + pageSize);
        }
        // find specific post information
        else if(num!=null) {
            Integer index = (num - 1) % n;
            Integer animalId1 = animals.get(index).getId().intValue();
            postings = postingService.selectByAnimalId(animalId1, j, j + pageSize);
        }
        // find specific post information of a user
        else if(userSearch != null) {
            postings = postingService.selectByUserId(userId, j, j + pageSize);
        }
        // find all post information
        else postings = postingService.selectAll(j, j + pageSize);


        List<PostingWithEditor> resPostings = new LinkedList<PostingWithEditor>();
        // 2. deal with post
        if(postings.size() == 0) return(R.error("no more post"));

        j = 0;
        for(int i = pageSize; j < postings.size() && i > 0; i--) {
            Posting post = postings.get(j);
            // get user and like information of this post
            Integer id = post.getUserId().intValue();
            User user = userService.selectById(id);
            // if this post is liked
            boolean like = false;
            if(likesId.contains(post.getId().intValue())) like = true;
            PostingWithEditor POS = new PostingWithEditor(post, user.getName(), user.getProfile(), like);
            resPostings.add(POS);
            j++;
        }
        return R.success(resPostings);
    }

    /**
     * change the like number of a post
     * @param postingId
     * @param changeNumber
     * @return
     */
    @PostMapping("/changeLikesNumber")
    public R<Integer> changeLikesNumber(Integer postingId, Integer changeNumber) {
        Integer mark = postingService.changeLikesNumber(postingId, changeNumber);
        if(mark != 0) return R.success(mark);
        else return R.error("fail to change like number");
    }
}
