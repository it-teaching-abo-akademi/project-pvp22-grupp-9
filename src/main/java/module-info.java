module com.example.pos_system_version_xx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires spring.web;
    requires spring.data.jpa;
    requires spring.context;


    opens com.example.pos_system_version_xx to javafx.fxml;
    exports com.example.pos_system_version_xx;
}