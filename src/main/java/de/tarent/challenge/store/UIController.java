package de.tarent.challenge.store;

import de.tarent.challenge.store.cart.Cart;
import de.tarent.challenge.store.cart.CartService;
import de.tarent.challenge.store.depot.DepotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UIController {


    private final CartService cartService;

    private final DepotService depotService;

    public UIController(CartService cartService, DepotService depotService) {
        Assert.notNull(cartService, "CartService should be not null");
        Assert.notNull(depotService, "DepotService should be not null");
        this.cartService = cartService;
        this.depotService = depotService;
    }

    @GetMapping("/ui")
    public String list(Model model) {
        List<Cart> carts = cartService.retrieveAll();
        model.addAttribute("carts", carts);
        return "carts";
    }


    //
    @GetMapping("/showCart/{id}")
    public String showCart(@PathVariable Long id, Model model) {
        Cart cart = cartService.retrieveById(id).get();
        System.out.println("DEBUG    " + cart.getCartItems().size());
        model.addAttribute("cart", cart);
        return "showCart";
    }

}