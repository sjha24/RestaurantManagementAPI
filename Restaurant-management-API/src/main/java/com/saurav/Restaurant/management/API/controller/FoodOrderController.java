package com.saurav.Restaurant.management.API.controller;

import com.saurav.Restaurant.management.API.model.FoodOrder;
import com.saurav.Restaurant.management.API.service.AuthTokenService;
import com.saurav.Restaurant.management.API.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food/order")
public class FoodOrderController {
    @Autowired
    FoodOrderService foodOrderService;
    @Autowired
    AuthTokenService authTokenService;
    @PostMapping
    public ResponseEntity<String>placeOrder(@RequestBody FoodOrder foodOrder, @PathVariable String token, @PathVariable String email){
        HttpStatus status;
        String message = "";
        if(authTokenService.authenticate(email,token)){
            foodOrderService.placeOrder(foodOrder,token);
            message = "Your order is successfully accepted thank you for order";
            status = HttpStatus.OK;
        }else{
            message = "Invalid user";
            status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(message,status);
    }
    @GetMapping
    public ResponseEntity<List<FoodOrder>>getOrders(@PathVariable String email,@PathVariable String token){
        String message = "";
        HttpStatus status;
        List<FoodOrder>foodOrderList = null;
        if(authTokenService.authenticate(email,token)){
            foodOrderList = foodOrderService.getOrders(email,token);
            status = HttpStatus.ACCEPTED;
        }else {
            status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(foodOrderList,status);
    }
    @PutMapping
    public ResponseEntity<String>updateYourOrder(@RequestBody FoodOrder foodOrder,@RequestParam String email, @RequestParam String token){
        String message = "";
        HttpStatus status;
        if(authTokenService.authenticate(email, token)){
            foodOrderService.updateOrder(email,token,foodOrder);
            message = "Your updated order is accepted";
            status = HttpStatus.ACCEPTED;
        }else{
            message = "Invalid user";
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(message,status);
    }
}
