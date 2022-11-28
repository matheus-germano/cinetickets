package com.mycompany.cinetickets.Utils;

import java.io.IOException;

import com.mycompany.cinetickets.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Navigation {
  public void navigateTo(String goTo, Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(goTo + ".fxml"));
    AnchorPane anchorPane = loader.load();
    Scene scene = new Scene(anchorPane);

    stage.setScene(scene);
    stage.show();
  }
}
