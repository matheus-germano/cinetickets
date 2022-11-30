package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Components.SeatComponent;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Movie;
import com.mycompany.cinetickets.Models.Seat;
import com.mycompany.cinetickets.Utils.Navigation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyTicketsController implements Initializable {
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

  Navigation nav = new Navigation();
  private ArrayList<SeatComponent> controllers = new ArrayList();
  private String[] seatColumns = { "A", "B", "C", "D", "E", "F" };
  private ArrayList<String> alreadyBoughtSeats = new ArrayList();
  private ArrayList<String> selectedSeats = new ArrayList<>();
  public static BuyTicketsController buyTicketsController;
  private Movie movie;

  public BuyTicketsController() {
    buyTicketsController = this;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    lbWelcome.setText(App.user.getName() + "!");
  }

  public void setBuyTicketData(Movie movie, Date sessionDate, int roomId) {
    this.movie = movie;
    lbMovieName.setText(movie.getName());
    lbSessionDateAndRoom.setText(sessionDate + " | Sala " + roomId);

    Image moviePoster = new Image(movie.getPoster());

    ivMoviePoster.setImage(moviePoster);

    fillSeatsGrid();
  }

  public void fillSeatsGrid() {
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    gpSeatsGrid.getColumnConstraints().clear();
    gpSeatsGrid.getRowConstraints().clear();
    gpSeatsGrid.getChildren().clear();

    try {
      con = dbConnection.getConnection();
      String query = "select * from ticket where idFilme = " + movie.getId() + " and numeroSala = " + 4
          + " and dataSessao = '2022-11-20 14:30:00'";

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
      } else {
        do {
          alreadyBoughtSeats.add(rs.getString("assento"));
        } while (rs.next());
      }
    } catch (SQLException ex) {
      Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if (con != null) {
          con.close();
        }

        if (st != null) {
          st.close();
        }

        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

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

          String seatId = (column + i).toString();
          String seatType = "common";

          if (alreadyBoughtSeats.contains(seatId)) {
            System.out.println("Entrou");
            seatType = "bought";
          }

          Seat seat = new Seat(
              seatId,
              seatType,
              false);

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

  public void addSelectedSeatInList(String seatId) {
    selectedSeats.add(seatId);
    int totalSelectedSeats = selectedSeats.size();
    float totalPurchasePrice = totalSelectedSeats * 22;
    lbTotalTickets.setText(totalSelectedSeats + "");
    lbPurchasePrice.setText("R$ " + String.format("%.2f", totalPurchasePrice));
  }

  public void removeSeatFromList(String seatId) {
    selectedSeats.remove(seatId);
    int totalSelectedSeats = selectedSeats.size();
    float totalPurchasePrice = totalSelectedSeats * 22;
    lbTotalTickets.setText(totalSelectedSeats + "");
    lbPurchasePrice.setText("R$ " + String.format("%.2f", totalPurchasePrice));
  }

  public void confirmPurchase() {
    if (selectedSeats.size() == 0) {
      Alert a = new Alert(Alert.AlertType.WARNING);
      a.setTitle("Ocorreu um erro");
      a.setContentText("Voce deve selecionar um assento antes de continuar");
      a.showAndWait();

      return;
    }

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    try {
      con = dbConnection.getConnection();

      for (String seatId : selectedSeats) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String purchaseDate = sdf.format(new Date());

        String query = "insert into ticket values (default,'" + App.user.getId() + "', " + movie.getId()
            + ", 4,'2022-11-20 14:30:00','" + purchaseDate + "', 11.00, '"
            + seatId + "', 0, 0, 1, 0)";

        st = (Statement) con.createStatement();
        st.execute(query);
      }

      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setTitle("Sucesso!");
      a.setContentText("Ingresso(s) comprado(s) com sucesso");
      a.showAndWait();
    } catch (SQLException ex) {
      Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if (con != null) {
          con.close();
        }

        if (st != null) {
          st.close();
        }

        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
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
