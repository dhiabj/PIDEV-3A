/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Dhia
 */
public class AlertDialog {
    public static void display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);
        
        Label label = new Label();
        label.setText(message);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction(e-> window.close());
        
        VBox layout = new VBox();
        layout.getChildren().addAll(label, buttonOk);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }
}
