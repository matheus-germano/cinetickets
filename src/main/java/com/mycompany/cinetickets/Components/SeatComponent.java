package com.mycompany.cinetickets.Components;

import java.util.ArrayList;

import com.mycompany.cinetickets.Models.Seat;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SeatComponent {
  private ArrayList<SeatComponent> controllers = new ArrayList();
  private GridPane gridParent;

  @FXML
  private ImageView imIcon;

  public void setSeatData(Seat seat) {

  }

  public void setGridParent(GridPane pane, ArrayList<SeatComponent> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
