module com.example.climatenow {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfoenix;
    requires java.sql;
    requires java.desktop;

    opens com.example.climatenow to javafx.fxml;
    exports com.example.climatenow;
}