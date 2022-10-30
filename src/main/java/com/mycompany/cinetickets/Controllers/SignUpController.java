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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matheus
 */
public class SignUpController implements Initializable {
    
    @FXML
    private Button btnGoToSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lbGoToSignIn;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfBirthDate;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void signUp() {
        
    }
    
    @FXML
    public void goToSignIn() throws IOException {
        Stage stage = (Stage) pfPassword.getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(App.class.getResource("signIn.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }
}
