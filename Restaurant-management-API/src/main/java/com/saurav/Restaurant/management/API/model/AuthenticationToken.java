package com.saurav.Restaurant.management.API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenID;
    private String token;
    private LocalDateTime tokenCreationDateTime;
    @OneToOne
    @JoinColumn(name = "fk_user_ID")
    Users user;

    public AuthenticationToken(String token, LocalDateTime tokenCreationDateTime, Users user) {
        this.token = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
        this.user = user;
    }

    public AuthenticationToken(Users user) {
        this.user = user;
    }

}
