/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Encapsulation_Reclamation_User;
import entities.Reclamation_admin;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.AdminService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Reponse_adminController implements Initializable {

    @FXML
    private Label sujet;
    @FXML
    private Label reclamation;
    
    @FXML
    private Button envoyer;
    private Stage stage ;
    private Scene scene ;
    private Parent root ;
    private Pane pane;
    private List list;
    GUIutils gui ;
    @FXML
    private TextArea rp;
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
        sujet.setText(Encapsulation_Reclamation_User.getTitre());
        reclamation.setText(Encapsulation_Reclamation_User.getTexte());
        AdminService as= new AdminService();
        Reclamation_admin ra= as.displayById(Encapsulation_Reclamation_User.getId());
        rp.setText(ra.getReponse());
    }    


    @FXML
    private void envoyerOnaction(ActionEvent event) {
        int s = 0 ;
        if ( rp.getText().length()== 0 )
        {
            rp.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(rp).play();
            String title = "Echec" ;
            String message ="Remplissez la réponse SVP" ;
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
        if (s==1){
            new animatefx.animation.Shake(sujet).play();
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

        
        String reponse = rp.getText();
      
        
        Reclamation_admin rec = new Reclamation_admin(Encapsulation_Reclamation_User.getId(),reponse) ;
        AdminService service = new AdminService();
        service.modifierRep(rec); 
        
        
        //gui.alert(CONFIRMATION , "your response was sent successfully") ;
    }

    @FXML
    private void retourOnaction(MouseEvent event) {
        try{
        root = FXMLLoader.load(getClass().getResource("rec_admin_list.fxml")) ;
        stage= (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene) ;
        stage.show() ;
        }
        catch(IOException ex){
             System.out.println("e");
        }
    }
    
}
