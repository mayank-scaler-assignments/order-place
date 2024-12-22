package com.scaler.repositories.impl;

import com.scaler.models.CustomerSession;
import com.scaler.repositories.CustomerSessionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository {

    List<CustomerSession> customerSessionList;

    public CustomerSessionRepositoryImpl() {
        this.customerSessionList = new ArrayList<>();
    }

    @Override
    public CustomerSession save(CustomerSession customerSession) {
        customerSessionList.add(customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return customerSessionList.stream().filter(customerSession ->
                customerSession.getId() == userId && customerSession.isActive()).findFirst();
    }
}
