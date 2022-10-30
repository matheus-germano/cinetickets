module com.mycompany.cinetickets {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.cinetickets to javafx.fxml;
    exports com.mycompany.cinetickets;
}
