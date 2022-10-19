package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.GUIApplication;
import com.example.pos_system_version_xx.GUIController;
import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CustomerGUI extends Window {

    @FXML private Label customerLabel;

    @FXML private TableView<PRODUCT_TEST_CLASS> customerTable;

    @FXML private TableColumn customerTableName;
    @FXML private TableColumn customerTablePrice;

    public CustomerGUI() {}

    public void setupCustomerTable() {
        customerTable.setEditable(true);
        customerTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        customerTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
    }

    public void addProduct(PRODUCT_TEST_CLASS PTC) {
        customerTable.getItems().add(PTC);
    }

    public void removeProduct(PRODUCT_TEST_CLASS PTC) {
        customerTable.getItems().remove(PTC);
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
