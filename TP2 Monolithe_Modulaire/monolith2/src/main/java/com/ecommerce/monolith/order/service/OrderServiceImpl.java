package com.ecommerce.monolith.order.service;

import com.ecommerce.monolith.customer.service.CustomerService;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.dto.OrderRequest;
import com.ecommerce.monolith.order.entity.Order;
import com.ecommerce.monolith.order.repository.OrderRepository;
import com.ecommerce.monolith.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Override
    public OrderDTO createOrder(OrderRequest request) {
        if (!customerService.existsById(request.getCustomerId())) {
            throw new RuntimeException("Impossible de créer la commande : Client inexistant");
        }
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductIds(request.getProductIds());
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return mapToOrderDTO(savedOrder);
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToOrderDTO)
                .collect(Collectors.toList());

    }
    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID : " + id));
        return mapToOrderDTO(order);
    }
    private OrderDTO mapToOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setCustomer(customerService.getCustomerById(order.getCustomerId()));
        dto.setProducts(order.getProductIds().stream()
                .map(productService::getProductById)
                .collect(Collectors.toList()));
        return dto;
    }
}