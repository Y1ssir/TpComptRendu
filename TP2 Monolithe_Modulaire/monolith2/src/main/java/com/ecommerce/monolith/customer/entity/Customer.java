package com.ecommerce.monolith.customer.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.ecommerce.monolith.product.model.Product;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = true)
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<Product> products;
}
