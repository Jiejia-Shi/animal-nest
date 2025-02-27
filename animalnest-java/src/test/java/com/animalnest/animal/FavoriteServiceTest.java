package com.animalnest.animal;


import com.animalnest.animal.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FavoriteServiceTest {

    @Autowired
    FavoriteService favoriteService;

    @Test
    public void testFavoriteService() {
        List<Integer> animalId = favoriteService.selectByUserId(1);
        System.out.println(animalId);
        favoriteService.insertFavorite(1,10);
        favoriteService.deleteFavorite(1, 1);
    }
}
