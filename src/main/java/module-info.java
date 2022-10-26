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
    requires java.transaction;
    requires spring.aop;
    requires java.xml;
    requires java.desktop;
    requires javafx.swing;

    exports com.example.pos_system_version_xx;
    exports com.example.pos_system_version_xx.card;
    exports com.example.pos_system_version_xx.GUIElements;
    exports com.example.pos_system_version_xx.models;

    opens com.example.pos_system_version_xx to javafx.fxml, spring.core;
    opens com.example.pos_system_version_xx.card to org.hibernate.orm.core, spring.core;
    opens com.example.pos_system_version_xx.GUIElements to javafx.fxml;
    opens com.example.pos_system_version_xx.models to javafx.fxml, org.hibernate.orm.core, spring.core;


}