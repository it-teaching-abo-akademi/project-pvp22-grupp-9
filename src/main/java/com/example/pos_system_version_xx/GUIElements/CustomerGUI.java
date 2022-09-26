package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.models.Product;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CustomerGUI extends Window {

    public void addProduct(Product product) {
        System.out.println(this + ": adding product " + product);
    }

    public void removeProduct(Product product) {
        System.out.println(this + ": removing product " + product);
    }

    public void updateProduct(Product product) {
        System.out.println(this + ": updating product " + product);
    }

    public void startPaymentMode() {
        System.out.println();
    }





    public void start() {
        Stage stage = new Stage();
        stage.setTitle("Customer");

        Pane pane = new StackPane();
        Scene scene = new Scene(pane, 250, 250);

        stage.setScene(scene);
        stage.show();
    }
}
