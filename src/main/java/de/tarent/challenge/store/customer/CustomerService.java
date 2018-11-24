package de.tarent.challenge.store.customer;

import de.tarent.challenge.store.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public List<Customer> list(){
        return  repository.findAll();
    }

    public Customer findById(Long id){
        Optional<Customer> result=repository.findById(id);
        return  result.isPresent()?result.get():null;
    }

    public Customer findByName(String name){
        return  repository.findByName(name);
    }
}
