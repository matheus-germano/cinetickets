package com.mycompany.cinetickets.Models;

public class Seat {
  String id;
  String type;
  boolean isOcupied;

  public Seat(String id, String type, boolean isOcupied) {
    this.id = id;
    this.type = type;
    this.isOcupied = isOcupied;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public boolean getIsOcupied() {
    return isOcupied;
  }

  public void setIsOcupied(boolean isOcupied) {
    this.isOcupied = isOcupied;
  }
}
