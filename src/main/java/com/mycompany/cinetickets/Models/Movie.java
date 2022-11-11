package com.mycompany.cinetickets.Models;

import java.sql.Time;
import java.time.LocalDate;

public class Movie {
  private int id;
  private String name;
  private String image;
  private String gender;
  private LocalDate releasedAt;
  private Time duration;
  private String rating;
  private String poster;

  public Movie(int id, String name, String image, String gender, LocalDate releasedAt, Time duration, String rating,
      String poster) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.gender = gender;
    this.releasedAt = releasedAt;
    this.duration = duration;
    this.rating = rating;
    this.poster = poster;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDate getReleasedAt() {
    return releasedAt;
  }

  public void setReleasedAt(LocalDate releasedAt) {
    this.releasedAt = releasedAt;
  }

  public Time getDuration() {
    return duration;
  }

  public void setDuration(Time duration) {
    this.duration = duration;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }
}
