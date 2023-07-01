module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;
    requires jakarta.xml.bind;
    
    
    opens com.example.client.view to javafx.fxml;
    opens com.example.client.view.controllers to javafx.fxml;
    opens com.example.server.model to com.google.gson;
    exports com.example.client.view;
}