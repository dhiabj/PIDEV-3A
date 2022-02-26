/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Menu;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dhia
 */
public class MenuController implements Initializable {

    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_prix;
    @FXML
    private ImageView menuImage;
    
    private Menu menu;
    private MyListener myListener;
    
    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(menu);
    }
    
    public final static String CURRENCY = " TND";
    public void setData(Menu menu, MyListener myListener){
        this.myListener = myListener;
        this.menu = menu;
        lb_titre.setText(menu.getTitre());
        lb_prix.setText(String.valueOf(menu.getPrix()) + CURRENCY);
        File file = new File(menu.getImage());
        Image image = new Image(file.getAbsoluteFile().toURI().toString());
        menuImage.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
