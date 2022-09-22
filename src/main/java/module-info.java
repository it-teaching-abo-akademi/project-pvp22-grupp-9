module com.example.pos_system_version_xx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pos_system_version_xx to javafx.fxml;
    exports com.example.pos_system_version_xx;
}