package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.util.Date;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Utils.Navigation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BuyTicketsController {
  Navigation nav = new Navigation();

  @FXML
  private Button btnConfirmPurchase;

  @FXML
  private Pane btnLogout;

  @FXML
  private Pane goToMyProfileBtn;

  @FXML
  private Pane goToMyTicketsBtn;

  @FXML
  private Pane goToSessionsBtn;

  @FXML
  private GridPane gpSeatsGrid;

  @FXML
  private Label lbMovieName;

  @FXML
  private Label lbPurchasePrice;

  @FXML
  private Label lbSessionDateAndRoom;

  @FXML
  private Label lbTotalTickets;

  @FXML
  private Label lbWelcome;

  @FXML
  private ImageView ivMoviePoster;

  public void setBuyTicketData(String movieName, Image moviePoster, Date sessionDate, int roomId) {
    lbMovieName.setText(movieName);
    lbSessionDateAndRoom.setText(sessionDate + " | Sala " + roomId);

    ivMoviePoster.setImage(moviePoster);
  }

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
