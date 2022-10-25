package com.example.pos_system_version_xx.models;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;
    private double total = 0.0;

    public Order() {
        products = new ArrayList<Product>();
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> productList) {
        products.clear();
        for (Product p : productList ) {
            products.add(p);
        }
    }

    public double addProduct(Product product) {
        products.add(product);
        total += product.getPrice();
        return total;
    }

    public double removeProduct(Product product) {
        products.remove(product);
        total -= product.getPrice();
        return total;
    }

    public double getTotal() { return total; }

    public void setTotal(double total) {
        this.total = total;
    }

}
