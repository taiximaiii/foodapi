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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;


    @ManyToMany
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
            item.getCarts().add(this);
        }
    }
    public void removeAllItemsWithItemId(Long itemId) {
        List<Item> itemsToRemove = new ArrayList<>();
        for (Item item : items) {
            if (item.getItemId().equals(itemId)) {
                itemsToRemove.add(item);
            }
        }
        items.removeAll(itemsToRemove);
    }

}
