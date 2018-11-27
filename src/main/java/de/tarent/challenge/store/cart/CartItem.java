package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;


/**
 * a row in  cart
 */
@Data
@Entity
public class CartItem {

    /**
     * default constructor for hibernate initialisation
     */
    private CartItem() {
    }

    CartItem(Product product, int quantity) {
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

    private float total;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Float.compare(cartItem.total, total) == 0 &&
                Objects.equals(id, cartItem.id) &&
                Objects.equals(product, cartItem.product) &&
                Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, total);
    }
}
