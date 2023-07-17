package com.saurav.Restaurant.management.API.controller;

import com.saurav.Restaurant.management.API.model.UserType;
import com.saurav.Restaurant.management.API.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userType")
public class UserTypeController {
    @Autowired
    UserTypeService userTypeService;
    @PostMapping("/{email}")
    public String addUserType(@RequestBody UserType userType, @PathVariable String email){
        return userTypeService.addUserType(userType,email);
    }
}
