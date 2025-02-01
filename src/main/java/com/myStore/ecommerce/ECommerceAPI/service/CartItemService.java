package com.myStore.ecommerce.ECommerceAPI.service;


import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Cart;
import com.myStore.ecommerce.ECommerceAPI.model.Carrito.CartItem;
import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Product;
import com.myStore.ecommerce.ECommerceAPI.repository.Carrito.CartItemRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Optional;



@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartItemService(CartItemRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    @Transactional
    public CartItem addCartItem(Cart cart, Long productId, int quantity) {
        // 🔥 Ahora `getProductById()` devuelve un Optional, y podemos usar orElseThrow()
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock insuficiente");
        }

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            return cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public void removeCartItem(Cart cart, Long productId) {
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
    }
}
