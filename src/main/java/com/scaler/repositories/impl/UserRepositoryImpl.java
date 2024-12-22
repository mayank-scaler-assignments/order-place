package com.scaler.repositories.impl;

import com.scaler.models.User;
import com.scaler.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public Optional<User> findById(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }
}
