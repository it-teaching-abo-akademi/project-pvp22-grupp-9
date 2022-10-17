package com.example.pos_system_version_xx;

import com.example.pos_system_version_xx.tools.XMLParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.Order;
import com.example.pos_system_version_xx.models.Product;
import com.example.pos_system_version_xx.GET.RequestHandler;

import java.util.ArrayList;


public class GUIController {

    private Order order;

    private RequestHandler requestHandler;
    private XMLParser parser;

    public GUIController() {
        order = new Order();
        requestHandler = new RequestHandler();
        parser = new XMLParser();
    }

    public Product findProduct(String barcode) {
        String response = "";
        try {
            requestHandler.handlerFindBarcode(barcode);
            if (response == null) {
                return null; // NOT FOUND
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<Product> products = parser.parseProducts(response);
        assert products.size() > 0;


        return products.get(0);
    }

    public void printProducts() {
        order.printProducts();
    }

    public void addProduct(Product product) {
        order.addProduct(product);
    }

    public void removeProduct(Product product) {
        order.removeProduct(product);
    }

    public void addDiscountToProduct(Product product, double amount) {
        product.setDiscount(amount);
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<Product>();
    }

    //public void openCashbox() { requestHandler.handlerOpenCashbox();

}