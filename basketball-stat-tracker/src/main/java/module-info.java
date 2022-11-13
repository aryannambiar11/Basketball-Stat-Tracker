module com.example.basketballstattracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.basketballstattracker to javafx.fxml;
    exports com.example.basketballstattracker;
}