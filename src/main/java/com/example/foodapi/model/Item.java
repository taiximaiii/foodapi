package com.example.foodapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private String description;
    private String imageUrl;
    private String category;
    private int quantity;
    private double cost;

    @ManyToMany(mappedBy = "items")
    @JsonManagedReference
    @JsonIgnore
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();


}