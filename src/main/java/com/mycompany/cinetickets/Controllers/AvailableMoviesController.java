package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Components.AdmMovieCard;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Movie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AvailableMoviesController implements Initializable {
  @FXML
  private Pane btnLogout;

  @FXML
  private Pane goToAvailableMoviesBtn;

  @FXML
  private Label lbWelcome;

  @FXML
  private GridPane moviesGrid;

  private ArrayList<Movie> movies = new ArrayList<Movie>();
  private ArrayList<AdmMovieCard> controllers = new ArrayList();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lbWelcome.setText(App.user.getName() + "!");
    int gridRow = 1;

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    try {
      con = dbConnection.getConnection();
      String query = "select * from filme";

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
        return;
      } else {
        do {
          LocalDate lancamento = LocalDate.parse(rs.getString("lancamento"));

          Movie movie = new Movie(
              rs.getInt("idFilme"),
              rs.getString("nome"),
              "",
              rs.getString("genero"),
              lancamento,
              rs.getTime("duracao"),
              rs.getString("classificacao"),
              rs.getString("poster"));
          movies.add(movie);
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

    for (Movie movie : movies) {
      try {
        URL fxmlUrl = this.getClass()
            .getResource("/com/mycompany/cinetickets/AdmMovieCard.fxml");

        if (fxmlUrl == null) {
          System.err.println("Cannot find FXML file");
          return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);
        AnchorPane card = fxmlLoader.load();
        AdmMovieCard cardController = fxmlLoader.getController();
        cardController.setMovieData(movie);
        cardController.setGridParent(moviesGrid, controllers);
        controllers.add(cardController);

        moviesGrid.add(card, 0, gridRow++);
        GridPane.setMargin(card, new Insets(10));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
