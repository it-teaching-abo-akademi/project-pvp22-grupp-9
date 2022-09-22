package com.example.pos_system_version_xx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIApplication extends Application {
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
    }

    public static void main(String[] args) {
        launch();
    }
}