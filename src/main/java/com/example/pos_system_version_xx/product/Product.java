package com.example.pos_system_version_xx.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Product {

    @Id
    private int barcode;
    private int vatPercentage;
    private String name;
    private double discount;
    private ArrayList<String > keywords;

    public Product(){

    }

    public Product(int barcode, int vatPercentage, String name, ArrayList<String> keywords) {
        this.barcode = barcode;
        this.vatPercentage = vatPercentage;
        this.name = name;
        this.keywords = keywords;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public int getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(int vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }
}
