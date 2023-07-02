module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;
    requires jakarta.xml.bind;

    opens com.example.model to jakarta.xml.bind, com.google.gson;
    opens com.example.view to javafx.fxml;
    opens com.example.view.controllers to javafx.fxml;
    exports com.example.view;
}