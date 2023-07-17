package com.saurav.Restaurant.management.API.repository;

import com.saurav.Restaurant.management.API.model.Food;
import com.saurav.Restaurant.management.API.model.FoodOrder;
import com.saurav.Restaurant.management.API.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFoodOrderRepo extends JpaRepository<FoodOrder,Integer> {
    List<FoodOrder> findByUser(Users user);
}
