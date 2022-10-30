module com.mycompany.cinetickets {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.cinetickets to javafx.fxml;
    opens com.mycompany.cinetickets.Controllers to javafx.fxml;
    
    exports com.mycompany.cinetickets;
    exports com.mycompany.cinetickets.Controllers;
}
