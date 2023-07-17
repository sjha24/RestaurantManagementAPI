package com.saurav.Restaurant.management.API.repository;

import com.saurav.Restaurant.management.API.model.Food;
import com.saurav.Restaurant.management.API.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserTypeRepo extends JpaRepository<UserType,Integer> {
}
