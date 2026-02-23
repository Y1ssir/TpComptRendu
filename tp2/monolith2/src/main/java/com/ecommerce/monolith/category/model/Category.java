package com.ecommerce.monolith.category.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.ecommerce.monolith.product.model.Product;

@Entity
@Data // Génère les Getters/Setters grâce à Lombok
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Cette ligne permet de voir tous les produits d'une catégorie
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}