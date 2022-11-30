package com.mycompany.cinetickets.Components;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Controllers.BuyTicketsController;
import com.mycompany.cinetickets.Controllers.SignInController;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Movie;
import com.mycompany.cinetickets.Models.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MovieCard {
  private Movie movie;
  private int movieId;
  private ArrayList<MovieCard> controllers = new ArrayList();
  private GridPane gridParent;
  private ArrayList<Session> sessions = new ArrayList();

  @FXML
  private Label movieDuration;

  @FXML
  private ImageView moviePoster;

  @FXML
  private ImageView movieImageRating;

  @FXML
  private Label movieTitle;

  @FXML
  private Label movieRating;

  @FXML
  private GridPane sessionsGrid;

  private void getMovieSessions() {
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();
    ArrayList<Session> sessions = new ArrayList();

    try {
      con = dbConnection.getConnection();
      String query = "select distinct numeroSala from sessao where idFilme = " + movieId;

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
        return;
      } else {
        do {
          // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          // Date sessionDate = new Date(rs.getTimestamp("dataHora").getTime());

          Session session = new Session(movieId, rs.getInt("numeroSala"));

          sessions.add(session);
        } while (rs.next());
      }
    } catch (SQLException e) {
      Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, e);
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
      } catch (SQLException e) {
        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, e);
      }
    }
  }

  public void setMovieData(Movie movie) {
    this.movie = movie;
    this.movieId = movie.getId();
    this.movieDuration.setText(movie.getDuration() + "");
    this.movieTitle.setText(movie.getName());
    this.movieRating.setText(movie.getRating());

    Image poster = new Image(movie.getPoster());

    moviePoster.setImage(poster);

    Image rating = new Image(
        this.getClass().getResource("/assets/" + movie.getRating().split(" ")[0].toLowerCase() + ".png").toString(),
        true);

    movieImageRating.setImage(rating);

    getMovieSessions();
  }

  public void setGridParent(GridPane pane, ArrayList<MovieCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }

  @FXML
  public void goToBuyTickets() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(App.class.getResource("buyTickets.fxml"));
    Parent parent = loader.load();
    Scene scene = new Scene(parent);

    BuyTicketsController controller = loader.getController();
    Date sessionDate = new Date();
    controller.setBuyTicketData(movie, sessionDate, 1);

    Stage stage = (Stage) moviePoster.getScene().getWindow();

    stage.setScene(scene);
    stage.show();
  }
}
