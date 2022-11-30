package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Components.SeatComponent;
import com.mycompany.cinetickets.Models.Seat;
import com.mycompany.cinetickets.Utils.Navigation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BuyTicketsController implements Initializable {
  Navigation nav = new Navigation();
  private ArrayList<SeatComponent> controllers = new ArrayList();
  private String[] seatColumns = { "A", "B", "C", "D", "E", "F" };

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

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    lbWelcome.setText(App.user.getName() + "!");

    int gridColumns = 0;
    int gridRow = 1;

    for (String column : seatColumns) {
      for (int i = 1; i < 18; i++) {
        try {
          URL fxmlUrl = this.getClass()
              .getResource("/com/mycompany/cinetickets/seat.fxml");

          if (fxmlUrl == null) {
            System.err.println("Cannot find FXML file");
            return;
          }

          Seat seat = new Seat(
              column + i + "",
              "common",
              false);

          System.out.println(seat.getId());

          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(fxmlUrl);
          AnchorPane seatComponent = fxmlLoader.load();
          SeatComponent seatController = fxmlLoader.getController();
          seatController.setSeatData(seat);
          seatController.setGridParent(gpSeatsGrid, controllers);
          controllers.add(seatController);

          gpSeatsGrid.add(seatComponent, gridColumns++, gridRow);
          GridPane.setMargin(seatComponent, new Insets(5));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      gridRow++;
      gridColumns = 0;
    }
  }

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
