package com.example.pos_system_version_xx.models;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;

    public Order() {
        products = new ArrayList<Product>();
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
