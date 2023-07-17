package com.saurav.Restaurant.management.API.controller;

import com.saurav.Restaurant.management.API.model.Food;
import com.saurav.Restaurant.management.API.service.AuthTokenService;
import com.saurav.Restaurant.management.API.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    AuthTokenService authTokenService;
    @PostMapping("/{email}/{token}")
    public ResponseEntity<String>addFood(@RequestBody List<Food> foodList, @PathVariable String email, @PathVariable String token){
        String message = "";
        HttpStatus status;
        if(authTokenService.authenticate(email, token)){
            foodService.addFoods(foodList,token);
            message = "Food added successfully";
            status = HttpStatus.ACCEPTED;
        }else {
            message = "Invalid user";
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(message,status);
    }
    @GetMapping("/{foodName}")
    public List<Food>getAllFoods(@PathVariable String foodName){
        return foodService.getAllFoods(foodName);
    }
    @DeleteMapping("/{foodId}")
    public ResponseEntity<String>removeFood(@PathVariable Integer foodId,@RequestParam String email, @RequestParam String token){
        String message = "";
        HttpStatus status;
        if(authTokenService.authenticate(email, token)){
            foodService.deleteFood(foodId,token);
            message = "Food deleted successfully";
            status = HttpStatus.ACCEPTED;
        }else {
            message = "Invalid user";
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(message,status);
    }
}
