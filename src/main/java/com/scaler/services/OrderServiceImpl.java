package com.scaler.services;

import com.scaler.exceptions.InvalidMenuItem;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.MenuItemRepository;
import com.scaler.repositories.OrderRepository;
import com.scaler.repositories.UserRepository;

import java.util.*;

public class OrderServiceImpl implements OrderService{

    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;
    private final CustomerSessionRepository customerSessionRepository;

    public OrderServiceImpl(UserRepository userRepository, MenuItemRepository menuItemRepository, OrderRepository orderRepository, CustomerSessionRepository customerSessionRepository) {
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
        this.customerSessionRepository = customerSessionRepository;
    }

    @Override
    public Order placeOrder(long userId, Map<Long, Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        List<MenuItem> menuItems = menuItemRepository.findAllById(new ArrayList<>(orderedItems.keySet()));

        if(menuItems.size() != orderedItems.size()) {
            throw new InvalidMenuItem("Invalid Menu Item");
        }

        CustomerSession customerSession = new CustomerSession();
        customerSession.setUser(user);
        customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);

        customerSession = customerSessionRepository.save(customerSession);

        Map<MenuItem, Integer> orderedItemsMap = new HashMap<>();
        for(MenuItem menuItem : menuItems) {
            orderedItemsMap.put(menuItem, orderedItems.get(menuItem.getId()));
        }

        Order order = new Order();
        order.setOrderedItems(orderedItemsMap);
        order.setCustomerSession(customerSession);

        order = orderRepository.save(order);

        return order;
    }
}
