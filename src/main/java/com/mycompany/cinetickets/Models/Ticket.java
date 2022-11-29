package com.mycompany.cinetickets.Models;

import java.util.Date;

public class Ticket {
  int id;
  String buyerId;
  int movieId;
  int roomId;
  Date purchaseDate;
  Date sessionDate;
  float price;
  String seat;
  boolean is3d;
  boolean isSubtitled;
  boolean isHalfPriced;
  boolean isWheelchairUser;

  public Ticket(
      // int id,
      // String buyerId,
      int movieId,
      int roomId,
      // Date purchaseDate,
      Date sessionDate,
      float price,
      String seat
  // boolean is3d,
  // boolean isSubtitled,
  // boolean isHalfPriced,
  // boolean isWheelchairUser
  ) {
    // this.id = id;
    // this.buyerId = buyerId;
    this.movieId = movieId;
    this.roomId = roomId;
    // this.purchaseDate = purchaseDate;
    this.sessionDate = sessionDate;
    this.price = price;
    this.seat = seat;
    // this.is3d = is3d;
    // this.isSubtitled = isSubtitled;
    // this.isHalfPriced = isHalfPriced;
    // this.isWheelchairUser = isWheelchairUser;
  }

  public int getId() {
    return id;
  }

  public String getBuyerId() {
    return buyerId;
  }

  public int getMovieId() {
    return movieId;
  }

  public int getRoomId() {
    return roomId;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public Date getSessionDate() {
    return sessionDate;
  }

  public float getPrice() {
    return price;
  }

  public String getSeat() {
    return seat;
  }

  public boolean getIs3d() {
    return is3d;
  }

  public boolean getIsSubtitled() {
    return isSubtitled;
  }

  public boolean getIsHalfPriced() {
    return isHalfPriced;
  }

  public boolean getIsWheelchairUser() {
    return isWheelchairUser;
  }
}
