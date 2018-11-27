package de.tarent.challenge.store.depot;

import de.tarent.challenge.store.products.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

/**
 * store
 */
@Data
@Entity
public class Depot {
    /**
     * default constructor for hibernate initialisation
     */
    private Depot() {
    }

    public Depot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;


    /**
     * Each item can have a specific quantity
     */
    @Positive
    private int quantity;
}
