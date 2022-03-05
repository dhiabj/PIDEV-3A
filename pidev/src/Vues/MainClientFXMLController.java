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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class MainClientFXMLController implements Initializable {

    @FXML
    private Button btnEventetReser;
    @FXML
    private Button btngestLivraison;
    @FXML
    private Button btnprofil;
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

    @FXML
    private void GotoEventetReser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
            window.setTitle("ForU");
            window.setScene(new Scene(root1));
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    private void GotoGesLivraison(ActionEvent event) {
    }

    @FXML
    private void GotoGestReclamation(ActionEvent event) {
    }

    @FXML
    private void GotoGestMenu(ActionEvent event) {
    }

    @FXML
    private void GotoGestionProfil(MouseEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
            window.setTitle("ForU");
            window.setScene(new Scene(root1));
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GotoGestCommande(ActionEvent event) {
    }
    
}
