package de.tarent.challenge.store.cart;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


/**
 * cart entity
 */
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "cart")
    @OrderBy(value = "id asc")
    private Set<CartItem> cartItems;

    private boolean checkedOut;

    private float total;

    @DateTimeFormat(pattern = "HH:mm MM-dd-yyyy")
    private LocalDateTime checkedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return checkedOut == cart.checkedOut &&
                Float.compare(cart.total, total) == 0 &&
                Objects.equals(id, cart.id) &&
                Objects.equals(cartItems, cart.cartItems) &&
                Objects.equals(checkedDate, cart.checkedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItems, checkedOut, total, checkedDate);
    }
}
