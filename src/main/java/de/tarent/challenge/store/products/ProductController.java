package de.tarent.challenge.store.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * RESTful resources for managing products.
 */
@RestController
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        Assert.notNull(service, "ProductService should be not null");
        this.service = service;
    }

    /**
     * create or  update  a product object
     *
     * @param object - product to save - must not be {@literal null}.
     * @return the saved product will never be {@literal null}.
     * @throws ProductInvalidException - product is invalid
     */

    @PostMapping("/product")
    public Product save(@RequestBody Product object) {

        ProductValidator.getInstance().validate(object);

        //  check ob sku  unique
        Product productBySku = service.retrieveBySku(object.getSku());
        if (productBySku != null && object.getId() != null && object.getId().equals(productBySku.getId()))
            throw new ProductInvalidException("Product.sku is not unique");

        return service.save(object);
    }


    /**
     * Retrieves a product by its id.
     *
     * @param id must not be {@literal null}.
     * @return the product with the given id or throw ProductNotFoundException if none found
     * @throws ProductNotFoundException - product not found
     */
    @GetMapping("/product/{id}")
    public Product retrieveById(@PathVariable Long id) {
        return service.retrieveById(id).orElseThrow(() -> new ProductNotFoundException("Product not found, id=" + id));
    }


    /**
     * Returns all products.
     *
     * @return list of existing products
     */
    @GetMapping("/products")
    public List<Product> all() {
        return service.retrieveAll();
    }


    /**
     * Return product by sky
     *
     * @param sku - sku of product from interest
     * @return product
     */
    @GetMapping("/product/sku/{sku}")
    public Product retrieveBySku(@PathVariable String sku) {
        return service.retrieveBySku(sku);
    }


    /**
     * Get list of products by name
     *
     * @param name - name of product from interest
     * @return -list of products (name is not unique)
     */
    @GetMapping("/product/name/{name}")
    public List<Product> retrieveByName(@PathVariable String name) {
        return service.retrieveByName(name);
    }


    /**
     * Deletes the given cart.
     *
     * @param id from deleted cart
     */
    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return;
        }

        if (service.retrieveById(id).isPresent())
            service.delete(service.retrieveById(id).get());
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
