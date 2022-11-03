package com.mycompany.cinetickets.Components;

import java.sql.Time;
import java.util.ArrayList;

import com.mycompany.cinetickets.Models.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MovieCard {
  @FXML
  private Label movieDuration;

  @FXML
  private ImageView moviePoster;

  @FXML
  private Label movieTitle;

  @FXML
  private GridPane sessionsGrid;

  private ArrayList<MovieCard> controllers = new ArrayList();
  private GridPane gridParent;

  public void setMovieData(Movie movie) {
    this.movieDuration.setText(movie.getDuration() + "");
    this.movieTitle.setText(movie.getName());
  }

  public void setGridParent(GridPane pane, ArrayList<MovieCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
