package de.tarent.challenge.store;

import de.tarent.challenge.store.cart.Cart;
import de.tarent.challenge.store.cart.CartNotFoundException;
import de.tarent.challenge.store.cart.CartService;
import de.tarent.challenge.store.depot.Depot;
import de.tarent.challenge.store.depot.DepotService;
import de.tarent.challenge.store.products.Product;
import de.tarent.challenge.store.products.ProductNotFoundException;
import de.tarent.challenge.store.products.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UIController {


    private final CartService cartService;

    private final DepotService depotService;

    private final ProductService productService;

    public UIController(CartService cartService, DepotService depotService, ProductService productService) {
        Assert.notNull(cartService, "CartService should be not null");
        Assert.notNull(depotService, "DepotService should be not null");
        Assert.notNull(depotService, "ProductService should be not null");
        this.cartService = cartService;
        this.depotService = depotService;
        this.productService = productService;
    }

    @GetMapping("/ui")
    public String ui() {
        return "ui";
    }

    /**
     * List of products
     *
     * @param model -a holder for model attributes.
     * @return -outcome
     */
    @GetMapping("/ui_products")
    public String listProducts(Model model) {
        List<Product> products = productService.retrieveAll();
        model.addAttribute("products", products);
        return "products";
    }

    /**
     * list of List of available  products
     *
     * @param model -a holder for model attributes.
     * @return -outcome
     */
    @GetMapping("/ui_depot")
    public String listDepot(Model model) {
        List<Depot> depots = depotService.getAllAvailableProducts();
        model.addAttribute("depots", depots);
        return "depots";
    }


    /**
     * list of carts (all)
     *
     * @param model a holder for model attributes.
     * @return outcome
     */
    @GetMapping("/ui_carts")
    public String listAll(Model model) {
        List<Cart> carts = cartService.retrieveAll();
        model.addAttribute("title", "List of carts");
        model.addAttribute("carts", carts);
        return "carts";
    }


    /**
     * list of 'checked out' carts
     *
     * @param model a holder for model attributes.
     * @return outcome
     */
    @GetMapping("/ui_checked_carts")
    public String listCheked(Model model) {
        List<Cart> carts = cartService.retrieveByCheckedOut(true);
        model.addAttribute("title", "List of 'checked out'  carts");
        model.addAttribute("carts", carts);
        return "carts";
    }


    /***
     * detailed view for selected cart
     *
     * @param id - id of cart
     * @param model -a holder for model attributes.
     * @return outcome
     */
    @GetMapping("/showCart/{id}")
    public String showCart(@PathVariable Long id, Model model) {
        Cart cart = cartService.retrieveById(id).orElseThrow(() -> new CartNotFoundException("Cart not found, id=" + id));
        model.addAttribute("cart", cart);
        return "cart";
    }


    /**
     * detailed view for selected product
     *
     * @param id    - id of product
     * @param model -a holder for model attributes.
     * @return outcome
     */
    @GetMapping("/showProduct/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        Product product = productService.retrieveById(id).orElseThrow(() -> new ProductNotFoundException("Product not found, id=" + id));
        model.addAttribute("product", product);
        return "product";
    }

}