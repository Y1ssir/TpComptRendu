package com.ecommerce.monolith.customer.service;

import com.ecommerce.monolith.customer.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO getCustomerById(Long id);
    boolean existsById(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
}