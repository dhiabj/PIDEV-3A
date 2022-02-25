/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginSessionVues;

import Vues.MainFXMLController;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private Button btconnexion;
    @FXML
    private ImageView imageview;
    @FXML
    private Label mdpoublie;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private CheckBox chkbvoirmdp;
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void GotoFXML(String vue, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vues/"+vue+".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        String email = tfemail.getText();
        String mdp = pfpassword.getText();
        user u = us.getUserbyEmailPass(email, mdp);
        if (u.getId() != 0) {
            AlertWindow("Connexion avec succées", "Bonjour " + u.getNom() + ",\nInterface " + u.getRole(), Alert.AlertType.INFORMATION);
            if ("Admin".equals(u.getRole())) {
                GotoFXML("MainFXML", "Dashbord Admin");
            } else {
                GotoFXML("LivraisonFXML", "Gestion de livraison");
            }
        } else {
            AlertWindow("Connexion echouée", "Email ou mot de pass invalid!!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
