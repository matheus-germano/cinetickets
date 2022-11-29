package com.mycompany.cinetickets.Components;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.Ticket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    String movieName = "";

    try {
      con = dbConnection.getConnection();
      String query = "select nome from filme where idFilme = " + ticket.getMovieId() + " limit 1";

      st = (Statement) con.createStatement();
      rs = st.executeQuery(query);

      if (rs.next() == false) {
        return;
      } else {
        do {
          movieName = rs.getString("nome");
        } while (rs.next());
      }
    } catch (SQLException ex) {
      Logger.getLogger(TicketCard.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(TicketCard.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    lbMovie.setText(movieName);
    lbPrice.setText("R$ " + ticket.getPrice() + "");
    lbRoomId.setText(ticket.getRoomId() + "");
    lbSeat.setText(ticket.getSeat());
    lbSessionDate.setText(ticket.getSessionDate() + "");
  }

  public void setGridParent(GridPane pane, ArrayList<TicketCard> controllers) {
    this.gridParent = pane;
    this.controllers = controllers;
  }
}
