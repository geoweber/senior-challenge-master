package de.tarent.challenge.store.products;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    private String sku;

    private BigDecimal price;


    @ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty
   // @CollectionTable(name = "PRODUCT_EANS", joinColumns = @JoinColumn(name = "product_id"))
    private Set<String> eans;

    private Product() {
    }

    public Product(String sku, String name, Set<String> eans, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        this.price = price;
    }


    /*
    public Set<String> getEans() {
        return Sets.newHashSet(eans);
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(sku, product.sku) &&
                Objects.equals(name, product.name) &&
                Objects.equals(eans, product.eans) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, eans, price);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .add("price", price)
                .toString();
    }
}
