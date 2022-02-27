/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dhia
 */
public class AdminInterfaceController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private Button btn_ManageMenu;
    @FXML
    private Button btn_ManageIngredients;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ManageIngredients.setVisible(false);
        // TODO
    }    

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit (0);
    }

    @FXML
    private void handleManageMenu(ActionEvent event) throws Exception {
        SceneChanger.changeToScene(getClass(), event, "AdminMenu.fxml");
    }

    @FXML
    private void handleManageIngredients(ActionEvent event) throws Exception {
        SceneChanger.changeToScene(getClass(), event, "AdminIngredients.fxml");
    }
    
}
