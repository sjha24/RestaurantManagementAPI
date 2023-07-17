package com.saurav.Restaurant.management.API.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService {
    public boolean isValidEmail(String email){
        if(email != null){
            Pattern pattern = Pattern.compile("^\\S+@admin\\.com$");
            Matcher matcher = pattern.matcher(email);
            if(matcher.find() && matcher.group().equals(email)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
