module com.example.rest_vision {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.logging;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.rest_vision to javafx.fxml;
    exports com.example.rest_vision;
    exports com.example.rest_vision.controller.dashboard;
    opens com.example.rest_vision.controller.dashboard to javafx.fxml;
    exports com.example.rest_vision.controller.login;
    opens com.example.rest_vision.controller.login to javafx.fxml;
    exports com.example.rest_vision.controller.commande;
    opens com.example.rest_vision.controller.commande to javafx.fxml;
    exports com.example.rest_vision.controller.personnel;
    opens com.example.rest_vision.controller.personnel to javafx.fxml;
    exports com.example.rest_vision.controller.menu;
    opens com.example.rest_vision.controller.menu to javafx.fxml;
    exports com.example.rest_vision.controller.reservation;
    opens com.example.rest_vision.controller.reservation to javafx.fxml;
    exports com.example.rest_vision.controller.stock;
    opens com.example.rest_vision.controller.stock to javafx.fxml;
    exports com.example.rest_vision.controller.report;
    opens com.example.rest_vision.controller.report to javafx.fxml;
}