package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.events.ProductAddRequested;
import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

public class CashierGUI extends Window {

    //GUI elements must be accessed independently, and cannot be passed as arguments to functions
    @FXML private Label welcomeText; //?
    @FXML private Label totalLabel;
    @FXML private Label subtotalLabel;
    @FXML private TextField givenCash; //convert this String to double
    @FXML private TextField insertedBarcode; //you get whatever its contents are!!
    @FXML private TextField givenDiscount; //convert to int
    @FXML private TableView<PRODUCT_TEST_CLASS> cartTable;
    @FXML private TableView<PRODUCT_TEST_CLASS> productTable;

    @FXML private TableColumn productTableName;
    @FXML private TableColumn productTablePrice;
    @FXML private TableColumn productTableBarcode;
    @FXML private TableColumn cartTableName;
    @FXML private TableColumn cartTablePrice;


    @FXML
    public void onHelloButtonClick() {
        productTable.setEditable(true);
        productTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        productTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
        productTableBarcode.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("barcode"));
        productTable.setItems(FXCollections.observableArrayList(
                new PRODUCT_TEST_CLASS("Banana", 1.00, ""),
                new PRODUCT_TEST_CLASS("Apple", 1.00,""),
                new PRODUCT_TEST_CLASS("Hannes", 1.00,"")
        ));
        cartTable.setEditable(true);
        cartTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        cartTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
    } //This can be used as a test

    // These are called when the GUI requests something to be done
    @FXML
    public void requestAddProduct() {
    PRODUCT_TEST_CLASS product = productTable.getSelectionModel().getSelectedItem();
        cartTable.getItems().add(product);

        //fireEvent(new ProductAddRequested(insertedBarcode.getText()));
    }

    @FXML
    public void requestScanProduct() {
        //fireEvent(new ProductAddRequested(insertedBarcode.getText()));
    }
    @FXML
    public void requestRemoveProduct() {
        PRODUCT_TEST_CLASS product = cartTable.getSelectionModel().getSelectedItem();
        cartTable.getItems().remove(product);
        //fireEvent(new ProductRemoveRequested(product));
    }

    //@FXML >> old parameters: (Product product, double discount)
    public void requestAddDiscount() {
        //get these from tableview and a textview
        //Product product;
        double discount;
        //  { CODE TO GET SELECTED PRODUCT }
        discount = Integer.parseInt( givenDiscount.getText() );
        //fireEvent(new ProductDiscountRequested(product, discount));
    }

    @FXML
    public void requestShelfSale() {}

    @FXML
    public void requestOpenCashbox() {}


    // These are called to alter the UI elements within this GUI

    public void addProduct(Product product) {
        productTable.setEditable(true);
        productTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        productTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
        productTableBarcode.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("barcode"));
        productTable.setItems(FXCollections.observableArrayList(
                new PRODUCT_TEST_CLASS("Banana", 0.45, ""),
                new PRODUCT_TEST_CLASS("apple", 0.65,""),
                new PRODUCT_TEST_CLASS("car", 25000.50,"")
        ));
    }

    public void removeProduct(Product product) {

    }

    public void updateProduct(Product product) {
        System.out.println(this + ": updating info about product " + product);
    }

    @FXML
    //connected to the "Start payment" button
    public void startPaymentMode() {

    }

    public void start() {
        Stage stage = new Stage();
        stage.setTitle("Cashier");

        Pane pane = new StackPane();

        Button addButton = new Button("Add product");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //requestAddProduct(new Barcode());
            }
        });
        pane.getChildren().add(addButton);

        Scene scene = new Scene(pane, 250, 250);
        stage.setScene(scene);

        stage.show();
    }
}
