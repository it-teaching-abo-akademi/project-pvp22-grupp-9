package com.example.pos_system_version_xx.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product banana = new Product("Banana", "12345");
    Product apple = new Product("Apple", "23456");
    Product bread = new Product("Bread", "34567");


    @Test
    void getSetDiscount() {
        assertEquals(0,banana.getDiscount());
        assertEquals(0,apple.getDiscount());
        assertEquals(0,bread.getDiscount());
        banana.setDiscount(20);
        apple.setDiscount(30);
        bread.setDiscount(40);
        assertEquals(20,banana.getDiscount());
        assertEquals(30,apple.getDiscount());
        assertEquals(40,bread.getDiscount());
    }

    @Test
    void getName() {
        assertEquals("Banana", banana.getName());
        assertEquals("Apple", apple.getName());
        assertEquals("Bread", bread.getName());
    }

    @Test
    void getBarcode() {
        assertEquals("12345", banana.getBarcode());
        assertEquals("23456", apple.getBarcode());
        assertEquals("34567", bread.getBarcode());
    }

    @Test
    void getSetPrice() {
        assertEquals(5, apple.getPrice());
        assertEquals(5, banana.getPrice());
        assertEquals(5, bread.getPrice());
        apple.setPrice(2.0);
        banana.setPrice(3.5);
        bread.setPrice(6.9);
        assertEquals(2, apple.getPrice());
        assertEquals(3.5, banana.getPrice());
        assertEquals(6.9, bread.getPrice());
    }
}