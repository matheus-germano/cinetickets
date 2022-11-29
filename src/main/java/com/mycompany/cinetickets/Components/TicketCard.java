package com.mycompany.cinetickets.Components;

import java.util.ArrayList;

import com.mycompany.cinetickets.Models.Ticket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TicketCard {
  private ArrayList<TicketCard> controllers = new ArrayList();
  private GridPane gridParent;

  @FXML
  private Label lbMovie;

  @FXML
  private Label lbPrice;

  @FXML
  private Label lbRoomId;

  @FXML
  private Label lbSeat;

  @FXML
  private Label lbSessionDate;

  public void setTicketData(Ticket ticket) {
    lbMovie.setText(ticket.getMovieId() + "");
    lbPrice.setText(ticket.getPrice() + "");
    lbRoomId.setText(ticket.getRoomId() + "");
    lbSeat.setText(ticket.getSeat());
    lbSessionDate.setText(ticket.getSessionDate() + "");
  }

  public void setGridParent(GridPane pane, ArrayList<TicketCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
