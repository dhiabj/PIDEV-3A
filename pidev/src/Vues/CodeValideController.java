/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class CodeValideController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Label backlogin;
    @FXML
    private Button envoyMailcode;
    @FXML
    private Button fermer;
    @FXML
    private Label error_email;
    @FXML
    private TextField code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void gotologin(MouseEvent event) {
        GotoFXML("LoginFXML", "Bienvenue", event);
    }

    @FXML
    private void gotomdp(ActionEvent event) {
        if ("5569112".equals(code.getText())) {
            AlertWindow("Code Correcte"," Reinialiser votre mot de passe ", Alert.AlertType.INFORMATION);
            GotoFXML("RecupMDP", "Bienvenue", event);
        }else {
            AlertWindow("Code Incorrecte", "Le code est invalide ! Essayer une autre fois!", Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();
    }

}
