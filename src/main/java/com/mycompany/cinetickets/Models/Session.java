package com.mycompany.cinetickets.Models;

public class Session {
  private int movieId;
  private int roomId;
  private String movieTime;

  public Session(int movieId, int roomId, String movieTime) {
    this.movieId = movieId;
    this.roomId = roomId;
    this.movieTime = movieTime;
  }

  public int getMovieId() {
    return movieId;
  }

  public int getRoomId() {
    return roomId;
  }

  public String getMovieTime() {
    return movieTime;
  }
}
