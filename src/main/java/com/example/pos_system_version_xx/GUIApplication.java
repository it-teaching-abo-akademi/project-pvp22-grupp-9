package com.example.pos_system_version_xx;

import com.example.pos_system_version_xx.GUIElements.CashierGUI;
import com.example.pos_system_version_xx.GUIElements.CustomerGUI;
import com.example.pos_system_version_xx.events.CustomEvent;
import com.example.pos_system_version_xx.events.SaleEventHandler;
import com.example.pos_system_version_xx.models.Product;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

//@SpringBootApplication //remove comment when springboot works
public class GUIApplication extends Application {

    public GUIApplication() {
        controller = new GUIController();
    };



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



    private CustomerGUI customer;
    private CashierGUI cashier;
    private GUIController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("cashier-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setTitle("Cashier");
        stage.show();

        cashier = fxmlLoader.getController();

        cashier.addEventHandler(CustomEvent.CUSTOM_EVENT_TYPE, new SaleEventHandler() {
            @Override
            public void onProductScanRequested(String barcode) {
                Product product = controller.findProduct(barcode);
                if (product == null) {
                    System.out.println("Product not found");
                    return;     // error
                }

                controller.addProduct(product);
                cashier.addProduct(product);
                //customer.addProduct(product);
            }

            @Override
            public void onGetAllProductsRequested() {
                ArrayList<Product> products = controller.getAllProducts();
                cashier.addToProductCatalog(products);
            }

            @Override
            public void onProductAddRequested(Product product) {

            }

            @Override
            public void onProductRemoveRequested(Product product) {
                if (product == null) {
                    System.out.println("Product not found");
                    return;     // error
                }

                //controller.removeProduct(product);
                cashier.removeProduct(product);
                //customer.removeProduct(product);
            }

            @Override
            public void onProductDiscountRequested(Product param0, double param1) {

            }

            @Override
            public void onStartPaymentRequested() {

            }

            @Override
            public void onOpenCashboxRequested() {

            }
        });

        Stage customerStage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(GUIApplication.class.getResource("customer-view.fxml"));
        try {
            Scene scene2 = new Scene(fxmlLoader2.load(), 600, 600);
            customerStage.setScene(scene2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        customerStage.setTitle("Customer");
        customerStage.show();

        customer = fxmlLoader2.getController();
    }

    public static void main(String[] args)
    {//SpringApplication.run(SpringBootStarter.class, args);
            launch();
    }


}