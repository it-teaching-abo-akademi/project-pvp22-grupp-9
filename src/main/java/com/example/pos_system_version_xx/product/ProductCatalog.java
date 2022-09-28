package com.example.pos_system_version_xx.product;

import com.example.pos_system_version_xx.models.Barcode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface ProductCatalog {

    
    @GetMapping
    default void findProductByBarcode(Barcode barcode){

    }

    @GetMapping
    default void findProductByName(Product name){

        //name = Product.getName();
    }

    @PostMapping
    default void saveProduct(Product product){

    }
}
