package foru_crud;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import foru_crud.Foru_crud;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class GUIutils {
    public void  newView(ActionEvent event, String GUI, String title){
        try{
           
            Parent root = FXMLLoader.load(getClass().getResource(GUI));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();}catch(IOException ex) {
               Logger.getLogger(Foru_crud.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    public void closeView(ActionEvent event, String view){
        try{
        Parent root = FXMLLoader.load(getClass().getResource(view));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            }catch(Exception e) {alert(AlertType.ERROR,view + " can not be found!");}
    }
    
    public void closeNOpen(ActionEvent event, String viewO, String viewC, String title){
        //zeyda
    }
  
        // action event
     public static void alert(AlertType type, String msg){
                Alert a = new Alert(type, msg);
                a.show();
            }

     public static void alert(){
                Alert a = new Alert(AlertType.NONE, 
                              "default Dialog",ButtonType.APPLY);

                a.show();
            }
        
   public static void Buttonimage(Button button, String name){
    ImageView view = new ImageView("/com/mstore/ressources/"+name);
    view.setFitHeight(button.getPrefHeight());
    view.setFitWidth(button.getPrefWidth());
    button.setGraphic(view);
    view.setPreserveRatio(true);
   }
}
