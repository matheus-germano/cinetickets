package com.mycompany.cinetickets.Utils;

public class Misc {
  public boolean validatePassword(String password) {
    if (password
        .matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
      return true;
    } else {
      return false;
    }
  }
}
