package com.animalnest.animal.service;


import com.animalnest.animal.entity.Animal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AnimalService {

    /**
     * get all animal information
     * @return
     */
    public List<Animal> selectAll();

    /**
     * get animal information by id
     * @param id
     * @return
     */
    public Animal selectById(Integer id);
}
