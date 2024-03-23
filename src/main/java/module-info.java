module com.example.rest_vision {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.rest_vision to javafx.fxml;
    exports com.example.rest_vision;
}