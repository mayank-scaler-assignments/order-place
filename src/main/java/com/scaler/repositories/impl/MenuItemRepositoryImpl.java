package com.scaler.repositories.impl;

import com.scaler.models.MenuItem;
import com.scaler.repositories.MenuItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuItemRepositoryImpl implements MenuItemRepository {

    List<MenuItem> menuItems;

    private int counter = 0;

    public MenuItemRepositoryImpl() {
        this.menuItems = new ArrayList<>();
    }

    @Override
    public MenuItem add(MenuItem menuItem) {
        menuItem.setId(counter++);
        menuItems.add(menuItem);
        return menuItem;
    }

    @Override
    public Optional<MenuItem> findById(long id) {
        return menuItems.stream().filter(menuItem -> menuItem.getId() == id).findFirst();
    }

    @Override
    public List<MenuItem> findAllById(List<Long> ids) {
        return menuItems.stream()
                .filter(menuItem -> {
                    return ids.contains(menuItem.getId());
                })
                .collect(Collectors.toList());
    }
}
