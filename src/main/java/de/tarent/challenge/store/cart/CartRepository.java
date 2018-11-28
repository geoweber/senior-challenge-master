package de.tarent.challenge.store.cart;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * get a list of cart with certainly  checkedOut
     *
     * @param checkedOut
     * @return list of cart
     */
    List<Cart> findByCheckedOutOrderByCheckedDateDesc(boolean checkedOut);
}
