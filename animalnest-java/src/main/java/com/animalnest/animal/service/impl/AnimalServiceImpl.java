package com.animalnest.animal.service.impl;


import com.animalnest.animal.dao.AnimalDao;
import com.animalnest.animal.entity.Animal;
import com.animalnest.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalDao animalDao;

    public List<Animal> selectAll() {
        return animalDao.selectAll();
    }

    public Animal selectById(Integer id) {
        return animalDao.selectById(id);
    }
}
