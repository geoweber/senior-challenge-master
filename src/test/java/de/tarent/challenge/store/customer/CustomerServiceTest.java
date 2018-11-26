package de.tarent.challenge.store.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void list() {
        assertNotNull(service.list());
        assertEquals(1, service.list().size());
    }

    @Test
    void findById() {
        assertEquals("user", service.findById(1L).getName());
    }

    @Test
    void findByName() {
        assertEquals(1L, (long) service.findByName("user").getId());
    }
}