/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cinetickets.Controllers;

import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Utils.Base64Utils;
import com.mycompany.cinetickets.Utils.Misc;
import com.mycompany.cinetickets.Utils.Navigation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matheus
 */
public class SignUpController {
    Navigation nav = new Navigation();

    @FXML
    private Button btnGoToSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lbGoToSignIn;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    public void signUp() {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        if (validateSignUpFormData()) {
            Base64Utils base64Utils = new Base64Utils();
            DbConnection dbConnection = new DbConnection();

            String id = tfId.getText();
            String name = tfName.getText();
            String email = tfEmail.getText();
            String password = base64Utils.encode(pfPassword.getText());
            LocalDate birthDate = dpBirthDate.getValue();

            try {
                con = dbConnection.getConnection();
                String query = "insert into pessoa values ('" + id + "', '" + name + "','" + birthDate + "', '" + email
                        + "', '" + password + "', 'cliente')";

                st = (Statement) con.createStatement();
                st.execute(query);

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Sucesso!");
                a.setContentText("Cadastrado com sucesso! Fa??a o login");
                a.showAndWait();

                try {
                    goToSignIn();
                } catch (Exception e) {

                }
            } catch (SQLException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());

                if (ex.getMessage().startsWith("Duplicate entry")) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Ocorreu um erro");
                    a.setContentText("CPF ja cadastrado no sistema");
                    a.showAndWait();

                    return;
                }
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

    public boolean validateSignUpFormData() {
        Misc misc = new Misc();

        if (tfEmail.getText().isEmpty() || pfPassword.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ocorreu um erro");
            a.setContentText("Preencha todos os campos");
            a.showAndWait();

            return false;
        }

        if (!misc.validatePassword(pfPassword.getText())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ocorreu um erro");
            a.setContentText(
                    "A senha deve conter pelo menos um numero, uma letra maiuscula, uma minuscula e um caracter especial");
            a.showAndWait();

            return false;
        }

        return true;
    }

    @FXML
    public void goToSignIn() throws IOException {
        nav.navigateTo("signin", (Stage) pfPassword.getScene().getWindow());
    }
}
