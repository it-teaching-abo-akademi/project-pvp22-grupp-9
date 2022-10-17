package com.example.pos_system_version_xx.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class PRODUCT_TEST_CLASS {
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty barcode;

    public PRODUCT_TEST_CLASS(String name, String barcode) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(5);
        this.barcode = new SimpleStringProperty(barcode);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String productName) {
        name.set(productName);
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(Double productPrice) {
        price.set(productPrice);
    }

    public String getBarcode() {
        return barcode.get();
    }
    public void setBarcode(String productBarcode) {
        barcode.set(productBarcode);
    }

    //Converts a PRODUCT_TEST_CLASS to Product
    public Product toProduct(PRODUCT_TEST_CLASS p) {
        return new Product(p.getName(), p.getBarcode());
    }

}
