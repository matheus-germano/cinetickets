package com.mycompany.cinetickets.Utils;

import java.io.IOException;

import com.mycompany.cinetickets.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Navigation {
  @FXML
  private Pane goToMyProfileBtn;

  @FXML
  private Pane goToSessionsBtn;

  @FXML
  private Pane goToMyTicketsBtn;

  public void navigateTo(String goTo, Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(goTo + ".fxml"));
    AnchorPane anchorPane = loader.load();
    Scene scene = new Scene(anchorPane);

    stage.setScene(scene);
    stage.show();
  }
}
