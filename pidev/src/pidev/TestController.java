/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foru_crud;

import entities.Encapsulation_Reclamation_User;
import entities.Reclamation_user;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List list;
    @FXML
    private Pane pane;
     GUIutils gui= new GUIutils();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        render();
    } 
    private void render(){
        //search.setText(Panier_itemsController.searched);
        //panierDao = new PanierDao();
        
        Reclamation_user ru = new Reclamation_user();
        UserService us = new UserService();
        //pisteDao = new PisteDao();
        list = us.readAll();
        //list = panierDao.displayAll(Panier_itemsController.searched);
        
        //List<String> joinPrix = panierDao.joinPrix(null);
        for(int i = 0; i<list.size();i++){
            
        try{
        ru = (Reclamation_user) list.get(i);
        final int j = i;
        //List<String> join = panierDao.joinLink((Panier)list.get(i));

        Button del = new Button();
        Button plus = new Button();
        Label desc = new Label();
        
        ImageView img = new ImageView();
        img.setFitHeight(50);
        img.setFitWidth(80);
        del.setPrefSize(20, 20);
        del.setLayoutX(550);
        del.setLayoutY(120 + (100 * i));
        
        
        desc.setLayoutX(pane.getLayoutX()+10);
        desc.setTextFill(Color.WHITE);
        desc.setText( "Sujet : " + ru.getTitre());
        
        plus.setPrefSize(20, 20);
        plus.setLayoutX(550);
        plus.setLayoutY(120 + 30 + (100 * i));

        
                del.setOnAction((event) -> {
                    us.suppRec((Reclamation_user) list.get(j));
                    gui.newView(event, "test.fxml", "panier");
                });
        plus.setOnAction((event) -> {
         try{
        Reclamation_user ruuu=(Reclamation_user) list.get(j);
             Encapsulation_Reclamation_User.setId(ruuu.getId());
             Encapsulation_Reclamation_User.setIdr(ruuu.getIdr());
             Encapsulation_Reclamation_User.setTexte(ruuu.getTexte());
             Encapsulation_Reclamation_User.setTitre(ruuu.getTitre());
        
            gui.newView(event, "reponse_user.fxml", "piste");
        }catch(Exception e){
            
        }
                });
//        GUIutils.Buttonimage(del, "del.png");
//        GUIutils.Buttonimage(plus, "inspect.png");
        //pane.getChildren().add(img);
        //pane.getChildren().add(del)
        pane.getChildren().add(plus);
        pane.getChildren().add(desc);
            
        } catch(Exception e){      
      }
     } 
    
    }
}
