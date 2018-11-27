package de.tarent.challenge.store.depot;

import de.tarent.challenge.store.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * "Available products" functionality
 */
@Service
public class DepotService {

    private final DepotRepository repository;

    public DepotService(DepotRepository repository) {
        Assert.notNull(repository, "DepotRepository should not be null");
        this.repository = repository;
    }


    /**
     * Speichern neuen Datenbestand. Falls neue Anzahl 0 ist, dann wird der Produkt aus der Depot Tabelle geloescht
     *
     * @param object - neuen Datenbestand fuer bestimmten Produkt
     * @return neuen Datenbestand fuer bestimmten Produkt oder null falls der Prudkt nicht mehr vorhanden
     */
    public Depot save(@NotNull Depot object) {
        if (object.getQuantity() != 0) {
            return repository.save(object);
        }

        Optional<Depot> depot = repository.findById(object.getId());
        depot.ifPresent(repository::delete);
        return null;


    }

    /**
     * get available quantity for product
     * @param product - product
     * @return number
     */
    public int getAvailableQuantityByProduct(Product product) {
        Depot object = repository.getDepotByProduct(product);
        return object != null ? object.getQuantity() : 0;
    }


    /**
     * get all available products
     * @return -list of products
     */
    public List<Depot> getAllAvailableProducts() {
        return repository.findAll();
    }
}
