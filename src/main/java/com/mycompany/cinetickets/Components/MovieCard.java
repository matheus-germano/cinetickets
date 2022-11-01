package com.mycompany.cinetickets.Components;

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
}
