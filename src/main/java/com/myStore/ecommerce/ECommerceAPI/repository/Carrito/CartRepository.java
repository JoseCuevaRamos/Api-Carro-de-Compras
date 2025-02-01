package com.myStore.ecommerce.ECommerceAPI.repository.Carrito;

import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Cart;
import com.myStore.ecommerce.ECommerceAPI.model.Login.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
