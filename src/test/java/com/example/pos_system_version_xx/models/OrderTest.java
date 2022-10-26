package com.example.pos_system_version_xx.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order1 = new Order();
    Order order2 = new Order();
    Order order3 = new Order();
    Product banana = new Product("Banana", "12345");
    Product apple = new Product("Apple", "23456");
    Product bread = new Product("Bread", "34567");


    @Test
    void getSetProducts() {

        ArrayList<Product> expected1 = new ArrayList<>(3);
        ArrayList<Product> expected2 = new ArrayList<>(5);
        ArrayList<Product> expected3 = new ArrayList<>(5);

        expected1.add(0, banana);
        expected1.add(1, apple);
        expected1.add(2, bread);

        expected2.add(0, bread);
        expected2.add(1, bread);
        expected2.add(2, bread);
        expected2.add(3, banana);
        expected2.add(4, banana);

        expected3.add(0, bread);
        expected3.add(1, apple);
        expected3.add(2, bread);
        expected3.add(3, apple);
        expected3.add(4, bread);

        order1.setProducts(expected1);
        order2.setProducts(expected2);
        order3.setProducts(expected3);

        assertEquals(expected1, order1.getProducts());
        assertEquals(expected2, order2.getProducts());
        assertEquals(expected3, order3.getProducts());

        order1.removeProduct(banana);
        order1.removeProduct(apple);
        order1.removeProduct(bread);
        System.out.println("order1 is empty");

        order2.removeProduct(bread);
        order2.removeProduct(bread);
        order2.removeProduct(bread);
        order2.removeProduct(banana);
        order2.removeProduct(banana);
        System.out.println("order2 is empty");

        order3.removeProduct(bread);
        order3.removeProduct(apple);
        order3.removeProduct(bread);
        order3.removeProduct(apple);
        order3.removeProduct(bread);
        System.out.println("order3 is empty");

        assertTrue(order1.getProducts().isEmpty());
        assertTrue(order2.getProducts().isEmpty());
        assertTrue(order3.getProducts().isEmpty());
    }

    @Test
    void addRemoveProduct() {
        ArrayList<Product> ORDER1 = new ArrayList<>();
        ArrayList<Product> ORDER2 = new ArrayList<>();
        ArrayList<Product> ORDER3 = new ArrayList<>();

        assertEquals(ORDER1, order1.getProducts());
        assertEquals(ORDER2, order1.getProducts());
        assertEquals(ORDER3, order1.getProducts());

        ORDER1.add(banana);
        ORDER1.add(banana);

        ORDER2.add(apple);
        ORDER2.add(banana);
        ORDER2.add(apple);

        ORDER3.add(banana);
        ORDER3.add(bread);
        ORDER3.add(apple);
        ORDER3.add(bread);

        order1.addProduct(banana);
        order1.addProduct(banana);

        order2.addProduct(apple);
        order2.addProduct(banana);
        order2.addProduct(apple);

        order3.addProduct(banana);
        order3.addProduct(bread);
        order3.addProduct(apple);
        order3.addProduct(bread);

        assertEquals(ORDER1, order1.getProducts());

        assertEquals(ORDER1.get(0), banana);
        assertEquals(ORDER1.get(1), banana);

        assertEquals(ORDER2, order2.getProducts());

        assertEquals(ORDER2.get(0), apple);
        assertEquals(ORDER2.get(1), banana);
        assertEquals(ORDER2.get(2), apple);

        assertEquals(ORDER3, order3.getProducts());

        assertEquals(ORDER3.get(0), banana);
        assertEquals(ORDER3.get(1), bread);
        assertEquals(ORDER3.get(2), apple);
        assertEquals(ORDER3.get(3), bread);

        order3.removeProduct(bread);
        ORDER3.remove(1);
        assertEquals(ORDER3, order3.getProducts());

        order3.removeProduct(apple);
        ORDER3.remove(1);
        assertEquals(ORDER3, order3.getProducts());

        order3.removeProduct(banana);
        ORDER3.remove(0);
        assertEquals(ORDER3, order3.getProducts());

        order3.removeProduct(bread);
        ORDER3.remove(0);
        assertEquals(ORDER3, order3.getProducts());

        order2.removeProduct(apple);
        ORDER2.remove(0);
        assertEquals(ORDER2, order2.getProducts());

        order2.removeProduct(apple);
        ORDER2.remove(1);
        assertEquals(ORDER2, order2.getProducts());

        order2.removeProduct(banana);
        ORDER2.remove(0);
        assertEquals(ORDER2, order2.getProducts());

        order1.removeProduct(banana);
        ORDER1.remove(0);
        assertEquals(ORDER1, order1.getProducts());

        order1.removeProduct(banana);
        ORDER1.remove(0);
        assertEquals(ORDER1, order1.getProducts());

        assertTrue(ORDER1.isEmpty());
        assertTrue(order1.getProducts().isEmpty());

        assertTrue(ORDER2.isEmpty());
        assertTrue(order2.getProducts().isEmpty());

        assertTrue(ORDER3.isEmpty());
        assertTrue(order3.getProducts().isEmpty());

    }

    @Test
    void getSetTotal() {
        apple.setPrice(2.0);
        banana.setPrice(3.5);
        bread.setPrice(6.9);

        assertEquals(2, apple.getPrice());
        assertEquals(3.5, banana.getPrice());
        assertEquals(6.9, bread.getPrice());

        order1.addProduct(apple);
        order1.addProduct(apple);
        order1.addProduct(banana);
        order1.addProduct(apple);
        order1.addProduct(banana);
        order1.addProduct(bread);

        assertEquals(19.9, order1.getTotal());

        order1.setTotal(20);

        assertEquals(20, order1.getTotal());

        order1.removeProduct(apple);
        order1.removeProduct(apple);
        order1.removeProduct(apple);
        order1.removeProduct(banana);
        order1.removeProduct(banana);
        order1.removeProduct(bread);

        assertTrue(order1.getProducts().isEmpty());
    }

}