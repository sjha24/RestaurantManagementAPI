package com.saurav.Restaurant.management.API.service;

import com.saurav.Restaurant.management.API.model.AuthenticationToken;
import com.saurav.Restaurant.management.API.model.FoodOrder;
import com.saurav.Restaurant.management.API.model.enums.orderStatus;
import com.saurav.Restaurant.management.API.repository.IAuthTokenRepo;
import com.saurav.Restaurant.management.API.repository.IFoodOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {
    @Autowired
    IFoodOrderRepo orderRepo;
    @Autowired
    IAuthTokenRepo tokenRepo;
    @Autowired
    AdminService adminService;
    public void placeOrder(FoodOrder foodOrder, String token) {
        AuthenticationToken authenticationToken = tokenRepo.findFirstByToken(token);
        foodOrder.setOrderStatus(orderStatus.ORDER_RECEIVED);
        foodOrder.setUser(authenticationToken.getUser());
        orderRepo.save(foodOrder);
    }

    public List<FoodOrder> getOrders(String email, String token) {
        AuthenticationToken authenticationToken = tokenRepo.findFirstByToken(token);
        if(authenticationToken.getUser().getUserType().getUserRoleID()==1){
            return orderRepo.findAll();
        }else{
            List<FoodOrder>orderList = new ArrayList<>();
            orderList = orderRepo.findByUser(authenticationToken.getUser());
            return orderList;
        }
    }

    public void updateOrder(String email, String token, FoodOrder foodOrder) {
        AuthenticationToken authenticationToken = tokenRepo.findFirstByToken(token);
        if(foodOrder.getUser().equals(authenticationToken.getUser()) || adminService.isValidEmail(email)){
            if(foodOrder.getOrderID() != null){
                Optional<FoodOrder> updateOrder = orderRepo.findById(foodOrder.getOrderID());
                if(updateOrder.isPresent()){
                    FoodOrder existOrder = updateOrder.get();
                    if(foodOrder.getFoods() != null){
                        existOrder.setFoods(foodOrder.getFoods());
                    }
                    if(foodOrder.getOrderQuantity() != null){
                        existOrder.setOrderQuantity(foodOrder.getOrderQuantity());
                    }
                    if(foodOrder.getOrderStatus() != null){
                        existOrder.setOrderStatus(foodOrder.getOrderStatus());
                    }
                    orderRepo.save(existOrder);
                }else {
                    throw new IllegalStateException("No such order exist");
                }
            }else {
                throw new IllegalStateException("Enter valid order ID");
            }
        }else{
            throw new IllegalStateException("Please give me Valid detail");
        }
    }
}
