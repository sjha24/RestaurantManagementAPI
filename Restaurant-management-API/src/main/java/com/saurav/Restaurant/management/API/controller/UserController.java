package com.saurav.Restaurant.management.API.controller;
import com.saurav.Restaurant.management.API.dto.SignInInput;
import com.saurav.Restaurant.management.API.dto.SignInOutput;
import com.saurav.Restaurant.management.API.dto.SignUpInput;
import com.saurav.Restaurant.management.API.dto.SignUpOutput;
import com.saurav.Restaurant.management.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpInput) throws NoSuchAlgorithmException {
        return userService.signUp(signUpInput);
    }
    @PostMapping("/signIn")
    public SignInOutput sigIn(@RequestBody SignInInput signInInput) throws NoSuchAlgorithmException {
        return userService.signIn(signInInput);
    }
}
