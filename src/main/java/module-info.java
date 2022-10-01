module com.example.pos_system_version_xx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires spring.web;
    requires spring.data.jpa;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.beans;
    requires com.zaxxer.hikari;

    exports com.example.pos_system_version_xx;
    exports com.example.pos_system_version_xx.customer;
    exports com.example.pos_system_version_xx.product;

    opens com.example.pos_system_version_xx to javafx.fxml, spring.core;
    opens com.example.pos_system_version_xx.product to org.hibernate.orm.core;
    opens com.example.pos_system_version_xx.customer to org.hibernate.orm.core;
}