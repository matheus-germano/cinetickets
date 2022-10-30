/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cinetickets.Controllers;

import com.mycompany.cinetickets.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matheus
 */
public class SignInController implements Initializable {
    @FXML
    private TextField tfEmail;
    
    @FXML
    private PasswordField tfPassword;
    
    @FXML
    private Button btnSignIn;
    
    @FXML
    private Button btnGoToSignUp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void signIn() {
        if (validateInfo()) {
            String email = tfEmail.getText();
            String password = tfPassword.getText();
        }
    }
    
    public boolean validateInfo() {
        if (tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ocorreu um erro");
            a.setContentText("Preencha todos os campos");
            a.showAndWait();
            
            return false;
        }
        
        if (!validatePassword()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ocorreu um erro");
            a.setContentText("A senha deve conter pelo menos um numero, uma letra maiuscula, uma minuscula e um caracter especial");
            a.showAndWait();
            
            return false;
        }
        
        return true;
    }
    
    public boolean validatePassword() {
        if (tfPassword.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            return true;
        } else {
            return false;
        }
    }
    
    @FXML
    public void goToSignUp() throws IOException {
        Stage stage = (Stage) tfPassword.getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(App.class.getResource("signUp.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }
}