package com.animalnest.animal.service;

import com.animalnest.animal.dao.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavoriteService {

    /**
     * get all favorite animals of this user
     * @param userId
     * @return
     */
    public List<Integer> selectByUserId(Integer userId);

    /**
     * add favorite animal
     * @param userId
     * @param animalId
     */
    public void insertFavorite(Integer userId, Integer animalId);

    /**
     * delete favorite animal
     * @param userId
     * @param animalId
     */
    public void deleteFavorite(Integer userId, Integer animalId);
}
