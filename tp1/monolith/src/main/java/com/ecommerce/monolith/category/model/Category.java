package com.ecommerce.monolith.category.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.ecommerce.monolith.product.model.Product;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    
    private List<Product> products;
}
