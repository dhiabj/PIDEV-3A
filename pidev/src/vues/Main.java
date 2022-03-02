/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DataSource;

/**
 *
 * @author zacha
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        

        Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
       /* Parent root = FXMLLoader.load(getClass().getResource("AdminCommande.fxml"));*/
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        DataSource ds1=DataSource.getInstance();
        
    }
    
}
