package com.saurav.Restaurant.management.API.model;
import com.saurav.Restaurant.management.API.model.enums.orderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;
    private Integer orderQuantity;
    @Enumerated(EnumType.STRING)
    private orderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "fk_user_ID")
    Users user;
    @OneToMany
    @JoinColumn(name = "fk_order_ID")
    List<Food>foods;
}
