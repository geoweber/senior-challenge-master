package de.tarent.challenge.store.cart;


import de.tarent.challenge.store.depot.DepotService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * RESTful resources for managing carts.
 */
@RestController
public class CartController {


    private final CartService service;

    private final DepotService depotService;

    public CartController(CartService service, DepotService depotService) {
        Assert.notNull(service, "CartService should be not null");
        Assert.notNull(depotService, "DepotService should be not null");
        this.service = service;
        this.depotService = depotService;
    }


    /**
     * create or  update  a cart object
     *
     * @param object - cart to save - must not be {@literal null}.
     * @return the saved cart will never be {@literal null}.
     * @throws CartInvalidException -cart is invalid
     */
    @PostMapping("/cart")
    public Cart save(@RequestBody Cart object) {

        // Validation
        CartValidator.getInstance().validate(object);

        float total=0f;
        //Unavailable products cannot be added to a cart.
        for (CartItem item : object.getCartItems()) {
            int availableQuantityByProduct = depotService.getAvailableQuantityByProduct(item.getProduct());

            if (availableQuantityByProduct < item.getQuantity()) {
                StringBuilder message = new StringBuilder();
                message.append("There are only  ");
                message.append(availableQuantityByProduct);
                message.append("  of product ");
                message.append(item.getProduct().getSku());
                message.append("  is now  available.  Your request - ");
                message.append(item.getQuantity());
                message.append(" could not be  fulfilled. Sorry!");
                throw new CartInvalidException(message.toString());
            }
            // calculate total price
            total+=item.getTotal();
        }

        //TODO Frage: wan andert sich der Lagerbestand? (beim cart save? or  beim cart check out?)


        //save total price
        object.setTotal(total);

        return service.save(object);
    }


    /**
     * Retrieves a cart by its id.
     *
     * @param id must not be {@literal null}.
     * @return the cart with the given id or throw CartNotFoundException if none found
     * @throws CartInvalidException -cart is invalid found
     */
    @GetMapping("/cart/{id}")
    public Cart retrieveById(@PathVariable Long id) {
        return service.retrieveById(id).orElseThrow(() -> new CartNotFoundException("Cart not found, id=" + id));
    }


    /**
     * Returns all carts.
     *
     * @return list of existing carts
     */
    @GetMapping("/carts")
    public List<Cart> all() {
        return service.retrieveAll();
    }


    /**
     * Deletes the given cart.
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