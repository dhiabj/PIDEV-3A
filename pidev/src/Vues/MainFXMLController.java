/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class MainFXMLController implements Initializable {

    @FXML
    private Button btngestUser;
    @FXML
    private Button btngestLivraison;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btngestEvenement;
    @FXML
    private Button btngestReclamation;
    @FXML
    private Button btngestMenu;

    /**
     * Initializes the controller class.
     */
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
    @FXML
    private void GotoGestUser(ActionEvent event) {
       GotoFXML("UserFXML", "ForU",event);
    }

   

    @FXML
    private void GotoGestionProfil(ActionEvent event) {
        GotoFXML("ProfilFXML", "ForU",event);
    }

    @FXML
    private void GotoGestEvenement(ActionEvent event) {
           GotoFXML("EvenementFXML", "ForU",event);
    }

    @FXML
    private void GotoGestReclamation(ActionEvent event) {
    }

    @FXML
    private void GotoGestMenu(ActionEvent event) {
    }

    @FXML
    private void GotoGestLivraison(ActionEvent event) {
        GotoFXML("LivraisonFXML", "ForU",event);
    }
     
    
}
