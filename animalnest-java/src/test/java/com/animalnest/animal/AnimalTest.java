package com.animalnest.animal;


import com.animalnest.animal.dao.AnimalDao;
import com.animalnest.animal.entity.Animal;
import com.animalnest.animal.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class AnimalTest {

    @Autowired
    private AnimalDao animalDao;


    @Test
    void testSelectAll() {
        List<Animal> animals = animalDao.selectAll();
        for(int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i));
        }
    }



}
