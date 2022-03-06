/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.MD5Utils;
import static pidev.Pidev.Userconnected;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ResetPasswordFXMLController implements Initializable {

    @FXML
    private MenuBar menu;
    @FXML
    private ImageView ImageUserLogedIn;
    @FXML
    private TextField pass1;
    @FXML
    private TextField pass2;
    @FXML
    private TextField pass3;
    @FXML
    private ImageView ImageUserLogedIn1;
    @FXML
    private Label NomPrenom;
    UserService us = new UserService();
    @FXML
    private Button Reset;
    @FXML
    private ImageView imageviewlogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file1 = new File("/" + Userconnected.getImage());
        ImageUserLogedIn.setImage(new Image(file1.toURI().toString()));

      
       NomPrenom.setText(Userconnected.getPrenom()+" "+Userconnected.getNom());
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

    @FXML
    private void UpdateProfile(MouseEvent event) {
        GotoFXML("ProfilFXML", "ForU", event);
    }

    @FXML
    private void DeleteAccount(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (us.suppUserPst(Userconnected)) {
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    LoginFXMLController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }
            }

        }
    }

    @FXML
    private void Logout(MouseEvent event) throws IOException, Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deconnexion");
        alert.setContentText("Voulez-vous vraiment Déconnecter?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Userconnected.setId(0);
            Userconnected.setPrenom("");
            Userconnected.setNom("");
            Userconnected.setEmail("");
            Userconnected.setPassword("");
            Userconnected.setAdresse("");
            Userconnected.setImage("");
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            try {
                Parent root = LOADER.load();
                Scene sc = new Scene(root);
                LoginFXMLController cntr = LOADER.getController();
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(sc);
                window.show();
            } catch (IOException ex) {

            }
        }
    }

    @FXML
    private void Reset(ActionEvent event) throws SQLException {
        if (MD5Utils.cryptage(pass1.getText()).equals(Userconnected.getPassword())) {
            if (pass2.getText().equals(pass3.getText())) {
                if (us.ResetPassword(pass2.getText(), Userconnected.getId())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme " + Userconnected.getPrenom() + " votre mot de passe a été bien modifier !", ButtonType.CLOSE);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Les deux mots de passe sont différents !", ButtonType.CLOSE);
            alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Vérifier votre mot de passe actuel !", ButtonType.CLOSE);
            alert.show();
        }
    }

    @FXML
    private void BackToPage(ActionEvent event) {
        GotoFXML("MainClientFXML", "ForU", event);
    }

    @FXML
    private void ResetPassword(MouseEvent event) {
        GotoFXML("ResetPassword", "ForU", event);
    }

}
