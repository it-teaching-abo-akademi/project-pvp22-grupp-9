package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.GUIApplication;
import com.example.pos_system_version_xx.GUIController;
import com.example.pos_system_version_xx.events.OpenCashboxRequested;
import com.example.pos_system_version_xx.events.ProductAddRequested;
import com.example.pos_system_version_xx.events.ProductRemoveRequested;
import com.example.pos_system_version_xx.events.ProductScanRequested;
import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;

public class CashierGUI extends Window {

    //GUI elements must be accessed independently, and cannot be passed as arguments to functions
    @FXML private Label totalLabel;
    @FXML private Label changeLabel;
    @FXML private TextField givenCash; //convert this String to double
    @FXML private TextField givenDiscount; //convert to int
    @FXML private TextField givenKeyword;
    @FXML private TextField insertedBarcode; //you get whatever its contents are!!
    @FXML private TableView<PRODUCT_TEST_CLASS> cartTable;
    @FXML private TableView<PRODUCT_TEST_CLASS> productTable;

    @FXML private TableColumn productTableName;
    @FXML private TableColumn productTablePrice;
    @FXML private TableColumn productTableBarcode;
    @FXML private TableColumn cartTableName;
    @FXML private TableColumn cartTablePrice;

    public CashierGUI() {

    }

    @FXML
    public void onHelloButtonClick() {
        productTable.setEditable(true);
        productTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        productTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
        productTablePrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        productTablePrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PRODUCT_TEST_CLASS, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PRODUCT_TEST_CLASS, Double> t) {
                PRODUCT_TEST_CLASS p = t.getRowValue();
                p.setPrice(t.getNewValue());
            }
        });
        productTableBarcode.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("barcode"));
        productTable.setItems(FXCollections.observableArrayList(
                new PRODUCT_TEST_CLASS("Banana", ""),
                new PRODUCT_TEST_CLASS("Apple", ""),
                new PRODUCT_TEST_CLASS("Hannes", "")
        ));
        cartTable.setEditable(true);
        cartTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        cartTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
    } //This can be used as a test

    // These are called when the GUI requests something to be done
    @FXML
    public void requestAddProduct() {
        // TODO: FIND PRODUCT IN QUESTION BASED ON WHICH PRODUCT IN THE CATALOG IS SELECTED
        //fireEvent(new ProductAddRequested();
    }

    @FXML
    public void requestScanProduct() {
        fireEvent(new ProductScanRequested(insertedBarcode.getText()));
    }

    @FXML
    public void requestRemoveProduct() {
        PRODUCT_TEST_CLASS product = cartTable.getSelectionModel().getSelectedItem();
        fireEvent(new ProductRemoveRequested(product));
    }

    //@FXML >> old parameters: (Product product, double discount)
    public void requestAddDiscount() {

        //fireEvent(new ProductDiscountRequested(product, discount));
    }

    @FXML
    public void requestShelfSale() {}

    @FXML
    public void requestOpenCashbox() {}


    // These are called to alter the UI elements within this GUI

    public void addProduct(PRODUCT_TEST_CLASS PTC) {
        //cartTable.setItems(FXCollections.observableArrayList(PTC));
        cartTable.getItems().add(PTC);

        /*double totalPrice = Double.parseDouble(totalLabel.getText());
        double addedPrice = product.getPrice();;
        totalPrice = totalPrice+addedPrice;
        totalLabel.setText(String.valueOf(totalPrice));*/
    }

    public void removeProduct(PRODUCT_TEST_CLASS PTC) {
        cartTable.getItems().remove(PTC);

        /*double totalPrice = Double.parseDouble(totalLabel.getText());
        double removedPrice = product.getPrice();;
        totalPrice = totalPrice-removedPrice;
        totalLabel.setText(String.valueOf(totalPrice));*/
    }

    public void updateProduct(Product product) {
        System.out.println(this + ": updating info about product " + product);
    }

    @FXML
    //connected to the "Start payment" button
    public void startPaymentMode() {
        if (Double.parseDouble(givenCash.getText()) > Double.parseDouble(totalLabel.getText())) {
            changeLabel.setText(String.valueOf(Double.parseDouble(givenCash.getText()) - Double.parseDouble(totalLabel.getText())));
            totalLabel.setText("0.00");
            //cashBox should open here
            //reset UI
        } else if (Double.parseDouble(givenCash.getText()) == Double.parseDouble(totalLabel.getText())) {
            totalLabel.setText("0.00");
            //cashBox should open here
            //reset UI
        } else if (Double.parseDouble(givenCash.getText()) < Double.parseDouble(totalLabel.getText())) {
            totalLabel.setText(String.valueOf(Double.parseDouble(totalLabel.getText()) - Double.parseDouble(givenCash.getText())));
            //cardPayment
        }
        else {
            //cardPayment
        }
    }
    @FXML
    public void addDiscountToProduct() {
        PRODUCT_TEST_CLASS product = cartTable.getSelectionModel().getSelectedItem();
        product.setPrice(product.getPrice()-(product.getPrice()*Double.parseDouble(givenDiscount.getText())/100));
        cartTable.refresh();
        double totalPrice = Double.parseDouble(totalLabel.getText());
        double discountedPrice = product.getPrice();;
        totalPrice = totalPrice-(totalPrice-discountedPrice);
        totalLabel.setText(String.valueOf(totalPrice));
    }



    public void addToProductCatalog(ArrayList<Product> products) {
    }
}
