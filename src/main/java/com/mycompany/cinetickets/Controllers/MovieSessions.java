package com.mycompany.cinetickets.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.cinetickets.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MovieSessions implements Initializable {

  @FXML
  private Label lbWelcome;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lbWelcome.setText(App.user.getName() + "!");
  }
}
