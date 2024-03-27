module com.example.rest_vision {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.logging;

    opens com.example.rest_vision to javafx.fxml;
    exports com.example.rest_vision;
}