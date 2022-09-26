package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.events.ProductAddRequested;
import com.example.pos_system_version_xx.events.ProductDiscountRequested;
import com.example.pos_system_version_xx.events.ProductRemoveRequested;
import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CashierGUI extends Window {

    public CashierGUI() {

    }

    // These are called when the GUI requests something to be done
    public void requestAddProduct(Barcode barcode) {
        // DO SOMETHING AND THEN FIRE EVENT
        fireEvent(new ProductAddRequested(barcode));
    }

    public void requestRemoveProduct(Product product) {
        fireEvent(new ProductRemoveRequested(product));
    }

    public void requestAddDiscount(Product product, double discount) {
        fireEvent(new ProductDiscountRequested(product, discount));
    }


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
