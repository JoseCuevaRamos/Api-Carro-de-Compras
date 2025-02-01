package com.myStore.ecommerce.ECommerceAPI.model.Carrito;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne

    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne

    @JoinColumn(name="cart_id", referencedColumnName = "id")
    @JsonIgnore
    private Cart cart;

    private Integer quantity;



}
