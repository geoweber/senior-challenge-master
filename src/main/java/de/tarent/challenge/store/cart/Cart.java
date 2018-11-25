package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


/**
 * cart
 */
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty
    private Set<CartItem> products;

    private boolean checked;


}
