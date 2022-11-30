package com.mycompany.cinetickets.Components;

import java.util.ArrayList;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Controllers.BuyTicketsController;
import com.mycompany.cinetickets.Models.Seat;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SeatComponent {
  private ArrayList<SeatComponent> controllers = new ArrayList();
  private GridPane gridParent;

  @FXML
  private ImageView imIcon;

  @FXML
  private Pane pSeat;

  private String id;
  private String type;
  private boolean isOcupied;

  public void setSeatData(Seat seat) {
    this.id = seat.getId();
    this.type = seat.getType();
    this.isOcupied = seat.getIsOcupied();
  }

  public void handleChangeSeatStatus() {
    if (isOcupied) {
      BuyTicketsController.buyTicketsController.removeSeatFromList(id);
      this.isOcupied = false;
      pSeat.setStyle("-fx-background-color: #0F9D58; -fx-background-radius: 100; -fx-border-radius: 100");
      imIcon.setImage(null);
    } else {
      BuyTicketsController.buyTicketsController.addSelectedSeatInList(id);
      this.isOcupied = true;
      pSeat.setStyle("-fx-background-color: #007AFF; -fx-background-radius: 100; -fx-border-radius: 100");
      Image icon = new Image(App.class.getResource("/assets/check.png").toString());
      imIcon.setImage(icon);
    }
  }

  public void setGridParent(GridPane pane, ArrayList<SeatComponent> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
