module com.example.softwareic482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.softwareic482 to javafx.fxml;
    exports com.example.softwareic482;
    exports com.example.softwareic482.model;
    opens com.example.softwareic482.model to javafx.fxml;
    opens com.example.softwareic482.controller to javafx.fxml;
}