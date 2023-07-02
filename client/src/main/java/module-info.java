module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;
    requires jakarta.xml.bind;
    
    
    opens com.example.view to javafx.fxml;
    opens com.example.view.controllers to javafx.fxml;
    opens com.example.model to com.google.gson;
    exports com.example.view;
}