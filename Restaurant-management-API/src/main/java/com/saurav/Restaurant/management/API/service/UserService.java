package com.saurav.Restaurant.management.API.service;

import com.saurav.Restaurant.management.API.dto.SignInInput;
import com.saurav.Restaurant.management.API.dto.SignInOutput;
import com.saurav.Restaurant.management.API.dto.SignUpInput;
import com.saurav.Restaurant.management.API.dto.SignUpOutput;
import com.saurav.Restaurant.management.API.model.AuthenticationToken;
import com.saurav.Restaurant.management.API.model.UserType;
import com.saurav.Restaurant.management.API.model.Users;
import com.saurav.Restaurant.management.API.repository.IAuthTokenRepo;
import com.saurav.Restaurant.management.API.repository.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthTokenRepo tokenRepo;
    @Autowired
    UserTypeService userTypeService;
    @Autowired
    AuthTokenService authTokenService;
    public String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();
        String encPassword = DatatypeConverter.printHexBinary(digested);
        return encPassword;
    }
    public SignUpOutput signUp(SignUpInput signUpInput) throws NoSuchAlgorithmException {
        Users user = userRepo.findFirstByUserEmail(signUpInput.getEmail());
        if(user != null){
            throw new IllegalStateException("User already exist !!! please signIn");
        }
        String encryptedPassword = null;
        encryptedPassword = encryptPassword(signUpInput.getPassword());
        if(! userTypeService.validateUserType(signUpInput.getEmail(), signUpInput.getUserType())){
            throw new IllegalStateException("Please Enter valid Detail");
        }
        user = new Users(signUpInput.getUserName(),signUpInput.getEmail(),encryptedPassword,signUpInput.getUserType());
        userRepo.save(user);
        return new SignUpOutput("SignUp successfully","Account Created successfully");
    }

    public SignInOutput signIn(SignInInput signInInput) throws NoSuchAlgorithmException {
        Users user = userRepo.findFirstByUserEmail(signInInput.getEmail());
        if(user == null){
            throw new IllegalStateException("User Invalid !!! Please signUp first");
        }
        String encryptedPassword = null;
        encryptedPassword = encryptPassword(signInInput.getPassword());
        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());
        if(! isPasswordValid){
            throw new IllegalStateException("Please give me valid user email and Password");
        }

        AuthenticationToken token = new AuthenticationToken();
        authTokenService.saveToken(token.getToken(),token.getTokenCreationDateTime(),user);
        return new SignInOutput("Authentication Successfully",token.getToken());
    }
}
