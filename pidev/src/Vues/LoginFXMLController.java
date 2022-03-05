/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;
import services.UserService;
import static pidev.Pidev.Userconnected;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import utils.Utils;


/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private CheckBox chkbvoirmdp;
    @FXML
    private Label mdpoublie;
    @FXML
    private Label newaccout;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();
    @FXML
    private Button connexion;
    @FXML
    private Button fermer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void GotoFXML(String vue, String title,Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage =(Stage)((Node) aEvent.getSource()).getScene().getWindow() ;
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
    private void MdpOublie(MouseEvent event) {
    }

    @FXML
    private void gotoREGISTER(MouseEvent event) {
        GotoFXML("RegisterFXML", "Bienvenue",event);
    }

    @FXML
    private void connexion(ActionEvent event) {
         String email = tfemail.getText();
        String mdp = pfpassword.getText();
        Userconnected = us.getUserbyEmailPass(email, mdp);
        if (Userconnected.getId() != 0) {
            Userconnected.setId(Userconnected.getId());
            Userconnected.setEmail(Userconnected.getEmail());
            Userconnected.setPassword(Userconnected.getPassword());
            AlertWindow("Connexion avec succées", "Je vous souhaite la bienvenue Mr/Mme " + Userconnected.getNom() + ",\nInterface " + Userconnected.getRole(), Alert.AlertType.INFORMATION);
            if ("Admin".equals(Userconnected.getRole())) {
                GotoFXML("MainFXML", "Dashbord Admin",event);
            } else {
                GotoFXML("MainClientFXML", "ForU",event);
            }
        } else {
            AlertWindow("Connexion echouée", "Email ou mot de pass invalid!!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
     Stage stage = (Stage) fermer.getScene().getWindow();
         stage.close();
    }

}
