/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cinetickets.Controllers;

import com.mycompany.cinetickets.App;
import com.mycompany.cinetickets.Database.DbConnection;
import com.mycompany.cinetickets.Models.User;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matheus
 */
public class SignInController {
    Navigation nav = new Navigation();

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnGoToSignUp;

    @FXML
    public void signIn() {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        if (validateSignInFormData()) {
            Base64Utils base64Utils = new Base64Utils();
            DbConnection dbConnection = new DbConnection();
            String email = tfEmail.getText();
            String password = base64Utils.encode(tfPassword.getText());

            try {
                con = dbConnection.getConnection();
                String query = "select * from pessoa where email = '" + email + "' and senha = '" + password
                        + "' limit 1";

                st = (Statement) con.createStatement();
                rs = st.executeQuery(query);

                if (rs.next() == false) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Ocorreu um erro");
                    a.setContentText("Usuario nao encontrado com as credenciais informadas");
                    a.showAndWait();

                    return;
                } else {
                    User user = new User();

                    do {
                        LocalDate localDate = LocalDate.parse(rs.getString("dataNascimento"));

                        user.createUser(
                                rs.getString("cpf"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                localDate);

                        System.out.println(user.getId());
                        System.out.println(user.getName());
                        System.out.println(user.getEmail());
                        System.out.println(user.getPassword());
                        System.out.println(user.getBirthDate());
                    } while (rs.next());

                    App.user = user;

                    try {
                        goToMovieSessions();
                    } catch (Exception e) {
                        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean validateSignInFormData() {
        Misc misc = new Misc();

        if (tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ocorreu um erro");
            a.setContentText("Preencha todos os campos");
            a.showAndWait();

            return false;
        }

        if (!misc.validatePassword(tfPassword.getText())) {
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
    public void goToMovieSessions() throws IOException {
        nav.navigateTo("movieSessions", (Stage) tfPassword.getScene().getWindow());
    }

    @FXML
    public void goToSignUp() throws IOException {
        nav.navigateTo("signup", (Stage) tfPassword.getScene().getWindow());
    }
}
