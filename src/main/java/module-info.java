module com.mycompany.cinetickets {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.cinetickets to javafx.fxml;
    opens com.mycompany.cinetickets.Controllers to javafx.fxml;
    opens com.mycompany.cinetickets.Components to javafx.fxml;
    opens com.mycompany.cinetickets.Utils to javafx.fxml;

    exports com.mycompany.cinetickets;
    exports com.mycompany.cinetickets.Controllers;
    exports com.mycompany.cinetickets.Components;
    exports com.mycompany.cinetickets.Utils;
}
