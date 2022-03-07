/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Encapsulation_Reclamation_User;
import entities.Reclamation_admin;
import entities.Reclamation_user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.AdminService;
import services.UserRecService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Modif_recController implements Initializable {

    @FXML
    private TextField sujet_modif;
    @FXML
    private TextArea text_modif;
    @FXML
    private Button modifier;
    GUIutils gui ;
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
    private void modifierOnaction(ActionEvent event) {
        
       int s = 0 ;
        if ( sujet_modif.getText().length()== 0 )
        {
            sujet_modif.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(sujet_modif).play();
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
            sujet_modif.setStyle(null) ;
            s+=1;
           }
         if ( text_modif.getText().length()== 0 )
        {
            text_modif.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(text_modif).play();
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
            text_modif.setStyle(null) ;
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
        
        Reclamation_user rec = new Reclamation_user(Encapsulation_Reclamation_User.getId(),sujet_modif.getText(),text_modif.getText()) ;
        UserRecService service = new UserRecService();
        service.modifierRec(rec); 
        
        //gui.alert(CONFIRMATION , "your response was sent successfully") ;
    }

    @FXML
    private void retourOnaction(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rec_user_list.fxml"));
        try {
            Parent root = loader.load();
            bqckbtn.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
