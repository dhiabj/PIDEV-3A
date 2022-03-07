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
import static pidev.Pidev.Userconnected;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class CodeVerifController implements Initializable {

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

    UserService us = new UserService();

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
    private void handleCloseButtonAction(MouseEvent event) {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void gotologin(ActionEvent event) {
        if ("5569112".equals(code.getText())) {
            Userconnected.setEtat("verified");
            if (us.modifierUserEtat(Userconnected)) {
                AlertWindow("Code Correcte", " Votre compte est bien verifié ", Alert.AlertType.INFORMATION);
                GotoFXML("LoginFXML", "Bienvenue", event);
            }
        } else {
            AlertWindow("Code Incorrecte", "Essayer une autre fois!", Alert.AlertType.ERROR);
        }
    }

}
