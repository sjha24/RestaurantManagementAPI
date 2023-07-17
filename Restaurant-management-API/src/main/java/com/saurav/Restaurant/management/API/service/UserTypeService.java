package com.saurav.Restaurant.management.API.service;

import com.saurav.Restaurant.management.API.model.UserType;
import com.saurav.Restaurant.management.API.repository.IUserTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserTypeService {
    @Autowired
    IUserTypeRepo userTypeRepo;
    @Autowired
    AdminService adminService;
    public String addUserType(UserType userType, String email) {
        if(adminService.isValidEmail(email)){
            userTypeRepo.save(userType);
            return "User type added successfully";
        }else{
            return "You Have not authority to add User Type";
        }
    }
    public boolean validateUserType(String email, UserType userType){
        if(userType.getUserRoleID()==1){
            Pattern pattern = Pattern.compile("^\\S+@admin\\.com$");
            Matcher matcher = pattern.matcher(email);
            if(matcher.find() && matcher.group().equals(email)){
                return true;
            }
        }else{
            if(userType.getUserRoleID() == 2){
                return true;
            }
        }
        return false;
    }
}
