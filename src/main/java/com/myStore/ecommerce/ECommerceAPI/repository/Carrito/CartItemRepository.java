package com.myStore.ecommerce.ECommerceAPI.repository.Carrito;

import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Cart;
import com.myStore.ecommerce.ECommerceAPI.model.Carrito.CartItem;
import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository  extends JpaRepository<CartItem, Long>{
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
