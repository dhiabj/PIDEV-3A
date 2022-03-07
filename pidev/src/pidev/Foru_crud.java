/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foru_crud;

import entities.Reclamation_admin;
import utils.DataSource;
import entities.Reclamation_user ;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.UserService;
import service.AdminService ;





public class Foru_crud extends Application {
   //private Stage primaryStage;
    //private Parent parentPage;

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
       
        
        launch(args);
    }
        
       @Override
    public void start (Stage stage ) throws Exception {
       /* this.primaryStage = stage;
        
        
                
        parentPage= FXMLLoader.load(getClass().getResource("rec_user_list.fxml"));
        
        Scene scene = new Scene(parentPage);
        
        stage.setScene(scene);
        stage.show();*/
         try{
            Parent root = FXMLLoader.load(getClass().getResource("rec_user_list.fxml")) ;
            Scene scene = new Scene(root) ;
            stage.setScene(scene);
            stage.show();
            
        }
        catch(IOException ex){
             Logger.getLogger(Foru_crud.class.getName()).log(Level.SEVERE, null, ex);
        }
      
 
        
    }
    
        
    
    
}
