package de.tarent.challenge.store.cart;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
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

    //@ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CartItem> products;

    private boolean checkedOut;

    private LocalDate checkedDate;


}
