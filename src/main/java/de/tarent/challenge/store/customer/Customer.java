package de.tarent.challenge.store.customer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;


    protected Customer() {
        super();
    }

    public Customer(Long id, String name, String password) {
        this.id=id;
        this.name = name;
        this.password = password;
    }
}
