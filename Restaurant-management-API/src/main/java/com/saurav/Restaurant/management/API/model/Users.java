package com.saurav.Restaurant.management.API.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    @ManyToOne
    @JoinColumn(name = "fk_userType_ID")
    UserType userType;
    public Users(String userName, String userEmail, String userPassword, UserType userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
    }
}
