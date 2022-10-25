package com.example.pos_system_version_xx;

import com.example.pos_system_version_xx.GET.RequestHandler;
import com.example.pos_system_version_xx.GET.SearchParamType;
import com.example.pos_system_version_xx.GUIElements.CashierGUI;
import com.example.pos_system_version_xx.GUIElements.CustomerGUI;
import com.example.pos_system_version_xx.GUIElements.MarketingCoordinatorGUI;
import com.example.pos_system_version_xx.GUIElements.SalesmanGUI;
import com.example.pos_system_version_xx.events.CustomEvent;
import com.example.pos_system_version_xx.events.SaleEventHandler;
import com.example.pos_system_version_xx.models.Order;
import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication //remove comment when springboot works
public class GUIApplication extends Application {

    public GUIApplication() {
        controller = new GUIController();
    }



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

    private SalesmanGUI salesman;

    private MarketingCoordinatorGUI marketingCoordinator;
    private GUIController controller;

    private HashMap<PRODUCT_TEST_CLASS, Product> productMap;

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

        Stage salesmanStage = new Stage();
        FXMLLoader fxmlLoader3 = new FXMLLoader(GUIApplication.class.getResource("salesman-view.fxml"));
        try {
            Scene scene3 = new Scene(fxmlLoader3.load(), 600, 600);
            salesmanStage.setScene(scene3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        salesmanStage.setTitle("Salesman");

        Stage marketingCoordinatorStage = new Stage();
        FXMLLoader fxmlLoader4 = new FXMLLoader(GUIApplication.class.getResource("marketingcoordinator-view.fxml"));
        try {
            Scene scene4 = new Scene(fxmlLoader4.load(), 600, 600);
            marketingCoordinatorStage.setScene(scene4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        marketingCoordinatorStage.setTitle("Marketing Coordinator");

        salesmanStage.show();
        marketingCoordinatorStage.show();
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

                PRODUCT_TEST_CLASS PTC = new PRODUCT_TEST_CLASS(product.getName(), product.getBarcode());

                double total = controller.addProduct(product);
                cashier.addProduct(PTC, total);
                customer.addProduct(PTC, total);
                productMap.put(PTC, product);
            }

            @Override
            public void onGetProductsRequested(String keyword, SearchParamType type) {
                ArrayList<Product> products = controller.getAllProducts();
                cashier.addToProductCatalog(products);
            }

            @Override
            public void onProductAddRequested(PRODUCT_TEST_CLASS PTC) {
                if (PTC == null) {
                    System.out.println("No product selected");
                    return; // error
                }
                Product product = PTC.toProduct(PTC);
                double total = controller.addProduct(product);
                PRODUCT_TEST_CLASS ptc = new PRODUCT_TEST_CLASS(product.getName(), product.getBarcode());
                productMap.put(ptc, product);
                cashier.addProduct(ptc, total);
                customer.addProduct(ptc, total);
            }

            @Override
            public void onProductRemoveRequested(PRODUCT_TEST_CLASS PTC) {
                if (PTC == null) {
                    System.out.println("Product not found");
                    return;     // error
                }
                Product product = productMap.get(PTC);
                double total = controller.removeProduct(product);
                cashier.removeProduct(PTC, total);
                customer.removeProduct(PTC, total);
                productMap.remove(PTC,product);
            }

            @Override
            public void onProductDiscountRequested(PRODUCT_TEST_CLASS PTC, double discount) {
                double discountedPrice = PTC.getPrice()*(discount/100);
                PTC.setPrice(PTC.getPrice()-(discountedPrice));
                Product product = productMap.get(PTC);
                product.setPrice(product.getPrice()-(discountedPrice));
                double totalPrice = controller.getOrder().getTotal();
                totalPrice -= discountedPrice;
                controller.getOrder().setTotal(totalPrice);
                cashier.setTotalLabel(String.valueOf(totalPrice));
                customer.setTotalLabel(String.valueOf(totalPrice));
                cashier.refresh();
                customer.refresh();
            }

            @Override
            public void onStartPaymentRequested(double total, double cash) {
                double change;
                if (cash < total) {
                    //card payment necessary
                    controller.startCardReader(total - cash);
                    while ( ! controller.cardReaderStatus().contains("DONE") ) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (Exception e) { System.out.println("Interrupted clock"); }
                    }
                    controller.resetCardReader();
                    change = 0.0;
                }
                else {
                    //all payment by cash
                    change = total-cash;
                    controller.openCashbox();
                }

                cashier.endPaymentMode(change);

                //print receipt?
                customer.printReceipt();
            }

            @Override
            public void onResetRequested() {
                productMap.clear();
                customer.reset();
                cashier.reset();
                controller.getOrder().getProducts().clear();
                controller.getOrder().setTotal(0.0);
            }

            @Override
            public void onFindKeywordRequested(String param) {
                if (param == "") { param = "*"; }
                ArrayList<Product> ps = controller.findProducts(param, SearchParamType.NAME);
                ps.addAll(controller.findProducts(param, SearchParamType.KEYWORD));
                cashier.addToProductCatalog(ps);
            }

            @Override
            public void onShelfProductsRequested() {
                if (controller.getShelfOrder().getProducts().isEmpty()) {
                    controller.getShelfOrder().setProducts(controller.getOrder().getProducts());
                    controller.getShelfOrder().setTotal(controller.getOrder().getTotal());
                    customer.reset();
                    cashier.reset();
                    controller.getOrder().getProducts().clear();
                    controller.getOrder().setTotal(0.0);
                    productMap.clear();
                }
                else {
                    controller.getOrder().setProducts(controller.getShelfOrder().getProducts());
                    controller.getOrder().setTotal(controller.getShelfOrder().getTotal());
                    productMap.clear();
                    ArrayList<PRODUCT_TEST_CLASS> PTC = new ArrayList<PRODUCT_TEST_CLASS>();
                    for (Product p : controller.getShelfOrder().getProducts()) {
                        PRODUCT_TEST_CLASS P = new PRODUCT_TEST_CLASS(p.getName(), p.getBarcode());
                        P.setPrice(p.getPrice());
                        PTC.add(P);
                        productMap.put(P,p);
                    }
                    controller.getShelfOrder().getProducts().clear();
                    controller.getShelfOrder().setTotal(0.0);
                    cashier.addToCartTable(PTC);
                    cashier.setTotalLabel(Double.toString(controller.getOrder().getTotal()));
                    customer.addToCartTable(PTC);
                    customer.setTotalLabel(Double.toString(controller.getOrder().getTotal()));
                }
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
        salesman = fxmlLoader3.getController();
        marketingCoordinator = fxmlLoader4.getController();
        customer.setupCustomerTable();
        cashier.setupCashierTables();
        cashier.addToProductCatalog(controller.getAllProducts());
        productMap = new HashMap<PRODUCT_TEST_CLASS, Product>();
    }

    public static void main(String[] args)
    {//SpringApplication.run(SpringBootStarter.class, args);
            launch();
    }


}