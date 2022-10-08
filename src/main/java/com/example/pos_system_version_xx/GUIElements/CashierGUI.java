package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.events.ProductAddRequested;
import com.example.pos_system_version_xx.events.ProductDiscountRequested;
import com.example.pos_system_version_xx.events.ProductRemoveRequested;
import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CashierGUI extends Window {

    //GUI elements must be accessed independently, and cannot be passed as arguments to functions
    @FXML private Label welcomeLabel; //?
    @FXML private Label totalLabel;
    @FXML private Label subtotalLabel;
    @FXML private TextField givenCash; //convert this String to double
    @FXML private TextField insertedBarcode; //you get whatever its contents are!!

    public CashierGUI() {

    }

    @FXML
    public void onHelloButtonClick() {} //This can be used as a test

    // These are called when the GUI requests something to be done
    //@FXML
    public void requestAddProduct(Barcode barcode) {
        // DO SOMETHING AND THEN FIRE EVENT
        fireEvent(new ProductAddRequested(barcode));
    }

    //@FXML
    public void requestRemoveProduct(Product product) {
        fireEvent(new ProductRemoveRequested(product));
    }

    //@FXML >> old parameters: (Product product, double discount)
    public void requestAddDiscount() {
        //get these from tableview and a textview
        //Product product;
        double discount;
        //  { CODE TO GET SELECTED PRODUCT }
        //discount = Double.parseDouble( <INSERT CREATED DISCOUNT TEXTFIELD>.getText() );
        //fireEvent(new ProductDiscountRequested(product, discount));
    }

    @FXML
    public void requestShelfSale() {}

    @FXML
    public void requestOpenCashbox() {}


    // These are called to alter the UI elements within this GUI
    public void addProduct(Product product) {
        System.out.println(this + ": adding product " + product);
    }

    public void removeProduct(Product product) {
        System.out.println(this + ": removing product " + product);
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
                requestAddProduct(new Barcode());
            }
        });
        pane.getChildren().add(addButton);

        Scene scene = new Scene(pane, 250, 250);
        stage.setScene(scene);

        stage.show();
    }
}
