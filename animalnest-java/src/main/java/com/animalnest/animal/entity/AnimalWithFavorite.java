package com.animalnest.animal.entity;


import lombok.Data;

/**
 * animal with favorite
 */
@Data
public class AnimalWithFavorite extends Animal{
    private boolean favorite = false;

    public AnimalWithFavorite(Animal animal, boolean favorite) {
        this.id = animal.id;
        this.name = animal.name;
        this.color = animal.color;
        this.gender = animal.gender;
        this.health = animal.health;
        this.habitat = animal.habitat;
        this.temperament = animal.temperament;
        this.habit = animal.habit;
        this.picture = animal.picture;

        this.favorite = favorite;
    }
}
