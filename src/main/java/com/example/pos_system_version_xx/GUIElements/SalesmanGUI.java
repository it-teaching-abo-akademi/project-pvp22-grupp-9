package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;

public class SalesmanGUI extends Window {

    @FXML
    private TableView<PRODUCT_TEST_CLASS> salesmanTable;

    @FXML
    private TableColumn salesmanTableName;
    @FXML
    private TableColumn salesmanTablePrice;
    @FXML
    private TableColumn salesmanTableBarcode;

    public SalesmanGUI() {

    }

    public void setupSalesmanTable() {
        salesmanTable.setEditable(true);
        salesmanTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("Name"));
        salesmanTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("Price"));
        salesmanTablePrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        salesmanTablePrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PRODUCT_TEST_CLASS, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PRODUCT_TEST_CLASS, Double> t) {
                PRODUCT_TEST_CLASS p = t.getRowValue();
                p.setPrice(t.getNewValue());
            }
        });
        salesmanTableBarcode.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("barcode"));
    }

    public void addToProductCatalog(ArrayList<Product> products) {
        ArrayList<PRODUCT_TEST_CLASS> PTC = new ArrayList<PRODUCT_TEST_CLASS>();
        for (Product p : products) {
            PTC.add(new PRODUCT_TEST_CLASS(p.getName(), p.getBarcode()));
        }
        salesmanTable.setItems(FXCollections.observableArrayList(PTC));
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        for (PRODUCT_TEST_CLASS PTC : salesmanTable.getItems()) {
            Product p = new Product(PTC.getName(), PTC.getBarcode());
            p.setPrice(PTC.getPrice());
            products.add(p);
        }
        return products;
    }
}