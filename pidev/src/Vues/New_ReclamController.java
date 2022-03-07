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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.Button;
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



/**
 * FXML Controller class
 *
 * @author Asus
 */
public class New_ReclamController implements Initializable {
    private Stage stage ;
    private Scene scene ;
    private Parent root ; 
    GUIutils gui ;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sujet_action(ActionEvent event) {
    }


    @FXML
    private void add_rec(ActionEvent event) {
        int s = 0 ;
        if ( sujet.getText().length()== 0 )
        {
            sujet.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(sujet).play();
            String title = "Echec" ;
            String message ="Remplissez le sujet SVP" ;
            TrayNotification tray = new TrayNotification() ; 
            AnimationType type = AnimationType.POPUP ;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(5000));
        }
        else{
            sujet.setStyle(null) ;
            s+=1;
           }
         if ( reclamation.getText().length()== 0 )
        {
            reclamation.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(reclamation).play();
            String title = "Echec" ;
            String message ="Remplissez la réclamation SVP" ;
            TrayNotification tray = new TrayNotification() ; 
            AnimationType type = AnimationType.POPUP ;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(5000));
        }
         else{
            reclamation.setStyle(null) ;
            s+=1;
         
         }
         if (s==2){
            //new animatefx.animation.Shake(sujet).play();
            String title = "Succé" ;
            String message ="Reclamation envoyée" ;
            TrayNotification tray = new TrayNotification() ; 
            AnimationType type = AnimationType.POPUP ;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(5000));
         }
        
        String titre = sujet.getText();
        String text = reclamation.getText();

        Reclamation_user rec = new Reclamation_user( titre, text);
        UserRecService ui = new UserRecService();
        ui.ajouterReclamation(rec);
        AdminService as = new AdminService();
        //System.out.println("aaaaaaaa");
        //System.out.println(as.latestId());
        
        Reclamation_admin ra = new Reclamation_admin(as.latestId());
        as.ajouterRep(ra);
//        gui= new GUIutils() ;
//        gui.alert(CONFIRMATION , "your reclamation was sent successfully") ;
        
        
        
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
