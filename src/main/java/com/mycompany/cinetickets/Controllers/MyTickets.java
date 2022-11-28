package com.mycompany.cinetickets.Controllers;

import java.io.IOException;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Utils.Navigation;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyTickets {
  Navigation nav = new Navigation();

  @FXML
  private Pane goToMyProfileBtn;

  @FXML
  private Pane goToSessionsBtn;

  @FXML
  private Pane goToMyTicketsBtn;

  @FXML
  private Pane btnLogout;

  @FXML
  public void logout() throws IOException {
    App.user = null;

    nav.navigateTo("signin", (Stage) btnLogout.getScene().getWindow());
  }

  @FXML
  public void goToMyProfile() throws IOException {
    nav.navigateTo("editProfile", (Stage) goToMyProfileBtn.getScene().getWindow());
  }

  @FXML
  public void goToSessions() throws IOException {
    nav.navigateTo("movieSessions", (Stage) goToSessionsBtn.getScene().getWindow());
  }

  @FXML
  public void goToMyTickets() throws IOException {
    nav.navigateTo("myTickets", (Stage) goToMyTicketsBtn.getScene().getWindow());
  }
}
