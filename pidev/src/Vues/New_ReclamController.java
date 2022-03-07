/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Reclamation_admin;
import entities.Reclamation_user;
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
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.AdminService;
import services.UserRecService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.GUIutils;
import static pidev.Pidev.Userconnected;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class New_ReclamController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    GUIutils gui;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea reclamation;
    @FXML
    private Button env_rec;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private ImageView bqckbtn;
    @FXML
    private Label nomUser;

    UserRecService rs = new UserRecService();
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomUser.setText(Userconnected.getPrenom() + " " + Userconnected.getNom());
        // TODO
    }

    @FXML
    private void sujet_action(ActionEvent event) {
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
    private void add_rec(ActionEvent event) throws Exception {
        int s = 0;
        if (rs.getnbRec(Userconnected.getId()) >= 5) {
            System.out.println("you are banned");
            AlertWindow("Banned", "Malheureusement Mme/M Tu es banni à cause de Spam, Essayer de connecter aprés 10 jours !!", Alert.AlertType.WARNING);
            Userconnected.setEtat("banned");
            us.BanUser(Userconnected);
            Userconnected=null;
            GotoFXML("LoginFXML","ForU", event);
        } else {
            if (sujet.getText().length() == 0) {
                sujet.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
                new animatefx.animation.Shake(sujet).play();
                String title = "Echec";
                String message = "Remplissez le sujet SVP";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(5000));
            } else {
                sujet.setStyle(null);
                s += 1;
            }
            if (reclamation.getText().length() == 0) {
                reclamation.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
                new animatefx.animation.Shake(reclamation).play();
                String title = "Echec";
                String message = "Remplissez la réclamation SVP";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(5000));
            } else {
                reclamation.setStyle(null);
                s += 1;

            }
            if (s == 2) {
                //new animatefx.animation.Shake(sujet).play();
                String title = "Succé";
                String message = "Reclamation envoyée";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(5000));
                String titre = sujet.getText();
                String text = reclamation.getText();
                int user_id = Userconnected.getId();
                Reclamation_user rec = new Reclamation_user(titre, text, user_id);
                UserRecService ui = new UserRecService();
                ui.ajouterReclamation(rec);
                AdminService as = new AdminService();
                //System.out.println("aaaaaaaa");
                //System.out.println(as.latestId());

                Reclamation_admin ra = new Reclamation_admin(as.latestId());
                as.ajouterRep(ra);
            }

            //System.out.println("aaaaaaaa");
            //System.out.println(as.latestId());
//        gui= new GUIutils() ;
//        gui.alert(CONFIRMATION , "your reclamation was sent successfully") ;
        }

    }

    @FXML
    private void retour(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("rec_user_list.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("e");
        }
    }

}
