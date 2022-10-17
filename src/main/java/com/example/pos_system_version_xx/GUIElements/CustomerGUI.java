package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.GUIApplication;
import com.example.pos_system_version_xx.GUIController;
import com.example.pos_system_version_xx.models.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CustomerGUI extends Window {

    @FXML private Label customerLabel;

    @FXML private TableView customerTable;

    public CustomerGUI() {}

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

    }
}
