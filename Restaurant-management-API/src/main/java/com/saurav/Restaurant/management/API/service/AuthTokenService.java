package com.saurav.Restaurant.management.API.service;
import com.saurav.Restaurant.management.API.model.AuthenticationToken;
import com.saurav.Restaurant.management.API.model.Users;
import com.saurav.Restaurant.management.API.repository.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthTokenService {
    @Autowired
    IAuthTokenRepo authTokenRepo;

    public void saveToken(String token, LocalDateTime tokenCreationDateTime, Users user) {
        authTokenRepo.save(new AuthenticationToken(token, tokenCreationDateTime, user));
    }
    public boolean authenticate(String email, String token) {
       AuthenticationToken authToken = authTokenRepo.findFirstByToken(token);
       if(authToken == null){
           return false;
       }
       String userEmail = authToken.getUser().getUserEmail();
       return userEmail.equals(email);
    }
    public void deleteToken(String token){
        AuthenticationToken authenticationToken = authTokenRepo.findFirstByToken(token);
        authTokenRepo.deleteById(authenticationToken.getTokenID());
    }

}
