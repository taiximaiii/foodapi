package com.example.foodapi.controller;

import com.example.foodapi.dto.CartItemDto;
import com.example.foodapi.security.UserPrincipal;
import com.example.foodapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/add-item")
    public ResponseEntity<String> addItemToCart(@RequestParam Long itemId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        cartService.addItemToCart(userPrincipal.getId(), itemId);
        return ResponseEntity.ok("Item added to cart successfully.");
    }
    @DeleteMapping("/remove-item")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Long itemId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        cartService.removeItemFromCart(userPrincipal.getId(), itemId);
        return ResponseEntity.ok("Item removed from cart successfully.");
    }
    @GetMapping("/get-cart")
    public ResponseEntity<List<CartItemDto>> getCartItemsWithDetails(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<CartItemDto> cartItemsWithDetails = cartService.getCartItemsWithDetails(userPrincipal.getId());

        if (cartItemsWithDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cartItemsWithDetails);
        }
    }
}
