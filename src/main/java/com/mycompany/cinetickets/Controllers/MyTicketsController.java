package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Ticket;
import com.mycompany.cinetickets.Utils.Navigation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyTicketsController implements Initializable {
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
  private Label lbWelcome;

  private ArrayList<Ticket> boughtTickets = new ArrayList();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lbWelcome.setText(App.user.getName() + "!");

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    try {
      con = dbConnection.getConnection();
      String query = "select * from ticket where cpfCliente = '" + App.user.getId() + "'";

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
        return;
      } else {
        do {
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          Date sessionDate = new Date(rs.getTimestamp("dataSessao").getTime());
          Date purchaseDate = new Date(rs.getTimestamp("dataCompra").getTime());

          Ticket ticket = new Ticket(
              rs.getInt("idTicket"),
              rs.getString("cpfCliente"),
              rs.getInt("idFilme"),
              rs.getInt("numeroSala"),
              sessionDate,
              purchaseDate,
              rs.getFloat("preco"),
              rs.getString("assento"),
              rs.getBoolean("versao3d"),
              rs.getBoolean("legendado"),
              rs.getBoolean("meiaEntrada"),
              rs.getBoolean("cadeirante"));
          boughtTickets.add(ticket);
        } while (rs.next());
      }
    } catch (SQLException ex) {
      Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
