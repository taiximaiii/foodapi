package com.example.foodapi.repository;

import com.example.foodapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query(value = "select i from Item i")
    List<Item> findAllItems();
}
