package com.myStore.ecommerce.ECommerceAPI.repository.Carrito;

import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 🔥 Si necesitas buscar un producto por nombre
    boolean existsByName(String name);
}
