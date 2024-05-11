module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.context;

    opens org.example to javafx.fxml;
    exports org.example.model;
    exports org.example.repository;
    exports org.example.service;
    exports org.example.ui;

    exports org.example;
}
