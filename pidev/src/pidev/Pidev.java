/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Nayrouz
 */
public class Pidev extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("/LoginSessionVues/LoginFXML.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setTitle("FORU");
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    
    
}
