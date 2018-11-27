package de.tarent.challenge.store.depot;

import de.tarent.challenge.store.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface DepotRepository extends JpaRepository<Depot, Long> {

    @Query("select d from Depot d where d.product =?1")
    Depot getDepotByProduct(Product product);

}
