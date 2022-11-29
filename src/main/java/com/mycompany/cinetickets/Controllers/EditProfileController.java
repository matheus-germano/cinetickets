package com.mycompany.cinetickets.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Utils.Base64Utils;
import com.mycompany.cinetickets.Utils.Misc;
import com.mycompany.cinetickets.Utils.Navigation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditProfileController implements Initializable {
  Navigation nav = new Navigation();
  Base64Utils base64 = new Base64Utils();
  Misc misc = new Misc();

  @FXML
  private Pane goToMyProfileBtn;

  @FXML
  private Pane goToSessionsBtn;

  @FXML
  private Pane goToMyTicketsBtn;

  @FXML
  private Pane btnLogout;

  @FXML
  private Label lbWelcome;

  @FXML
  private DatePicker dpBirthDate;

  @FXML
  private TextField tfEmail;

  @FXML
  private TextField tfId;

  @FXML
  private TextField tfName;

  @FXML
  private TextField tfPassword;

  @FXML
  private Button btnSaveChanges;

  String name = App.user.getName();
  String email = App.user.getEmail();
  String password = App.user.getPassword();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lbWelcome.setText(App.user.getName() + "!");
    tfName.setText(App.user.getName());
    tfId.setText(App.user.getId());
    dpBirthDate.setValue(App.user.getBirthDate());
    tfEmail.setText(App.user.getEmail());
  }

  @FXML
  public void handleSaveChanges() {
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    DbConnection dbConnection = new DbConnection();

    if (validateEditProfileFormData(tfName, tfEmail, tfPassword)) {
      try {
        if (!tfName.getText().isEmpty()) {
          name = tfName.getText();
        }
        if (!tfEmail.getText().isEmpty()) {
          email = tfEmail.getText();
        }
        if (!tfPassword.getText().isEmpty()) {
          password = base64.encode(tfPassword.getText());
          System.out.println(password);
        }

        con = dbConnection.getConnection();
        String query = "update pessoa set nome = '" + name + "', email = '" + email + "', senha = '" + password
            + "' where cpf = '" + App.user.getId() + "'";

        st = (Statement) con.createStatement();
        st.execute(query);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Sucesso");
        a.setContentText(
            "Dados alterados com sucesso!");
        a.showAndWait();

        App.user.setName(name);
        App.user.setEmail(email);
        App.user.setPassword(password);
        lbWelcome.setText(name);
        tfPassword.setText("");
      } catch (SQLException ex) {
        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
          Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public boolean validateEditProfileFormData(TextField... textFields) {
    var emptyFields = 0;

    if (base64.encode(tfPassword.getText()) == App.user.getPassword()) {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setTitle("Erro");
      a.setContentText(
          "Nova senha igual a anterior, insira uma diferente");
      a.showAndWait();

      return false;
    }

    if (!tfPassword.getText().isEmpty() && !misc.validatePassword(tfPassword.getText())) {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setTitle("Ocorreu um erro");
      a.setContentText(
          "A senha deve conter pelo menos um numero, uma letra maiuscula, uma minuscula e um caracter especial");
      a.showAndWait();

      return false;
    }

    for (TextField textField : textFields) {
      if (textField.getText().isEmpty()) {
        emptyFields++;
      }
    }

    if (emptyFields > 0) {
      Alert a = new Alert(Alert.AlertType.WARNING);
      a.setTitle("Aviso");
      a.setContentText(
          "Campos vazios serao ignorados");
      a.showAndWait();
    }

    return true;
  }

  @FXML
  public void logout() throws IOException {
    App.user = null;

    nav.navigateTo("signin", (Stage) btnLogout.getScene().getWindow());
  }

  @FXML
  public void goToMyProfile() throws IOException {
    nav.navigateTo("editProfile", (Stage) goToMyProfileBtn.getScene().getWindow());
  }

  @FXML
  public void goToSessions() throws IOException {
    nav.navigateTo("movieSessions", (Stage) goToSessionsBtn.getScene().getWindow());
  }

  @FXML
  public void goToMyTickets() throws IOException {
    nav.navigateTo("myTickets", (Stage) goToMyTicketsBtn.getScene().getWindow());
  }
}
