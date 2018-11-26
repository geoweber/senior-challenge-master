package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {


    private final CartService service;

    private final ProductService productService;

    public CartController(CartService service, ProductService productService) {
        Assert.notNull(service, "CartService should be not null");
        Assert.notNull(productService, "ProductService should be not null");
        this.service = service;
        this.productService = productService;
    }

    //Create a new cart / Update a cart

    @PostMapping("/cart")
    public Cart save(@RequestBody Cart object) {

        //CHECK: Carts that have been checked out cannot be changed anymore.
        if (object != null && object.getId() != null) {

            // get existing cart from db
            Optional<Cart> optCart = service.retrieveById(object.getId());
            if (optCart.isPresent() && optCart.get().isCheckedOut())
                throw new CartNotUpdatableException("Cart (id=" + object.getId() + " have been checked out and cannot be changed anymore.");
        }
        CartValidator.getInstance().validate(object);


        return service.save(object);
    }


    /**
     * Get cart by id
     *
     * @param id - id of cart from interest
     * @return cart by id
     */
    @GetMapping("/cart/{id}")
    public Cart retrieveById(@PathVariable Long id) {
        return service.retrieveById(id).orElseThrow(() -> new CartNotFoundException("Cart not found, id=" + id));
    }

    /**
     * Get all existing cart
     *
     * @return list of products
     */
    @GetMapping("/carts")
    public List<Cart> all() {
        return service.retrieveAll();
    }


    /**
     * Delete cart by id
     *
     * @param id from deleted cart
     */
    @DeleteMapping("/cart/{id}")
    public void delete(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return;
        }

        if (service.retrieveById(id).isPresent())
            service.delete(service.retrieveById(id).get());
    }
}
