/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Encapsulation_Reclamation_Admin;
import entities.Encapsulation_Reclamation_User;
import entities.Reclamation_admin;
import entities.Reclamation_user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.AdminService;
import services.UserRecService;
import services.UserService;
import utils.DataSource;
import static pidev.Pidev.Userconnected;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Rec_user_listController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static String searched = "";
    @FXML
    private Button ajouter;
    private Label sujetRecList;
    @FXML
    private Pane pane;
    private List list;
    private List list1;
    GUIutils gui;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private ImageView bqckbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        render();
    }

    public void setLabel(String value) {
        this.sujetRecList.setText(value);
    }

    private void inspect_rec(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("reponse_user.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Rec_user_listController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouter_rec(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("New_Reclam.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Rec_user_listController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void render() {
        Reclamation_user ru = new Reclamation_user();
        Reclamation_admin ra = new Reclamation_admin();
        UserRecService us = new UserRecService();
        AdminService as = new AdminService();
        //pisteDao = new PisteDao();
        list = us.readAllbyiduser(Userconnected.getId());
        list1 = as.readAlls();
        //list = panierDao.displayAll(Panier_itemsController.searched);
        
        //List<String> joinPrix = panierDao.joinPrix(null);
        for(int i = 0; i<list.size();i++){
            
        try{
        ru = (Reclamation_user) list.get(i);
        ra = (Reclamation_admin) list1.get(i) ;
        final int j = i;
        //List<String> join = panierDao.joinLink((Panier)list.get(i));

        Button del = new Button();
        Button plus = new Button();
        Label desc = new Label();
        
        ImageView img = new ImageView();
        //img.setFitHeight(50);
        //img.setFitWidth(80);
        del.setStyle("-fx-background-color: red;\n" +
"    -fx-background-radius: 100;\n" +
"    -fx-text-fill: white;");
        del.setText("Supprimer");
        del.setPrefSize(100, 30);
        del.setLayoutX(700);
        del.setLayoutY(140 + (100 * i));
        desc.setLayoutX(200);
        desc.setStyle("-fx-font-family: initial;\n" +
"    -fx-background-color: #fcc200;\n" +
"    -fx-font-weight: bold;");
        desc.setLayoutY(140 + (100 * i));
        desc.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount() == 2) {
//                ReservationGuide maReservationGuide = tableGuide.getSelectionModel().getSelectedItem();
//                EncapsulationReservationGuide encapsulationReservationGuide = new EncapsulationReservationGuide(maReservationGuide.getId(), maReservationGuide.getId_guide(), maReservationGuide.getId_touriste(), maReservationGuide.getDate_reservation());
             Reclamation_user ruuu=(Reclamation_user) list.get(j);
             Reclamation_admin raa = (Reclamation_admin) list1.get(j);
             Encapsulation_Reclamation_Admin.setIdr(raa.getIdr());
             Encapsulation_Reclamation_Admin.setId(raa.getId());
             Encapsulation_Reclamation_Admin.setReponse(raa.getReponse());
             Encapsulation_Reclamation_User.setId(ruuu.getId());
             Encapsulation_Reclamation_User.setIdr(ruuu.getIdr());
             Encapsulation_Reclamation_User.setTexte(ruuu.getTexte());
             Encapsulation_Reclamation_User.setTitre(ruuu.getTitre());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modif_rec.fxml"));
                try {
                    Parent root = loader.load();
                    desc.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        
//        desc.setLayoutX(pane.getLayoutX()+10);
        desc.setTextFill(Color.BLACK);
        desc.setText( "Sujet : " + ru.getTitre());
        
        plus.setStyle("-fx-background-color: #228B22;\n" +
"    -fx-background-radius: 100;\n" +
"    -fx-text-fill: white;");
        plus.setText("Inspecter");
        plus.setPrefSize(100, 30);
        plus.setLayoutX(700);
        plus.setLayoutY(140 + 30 + (100 * i));
        
        
                del.setOnAction((event) -> {
                    as.suppRep((Reclamation_admin) list1.get(j));
                    
                    us.suppRec((Reclamation_user) list.get(j));
                    
                   // gui.newView(event, "rec_user_list.fxml", "panier");
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("rec_user_list.fxml"));
                 
            try {
                Parent root = loader.load();
                pane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Rec_user_listController.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
                });
        plus.setOnAction((event) -> {
         try{
            Reclamation_user ruuu=(Reclamation_user) list.get(j);
             Encapsulation_Reclamation_User.setId(ruuu.getId());
             Encapsulation_Reclamation_User.setIdr(ruuu.getIdr());
             Encapsulation_Reclamation_User.setTexte(ruuu.getTexte());
             Encapsulation_Reclamation_User.setTitre(ruuu.getTitre());
             Reclamation_admin raa = (Reclamation_admin) list1.get(j);
             Encapsulation_Reclamation_Admin.setIdr(raa.getIdr());
             Encapsulation_Reclamation_Admin.setId(raa.getId());
             Encapsulation_Reclamation_Admin.setReponse(raa.getReponse());
             FXMLLoader loader = new FXMLLoader(getClass().getResource("reponse_user.fxml"));
                 
            try {
                Parent root = loader.load();
                pane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Rec_user_listController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            //gui.newView(event, "reponse_user.fxml", "piste");
        }catch(Exception e){
            
        }
                });
//        GUIutils.Buttonimage(del, "del.png");
//        GUIutils.Buttonimage(plus, "inspect.png");
        pane.getChildren().add(img);
        pane.getChildren().add(del);
        pane.getChildren().add(plus);
        pane.getChildren().add(desc);
            
        } catch(Exception e){      
      }
     } 
    
    }

    @FXML
    private void retourOnaction(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("MainClientFXML.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("e");
        }
    }

}