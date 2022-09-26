package com.example.pos_system_version_xx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.Order;
import com.example.pos_system_version_xx.models.Product;


public class GUIController {

    private Order order;

    public GUIController() {
        order = new Order();
    }

    public Product findProduct(Barcode barcode) {
        return new Product("Banana");
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
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<StudentsModel> tableView;

    @FXML
    private TableColumn<StudentsModel, String> tableColumn = new TableColumn<>();

    public class StudentsModel {
        String name;
        private StringProperty firstName = new SimpleStringProperty();

        public StudentsModel(String name) {
            this.name = name;
        }

        public StringProperty firstNameProperty() {
            return firstName;
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onHelloButtonPress() {
        welcomeText.setText("NOT WELCOME to JavaFX Application!");
    }
}