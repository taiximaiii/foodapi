package com.example.foodapi.service;

import com.example.foodapi.dto.CartItemDto;
import com.example.foodapi.model.Cart;
import com.example.foodapi.model.Customer;
import com.example.foodapi.model.Item;
import com.example.foodapi.repository.CartRepository;
import com.example.foodapi.repository.CustomerRepository;
import com.example.foodapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void addItemToCart(Long customerId, Long itemId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return;
        }
        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            return;
        }
        cart.addItem(item);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Long customerId, Long itemId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return;
        }
        Cart cart = customer.getCart();

        if (cart == null) {
            return;
        }
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            return;
        }
        cart.removeAllItemsWithItemId(itemId);
        cartRepository.save(cart);
        itemRepository.save(item);
    }
    @Override
    public List<CartItemDto> getCartItemsWithDetails(Long customerId) {
        List<CartItemDto> cartItemDTOs = new ArrayList<>();

        Cart cart = cartRepository.findByCustomerCustomerId(customerId);

        if (cart != null) {
            Map<Item, Integer> itemCountMap = new HashMap<>();

            for (Item item : cart.getItems()) {
                itemCountMap.put(item, itemCountMap.getOrDefault(item, 0) + 1);
            }

            for (Item item : itemCountMap.keySet()) {
                int quantity = itemCountMap.get(item);
                double totalPrice = item.getCost() * quantity;

                CartItemDto cartItemDTO = new CartItemDto();
                cartItemDTO.setItemName(item.getItemName());
                cartItemDTO.setQuantity(quantity);
                cartItemDTO.setTotalPrice(totalPrice);

                cartItemDTOs.add(cartItemDTO);
            }
        }

        return cartItemDTOs;
    }
}
