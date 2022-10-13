package com.example.pos_system_version_xx.models;

public class Product {
    private String name;
    private String barcode;
    private double discount;

    public Product() {

    }

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;

        this.discount = 0;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", barcode='" + barcode + '\'' +
                ", discount=" + discount +
                '}';
    }

    public double getDiscount() {
        return discount;
    }
}
