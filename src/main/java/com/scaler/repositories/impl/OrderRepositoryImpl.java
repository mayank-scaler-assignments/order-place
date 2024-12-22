package com.scaler.repositories.impl;

import com.scaler.models.Order;
import com.scaler.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    List<Order> orders;

    public OrderRepositoryImpl() {
        this.orders = new ArrayList<>();
    }

    @Override
    public Order save(Order order) {
        return order;
    }
}
