package com.animalnest.animal.controller;


import com.animalnest.animal.common.R;
import com.animalnest.animal.entity.Animal;
import com.animalnest.animal.entity.AnimalWithFavorite;
import com.animalnest.animal.service.AnimalService;
import com.animalnest.animal.service.FavoriteService;
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
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private FavoriteService favoriteService;


    /**
     * get num_th animal information
     * @param num
     * @return
     */
    @PostMapping("/nextAnimal")
    public R<AnimalWithFavorite> nextAnimal(Integer num, Integer userId) {
        // get animal information
        List<Animal> animals = animalService.selectAll();
        int n = animals.size();
        Integer index = (num - 1) % n;
        Animal animal = animals.get(index);
        // if this user sets this animal favorite
        List<Integer> favoriteAnimals = favoriteService.selectByUserId(userId);
        boolean favo = false;
        if(favoriteAnimals.contains(animal.getId().intValue())) favo = true;
        // return result
        AnimalWithFavorite aniWithFavo = new AnimalWithFavorite(animal, favo);
        return R.success(aniWithFavo);
    }

    /**
     * get animal information based on animalId
     * @param animalId
     * @return
     */
    @PostMapping("/favoriteAnimal")
    public R<AnimalWithFavorite> favoriteAnimal(Integer animalId) {
        Animal animal = animalService.selectById(animalId);
        AnimalWithFavorite resanimal = new AnimalWithFavorite(animal, true);
        return R.success(resanimal);
    }

    /**
     * get all animal information
     * @return
     */
    @PostMapping("/animalInfo")
    public R<List<Animal>> animalInfo() {
        List<Animal> animals = animalService.selectAll();
        return R.success(animals);
    }

    /**
     * get all favorite animals of a user
     * @param userId
     * @return
     */
    @PostMapping("/favoriteAnimals")
    public R<List<Animal>> favoritaAnimals(Integer userId) {
        List<Animal> favoAnimals = new LinkedList<Animal>();
        List<Integer> animalsId = favoriteService.selectByUserId(userId);
        for(Integer i : animalsId) {
            Animal favoAnimal = animalService.selectById(i);
            favoAnimals.add(favoAnimal);
        }
        if(favoAnimals.size() == 0) return R.error("暂未关心任何流浪动物");
        return R.success(favoAnimals);
    }


}
