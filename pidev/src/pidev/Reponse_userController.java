
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foru_crud;

import entities.Encapsulation_Reclamation_Admin;
import entities.Encapsulation_Reclamation_User;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.GUIutils;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Reponse_userController implements Initializable {
    private Stage stage ;
    private Scene scene ;
    private Parent root ; 
    GUIutils gui ;
    @FXML
    private Button retour;
    @FXML
    private Label rep_sujet;
    @FXML
    private Label rep_rep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rep_sujet.setText(Encapsulation_Reclamation_User.getTitre());
        rep_rep.setText(Encapsulation_Reclamation_Admin.getReponse());
    }    

    @FXML
    private void retourOnaction(ActionEvent event) {
    
        try{
        root = FXMLLoader.load(getClass().getResource("rec_user_list.fxml")) ;
        stage= (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene) ;
        stage.show() ;
        }
        catch(IOException ex){
             Logger.getLogger(Foru_crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
