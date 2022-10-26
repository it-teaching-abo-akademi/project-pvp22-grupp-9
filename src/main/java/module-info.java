module com.example.pos_system_version_xx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.transaction;
    requires java.xml;
    requires java.desktop;
    requires javafx.swing;

    exports com.example.pos_system_version_xx;
    exports com.example.pos_system_version_xx.GUIElements;
    exports com.example.pos_system_version_xx.models;

    opens com.example.pos_system_version_xx to javafx.fxml;
    opens com.example.pos_system_version_xx.GUIElements to javafx.fxml;
    opens com.example.pos_system_version_xx.models to javafx.fxml;


}