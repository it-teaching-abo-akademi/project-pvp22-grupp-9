package com.example.pos_system_version_xx.models;

public class Product {
    private String name;
    private double discount;

    private String barcode;

    public Product(String name) {
        this.name = name;
        this.discount = 0;
        //this.barcode =
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }

    public double getDiscount() {
        return discount;
    }
}
