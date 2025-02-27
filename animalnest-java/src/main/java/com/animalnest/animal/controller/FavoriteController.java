package com.animalnest.animal.controller;

import com.animalnest.animal.common.R;
import com.animalnest.animal.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    /**
     * add favorite animal of a user
     * @param userId
     * @param animalId
     * @return
     */
    @PostMapping("/insert")
    public R<Integer> insertFavorite(Integer userId, Integer animalId) {
        favoriteService.insertFavorite(userId, animalId);
        return R.success(new Integer(1));
    }

    /**
     * delete favorite animal of a user
     * @param userId
     * @param animalId
     * @return
     */
    @PostMapping("/delete")
    public R<Integer> deleteFavorite(Integer userId, Integer animalId) {
        favoriteService.deleteFavorite(userId, animalId);
        return R.success(new Integer(1));
    }

}
