package com.mycompany.cinetickets.Components;

import java.util.ArrayList;

import com.mycompany.cinetickets.Models.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class AdmMovieCard {
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

  private Movie movie;
  private int movieId;
  private ArrayList<AdmMovieCard> controllers = new ArrayList();
  private GridPane gridParent;

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
  }

  public void setGridParent(GridPane pane, ArrayList<AdmMovieCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
