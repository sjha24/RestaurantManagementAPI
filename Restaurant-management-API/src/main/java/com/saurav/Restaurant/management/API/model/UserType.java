package com.saurav.Restaurant.management.API.model;

import com.saurav.Restaurant.management.API.model.enums.typeOfUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserRoleID;
    @Enumerated(EnumType.STRING)
    private typeOfUser userTypes;
    private String userDesc;
}
