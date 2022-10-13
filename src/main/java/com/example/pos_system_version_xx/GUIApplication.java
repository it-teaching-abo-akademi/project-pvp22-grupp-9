package com.example.pos_system_version_xx;

import com.example.pos_system_version_xx.GUIElements.CashierGUI;
import com.example.pos_system_version_xx.GUIElements.CustomerGUI;
import com.example.pos_system_version_xx.events.CustomEvent;
import com.example.pos_system_version_xx.events.SaleEventHandler;
import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
//@SpringBootApplication //remove comment when springboot works
public class GUIApplication extends Application {

    public GUIApplication() {
        customer = new CustomerGUI();
        cashier = new CashierGUI();
        controller = new GUIController();

        cashier.addEventHandler(CustomEvent.CUSTOM_EVENT_TYPE, new SaleEventHandler() {

        @Override
        public void onProductAddRequested(String barcode) {
            Product product = controller.findProduct(barcode);
            if (product == null) {
                return;     // error
            }

            controller.addProduct(product);
            cashier.addProduct(product);
            customer.addProduct(product);
        }

        @Override
        public void onProductRemoveRequested(Product product) {
            controller.removeProduct(product);
            cashier.removeProduct(product);
            customer.removeProduct(product);
        }

        @Override
        public void onProductDiscountRequested(Product product, double amount) {
            controller.addDiscountToProduct(product, amount);
            cashier.updateProduct(product);
            customer.updateProduct(product);
        }

        @Override
        public void onStartPaymentRequested() {
            customer.startPaymentMode();
            cashier.startPaymentMode();
            //controller.waitForPayment();
        }

        @Override
        public void onOpenCashboxRequested() {
            //controller.openCashbox();
        }
    });

    /* IF WE WANT: THE CUSTOMER CAN DO STUFF
        customer.addEventHandler(CustomEvent.CUSTOM_EVENT_TYPE, new SaleEventHandler() {

        @Override
        public void onProductAddRequested(String barcode) {
            Product product = controller.findProduct(barcode);
            if (product == null) {
                // error
            }

            controller.addProduct(product);
            cashier.addProduct(product);
            customer.addProduct(product);
        }

        @Override
        public void onProductRemoveRequested(Product product) {
            controller.removeProduct(product);
            cashier.removeProduct(product);
            customer.removeProduct(product);
        }

        @Override
        public void onProductDiscountRequested(Product product, double amount) {
                /*
                boolean accepted = cashier.addCustomerRequest( ""+ "The customer is requesting to add a "
                     + amount * 100
                      + " percent discount. Accept?" );
                 if (accepted) {
                     cashier.requestAddDiscount(product, amount);
                 }
                *\
        }

        @Override
        public void onStartPaymentRequested() {
            customer.startPaymentMode();
            cashier.startPaymentMode();
            //controller.waitForPayment();
        }

    }); */
}


    private CustomerGUI customer;
    private CashierGUI cashier;
    private GUIController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("cashier-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Cashier");
        stage.setScene(scene);
        stage.show();

        Stage secondStage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(GUIApplication.class.getResource("customer-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 600, 600);
        secondStage.setTitle("Customer");
        secondStage.setScene(scene2);
        secondStage.show();

        //SpringApplication.run(SpringBootStarter.class, args);
    }

    public static void main(String[] args)
    {//SpringApplication.run(SpringBootStarter.class, args);
            launch();
    }
}