package com.example.foodapi.service;

import com.example.foodapi.dto.CartItemDto;
import com.example.foodapi.dto.OrderDetailDto;
import com.example.foodapi.dto.OrderItemDto;
import com.example.foodapi.model.Cart;
import com.example.foodapi.model.Item;
import com.example.foodapi.model.Order;
import com.example.foodapi.repository.CartRepository;
import com.example.foodapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void placeOrder(Long customerId) {
        Cart cart = cartRepository.findByCustomerCustomerId(customerId);
        if (cart != null) {
            Order order = new Order();
            order.setOrderDate(new Date());
            order.setOrderStatus("Pending");
            order.setCustomer(cart.getCustomer());
            List<Item> itemsInCart = cart.getItems();
            List<Item> clonedItems = new ArrayList<>();
            for (Item item : itemsInCart) {
                Item clonedItem = new Item();
                clonedItem.setItemId(item.getItemId());
                clonedItems.add(clonedItem);
            }
            order.setItems(clonedItems);
            orderRepository.save(order);
            cart.setItems(null);
            cartRepository.save(cart);
        }
    }
    @Override
    public List<Order> getAllOrder(Long customerId){
        return orderRepository.findAllByCustomerCustomerId(customerId);
    }

    @Override
    public OrderDetailDto getOrderDetailById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        if (order != null) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrderStatus(order.getOrderStatus());
            orderDetailDto.setOrderDate(order.getOrderDate());

            Map<Item, Integer> itemCountMap = new HashMap<>();
            for (Item item : order.getItems()) {
                itemCountMap.put(item, itemCountMap.getOrDefault(item, 0) + 1);
            }

            for (Item item : itemCountMap.keySet()) {
                int quantity = itemCountMap.get(item);
                double totalPrice = item.getCost() * quantity;

                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setItemName(item.getItemName());
                orderItemDto.setQuantity(quantity);
                orderItemDto.setTotalPrice(totalPrice);
                orderItemDto.setImageUrl(item.getImageUrl());
                orderItemDtos.add(orderItemDto);
            }
            orderDetailDto.setOrderItems(orderItemDtos);
            return orderDetailDto;
        }

        return null;
    }
    @Override
    public void confirmPayment(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if ("Pending".equals(order.getOrderStatus())) {
                order.setOrderStatus("Success");
                orderRepository.save(order);
            }
        }
    }
}

