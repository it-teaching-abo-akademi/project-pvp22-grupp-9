package com.example.pos_system_version_xx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GUIController {
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