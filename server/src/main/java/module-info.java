module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;
    requires jakarta.xml.bind;
    
    
    // opens com.example.view to javafx.fxml;
    opens com.example.model.chat to jakarta.xml.bind;
    opens com.example.view.controllers to javafx.fxml;
    opens com.example.model to com.google.gson, jakarta.xml.bind;
    // exports com.example.view;
}