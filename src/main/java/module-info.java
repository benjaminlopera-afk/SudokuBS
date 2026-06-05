module com.example.sudokubs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudokubs to javafx.fxml;
    exports com.example.sudokubs.Controllers;
    opens com.example.sudokubs.Controllers to javafx.fxml;
    exports com.example.sudokubs.Applications;
    opens com.example.sudokubs.Applications to javafx.fxml;
}