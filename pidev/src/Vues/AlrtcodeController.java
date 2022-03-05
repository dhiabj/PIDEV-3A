/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zacha
 */
public class AlrtcodeController implements Initializable {

    @FXML
    private Button dac_but;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
private void closeButtonAction(){
    // get a handle to the stage
    Stage stage = (Stage) dac_but.getScene().getWindow();
    // do what you have to do
    stage.close();
}
}
