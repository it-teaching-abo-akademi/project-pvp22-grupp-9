package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.events.*;
import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;

public class CashierGUI extends Window {

    //GUI elements must be accessed independently, and cannot be passed as arguments to functions
    @FXML private Label totalLabel;
    @FXML private Label changeLabel;

    @FXML private Label endLabel;
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
    public void resetRequested() {
        fireEvent(new ResetRequested());
    }

    public void setupCashierTables() {
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
        PRODUCT_TEST_CLASS PTC = productTable.getSelectionModel().getSelectedItem();
        fireEvent(new ProductAddRequested(PTC));
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

    @FXML
    public void requestAddDiscount() {
        fireEvent(new ProductDiscountRequested(cartTable.getSelectionModel().getSelectedItem(), Double.parseDouble(givenDiscount.getText())));
    }

    @FXML
    public void requestShelfSale() {
        fireEvent(new OnShelfProductsRequested());
    }

    @FXML
    public void requestOpenCashbox() {}

    @FXML
    public void requestFindKeyword() {
        fireEvent(new FindKeywordRequested(givenKeyword.getText()));
    }


    // These are called to alter the UI elements within this GUI

    public void addProduct(PRODUCT_TEST_CLASS PTC, double total) {
        //cartTable.setItems(FXCollections.observableArrayList(PTC));
        cartTable.getItems().add(PTC);
        totalLabel.setText(Double.toString(total));
        /*double totalPrice = Double.parseDouble(totalLabel.getText());
        double addedPrice = product.getPrice();
        totalPrice = totalPrice+addedPrice;
        totalLabel.setText(String.valueOf(totalPrice));*/
    }

    public void removeProduct(PRODUCT_TEST_CLASS PTC, double total) {
        cartTable.getItems().remove(PTC);
        totalLabel.setText(Double.toString(total));
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
        double total = Double.parseDouble(totalLabel.getText());
        double cashAmount = 0.0;
        String cashAmountString = givenCash.getText();
        if (cashAmountString != "") {
            try {
                cashAmount = Double.parseDouble(cashAmountString);
            } catch (Exception e) { System.out.println("Bad input"); }
        }
        fireEvent(new OnStartPaymentRequested(total, cashAmount));
    }

    public void endPaymentMode(double change) {
        changeLabel.setText(Double.toString(change));
        endLabel.setVisible(true);
    }

    public void addToProductCatalog(ArrayList<Product> products) {
        ArrayList<PRODUCT_TEST_CLASS> PTC = new ArrayList<PRODUCT_TEST_CLASS>();
        for (Product p : products) {
            PRODUCT_TEST_CLASS ptc = new PRODUCT_TEST_CLASS(p.getName(), p.getBarcode());
            ptc.setPrice(p.getPrice());
            PTC.add(ptc);
        }
        productTable.setItems(FXCollections.observableArrayList(PTC));
    }

    public Product getProductFromCatalog(String barcode) {
        for (PRODUCT_TEST_CLASS PTC : productTable.getItems()) {
            if (barcode.equals(PTC.getBarcode())) {
                return PTC.toProduct(PTC);
            }
        }
        return null;
    }

    public void reset() {
        cartTable.getItems().clear();
        totalLabel.setText("0.0");
        changeLabel.setText("0.0");
        endLabel.setVisible(false);
    }

    public void refresh() {
        cartTable.refresh();
        productTable.refresh();
    }

    public void addToCartTable(ArrayList<PRODUCT_TEST_CLASS> PTC) {
        cartTable.setItems(FXCollections.observableArrayList(PTC));
    }

    public void setTotalLabel(String total) {
        totalLabel.setText(total);
    }

}
