package com.mycompany.cinetickets.Models;

public class Session {
  private int movieId;
  private int roomId;
  private String sessionDate;

  public Session(int movieId, int roomId, String sessionDate) {
    this.movieId = movieId;
    this.roomId = roomId;
    this.sessionDate = sessionDate;
  }

  public int getMovieId() {
    return movieId;
  }

  public int getRoomId() {
    return roomId;
  }

  public String getSessionDate() {
    return sessionDate;
  }
}
