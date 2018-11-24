package de.tarent.challenge.store.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    // Aggregate root

    @GetMapping("/products")
    List<Product> all() {
        return productService.retrieveAllProducts();
    }

    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) throws Throwable {

        return productService.retrieveProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found, id="+id));
    }



    /*
    @GetMapping("/products")
    public String retrieveProducts(Model model) {


        model.addAttribute("products", productService.retrieveAllProducts());


        return "products";
    }

    @GetMapping("/getbysku/{sku}")
    public String retrieveProductBySku(@PathVariable String sku, Model model) {


        model.addAttribute("product", productService.retrieveProductBySku(sku));


        return "product";
    }

    */

}
