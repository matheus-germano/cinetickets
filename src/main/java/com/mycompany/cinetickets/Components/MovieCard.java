package com.mycompany.cinetickets.Components;

import java.sql.Time;
import java.util.ArrayList;

import com.mycompany.cinetickets.Models.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MovieCard {
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

  public void setMovieData(Movie movie) {
    this.movieDuration.setText(movie.getDuration() + "");
    this.movieTitle.setText(movie.getName());
    this.movieRating.setText(movie.getRating());

    System.out.println(movie.getRating());

    Image image = new Image(
        this.getClass().getResource("/assets/" + movie.getRating().split(" ")[0].toLowerCase() + ".png").toString(),
        true);

    movieImageRating.setImage(image);
  }

  public void setGridParent(GridPane pane, ArrayList<MovieCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
