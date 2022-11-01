package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Components.MovieCard;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MovieSessions implements Initializable {
  @FXML
  private Label lbWelcome;

  @FXML
  private GridPane moviesGrid;

  private ArrayList<Session> sessions = new ArrayList<Session>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lbWelcome.setText(App.user.getName() + "!");
    int gridColumns = 0;
    int gridRow = 1;

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    try {
      con = dbConnection.getConnection();
      String query = "select * from sessao";

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
        return;
      } else {
        do {
          Session session = new Session(rs.getInt("idFilme"), rs.getInt("numeroSala"), rs.getString("dataHora"));
          sessions.add(session);
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

    for (Session session : sessions) {
      try {
        URL fxmlUrl = this.getClass()
            .getResource("/com/mycompany/cinetickets/movieCard.fxml");

        if (fxmlUrl == null) {
          System.err.println("Cannot find FXML file");
          return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);
        AnchorPane card = fxmlLoader.load();
        MovieCard cardController = fxmlLoader.getController();

        if (gridColumns == 2) {
          gridColumns = 0;
          ++gridRow;
        }

        moviesGrid.add(card, gridColumns++, gridRow);
        GridPane.setMargin(card, new Insets(10));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
