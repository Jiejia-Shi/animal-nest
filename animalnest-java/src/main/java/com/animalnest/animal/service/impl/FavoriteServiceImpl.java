package com.animalnest.animal.service.impl;

import com.animalnest.animal.dao.FavoriteDao;
import com.animalnest.animal.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    public List<Integer> selectByUserId(Integer userId) {
        return favoriteDao.selectByUserId(userId);
    }

    public void insertFavorite(Integer userId, Integer animalId) {
        favoriteDao.insertFavorite(userId, animalId);
        return;
    }

    public void deleteFavorite(Integer userId, Integer animalId) {
        favoriteDao.deleteFavorite(userId, animalId);
        return;
    }
}
