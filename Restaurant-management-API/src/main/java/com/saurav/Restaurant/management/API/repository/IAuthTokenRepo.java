package com.saurav.Restaurant.management.API.repository;

import com.saurav.Restaurant.management.API.model.AuthenticationToken;
import com.saurav.Restaurant.management.API.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByToken(String token);
}
