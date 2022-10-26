package com.example.pos_system_version_xx;

import com.example.pos_system_version_xx.GET.RequestHandler;
import com.example.pos_system_version_xx.GET.SearchParamType;
import com.example.pos_system_version_xx.models.Order;
import com.example.pos_system_version_xx.models.Product;
import com.example.pos_system_version_xx.tools.XMLParser;

import java.util.ArrayList;


public class GUIController {

    private Order order;
    private Order shelfOrder;

    private RequestHandler requestHandler;
    private XMLParser parser;


    public GUIController() {
        order = new Order(); //WARNING: change in order to use shelf
        shelfOrder = new Order();
        requestHandler = new RequestHandler();
        parser = new XMLParser();
    }

    public Product findProduct(String barcode) {
        String response = "";
        try {
            response = requestHandler.find(barcode, SearchParamType.BARCODE);
            if (response == null) {
                return null; // NOT FOUND
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(response);


        ArrayList<Product> products = parser.parseProducts(response);
        assert products.size() > 0;

        return products.get(0);
    }

    public ArrayList<Product> findProducts(String param, SearchParamType type) {
        String response = "";
        try {
            response = requestHandler.find(param, type);
            if (response == null) {
                return null; // NOT FOUND
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(response);

        ArrayList<Product> products = parser.parseProducts(response);
        assert products.size() > 0;

        System.out.println("Products by " + type + " " + param);
        for (Product product : products) {
            System.out.println(product);
        }

        return products;
    }

    public Order getOrder() {
        return order;
    }

    public Order getShelfOrder() {
        return shelfOrder;
    }

    public double addProduct(Product product) {
        return order.addProduct(product);
    }

    public double removeProduct(Product product) {
        return order.removeProduct(product);
    }

    public ArrayList<Product> getAllProducts() {
        return findProducts("*", SearchParamType.NAME);
    }

    public String openCashbox() {
        String response = "";
        try {
            response = requestHandler.handlerOpenCashbox();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String startCardReader(double remainingTotal) {
        String response = "";
        try {
            response = requestHandler.handlerCardReaderWaitForPayment(remainingTotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String cardReaderStatus() {
        String response = "";
        try {
            response = requestHandler.handlerCardReaderStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String resetCardReader() {
        String response = "";
        try {
            response = requestHandler.handlerCardReaderReset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}