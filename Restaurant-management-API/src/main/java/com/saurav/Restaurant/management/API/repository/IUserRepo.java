package com.saurav.Restaurant.management.API.repository;

import com.saurav.Restaurant.management.API.model.Food;
import com.saurav.Restaurant.management.API.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<Users,Integer> {
    Users findFirstByUserEmail(String email);
}
