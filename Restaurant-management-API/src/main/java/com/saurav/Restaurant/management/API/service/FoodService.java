package com.saurav.Restaurant.management.API.service;
import com.saurav.Restaurant.management.API.model.AuthenticationToken;
import com.saurav.Restaurant.management.API.model.Food;
import com.saurav.Restaurant.management.API.repository.IAuthTokenRepo;
import com.saurav.Restaurant.management.API.repository.IFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    IFoodRepo foodRepo;
    @Autowired
    IAuthTokenRepo authTokenRepo;
    public List<Food> getAllFoods(String foodName) {
        if(foodName == null){
            return foodRepo.findAll();
        }else{
            return foodRepo.findByFoodName(foodName);
        }
    }

    public void deleteFood(Integer foodId, String token) {
        AuthenticationToken authenticationToken = authTokenRepo.findFirstByToken(token);
        if(authenticationToken.getUser().getUserType().equals("ADMIN")){
            foodRepo.deleteById(foodId);
        }
    }

    public void addFoods(List<Food> foodList,String token ) {
        AuthenticationToken authenticationToken = authTokenRepo.findFirstByToken(token);

        if(authenticationToken.getUser().getUserType().equals("ADMIN")){
           foodRepo.saveAll(foodList);
        }
    }
}
