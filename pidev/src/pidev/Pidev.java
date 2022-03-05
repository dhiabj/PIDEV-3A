/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.user;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nayrouz
 */
public class Pidev extends Application {

    public static user Userconnected = new user();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/vues/LoginFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("FORU");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
