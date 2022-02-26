/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.evenement;
import java.util.List;
import utils.DataSource;
import services.EvenementService;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nayrouz
 */
public class Pidev extends Application {

    public static void main(String[] args) {
        launch(args);
        DataSource ds1 = DataSource.getInstance();
     
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vues/MainFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("FORU");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      
    
}
