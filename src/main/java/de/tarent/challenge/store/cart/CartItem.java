package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * a row in  cart
 */
@Data
@Entity
public class CartItem {


    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.total=product.getPrice() * quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private int quantity;
    private Integer total;
}
