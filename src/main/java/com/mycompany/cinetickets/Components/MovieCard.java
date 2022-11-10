package com.mycompany.cinetickets.Components;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.cinetickets.Controllers.SignInController;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Movie;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MovieCard {
  private int movieId;

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

  private ArrayList<MovieCard> controllers = new ArrayList();
  private GridPane gridParent;

  private void getMovieSessions() {
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    try {
      con = dbConnection.getConnection();
      String query = "select * from sessao where idFilme = " + movieId;

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
        return;
      } else {
        do {
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          Date sessionDate = new Date(rs.getTimestamp("dataHora").getTime());
          System.out.println(sdf.format(sessionDate));
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
    this.movieId = movie.getId();
    this.movieDuration.setText(movie.getDuration() + "");
    this.movieTitle.setText(movie.getName());
    this.movieRating.setText(movie.getRating());

    Image image = new Image(
        this.getClass().getResource("/assets/" + movie.getRating().split(" ")[0].toLowerCase() + ".png").toString(),
        true);

    movieImageRating.setImage(image);

    getMovieSessions();
  }

  public void setGridParent(GridPane pane, ArrayList<MovieCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
