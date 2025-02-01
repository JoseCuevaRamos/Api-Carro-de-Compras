package com.myStore.ecommerce.ECommerceAPI.service;

import com.myStore.ecommerce.ECommerceAPI.model.Carrito.Product;
import com.myStore.ecommerce.ECommerceAPI.repository.Carrito.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 🔥 Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🔥 Obtener producto por ID (ahora devuelve Optional<Product>)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 🔥 Guardar nuevo producto
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 🔥 Actualizar producto (manejo seguro de nulos)
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    existingProduct.setDescription(product.getDescription());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // 🔥 Eliminar producto
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }
}
