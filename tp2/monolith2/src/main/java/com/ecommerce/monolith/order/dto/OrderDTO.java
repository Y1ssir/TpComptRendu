package com.ecommerce.monolith.order.dto;

import com.ecommerce.monolith.customer.dto.CustomerDTO;
import com.ecommerce.monolith.product.dto.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private CustomerDTO customer;
    private List<ProductDTO> products;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
}
