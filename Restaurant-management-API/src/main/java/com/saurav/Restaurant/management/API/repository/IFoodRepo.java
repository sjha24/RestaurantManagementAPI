package com.saurav.Restaurant.management.API.repository;

import com.saurav.Restaurant.management.API.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFoodRepo extends JpaRepository<Food,Integer> {
    List<Food> findByFoodName(String foodName);
}
