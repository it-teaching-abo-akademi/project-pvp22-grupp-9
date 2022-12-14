package com.example.pos_system_version_xx.GUIElements;

import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerGUI extends Window {

    @FXML private Label totalLabel;

    @FXML private TableView<PRODUCT_TEST_CLASS> customerTable;

    @FXML private TableColumn customerTableName;
    @FXML private TableColumn customerTablePrice;

    private int fileIndex = 0;

    public CustomerGUI() {}

    public void setupCustomerTable() {
        customerTable.setEditable(true);
        customerTableName.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, String>("name"));
        customerTablePrice.setCellValueFactory(new PropertyValueFactory<PRODUCT_TEST_CLASS, Double>("price"));
    }

    public void addProduct(PRODUCT_TEST_CLASS PTC, double total) {
        customerTable.getItems().add(PTC);
        totalLabel.setText(Double.toString(total));
    }

    public void removeProduct(PRODUCT_TEST_CLASS PTC, double total) {
        customerTable.getItems().remove(PTC);
        totalLabel.setText(Double.toString(total));
    }

    public void reset() {
        customerTable.getItems().clear();
        totalLabel.setText("0.0");
    }

    public void refresh() {
        customerTable.refresh();
    }

    public void addToCartTable(ArrayList<PRODUCT_TEST_CLASS> PTC) {
        customerTable.setItems(FXCollections.observableArrayList(PTC));
    }

    public void setTotalLabel(String total) {
        totalLabel.setText(total);
    }

    public void updateProduct(Product product) {
        System.out.println(this + ": updating product " + product);
    }

    public void startPaymentMode() {
        System.out.println();
    }

    public void printReceipt() {
        WritableImage image = customerTable.getScene().snapshot(null);

        File file = new File("receipt" + fileIndex + ".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }

        fileIndex += 1;
    }

    public void start() {

    }
}
