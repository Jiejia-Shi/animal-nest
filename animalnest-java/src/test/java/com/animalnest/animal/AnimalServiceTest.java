package com.animalnest.animal;

import com.animalnest.animal.entity.Animal;
import com.animalnest.animal.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class AnimalServiceTest {


    @Autowired
    private AnimalService animalService;

    @Test
    void testAnimalService() {
        List<Animal> animals = animalService.selectAll();
        for(int i = 0; i < animals.size(); i++) {
           // System.out.println(animals.get(i));
        }
        Animal animal = animalService.selectById(1);
        System.out.println(animal);

    }
}
