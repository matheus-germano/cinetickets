package com.mycompany.cinetickets.Models;

import java.util.ArrayList;
import java.util.Date;

public class Session {
  private int movieId;
  private int roomId;
  private ArrayList<Date> sessions;

  public Session(int movieId, int roomId) {
    this.movieId = movieId;
    this.roomId = roomId;
  }

  public int getMovieId() {
    return movieId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setSessions(ArrayList<Date> sessions) {
    this.sessions = sessions;
  }

  public ArrayList<Date> getSessions() {
    return sessions;
  }
}
