package com.ecommerce.monolith.order.service;

import com.ecommerce.monolith.order.dto.OrderRequest;
import com.ecommerce.monolith.order.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderRequest request);
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
    OrderDTO getOrderById(Long id);
}
