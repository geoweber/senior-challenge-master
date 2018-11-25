package de.tarent.challenge.store.products;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> all() {
        return productService.retrieveAllProducts();
    }


    @GetMapping("/product/{id}")
    public Product retrieveProductById(@PathVariable Long id) {
        return productService.retrieveProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found, id=" + id));
    }


    @GetMapping("/product/sku/{sku}")
    public Product retrieveProductBySku(@PathVariable String sku) {
        return productService.retrieveProductBySku(sku);
    }


    @GetMapping("/product/name/{name}")
    public List<Product> retrieveProductByName(@PathVariable String name) {
        return productService.retrieveProductByName(name);
    }


    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product object) {

        ProductValidator.getInstance().validate(object);

        //  check ob sku  unique
        Product productbySku =  productService.retrieveProductBySku(object.getSku());
        if (productbySku!=null && object.getId()!=null && object.getId().equals(productbySku.getId())) {
            throw new ProductInvalidException("Product.sku is not unique");
        }

        return productService.save(object);
    }


    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (!productService.retrieveProductById(id).isPresent()) return;

        productService.delete(productService.retrieveProductById(id).get());
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
