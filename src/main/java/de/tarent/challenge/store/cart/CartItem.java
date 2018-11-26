package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * a row in  cart
 */
@Data
@Entity
public class CartItem {

    /**
     * default constructor fpr hibernate
     */
    private CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.total = product.getPrice() * quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;


    /**
     * Each item can have a specific quantity
     */
    @Positive
    private Integer quantity;

    @Positive
    private Integer total;
}
