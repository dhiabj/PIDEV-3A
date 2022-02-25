/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import entities.livraison;
import entities.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.LivraisonService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class LivraisonFXMLController implements Initializable {

    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbtri;
    @FXML
    private Button btncreate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;
    @FXML
    private Button btntri;
    @FXML
    private ComboBox<String> cbrechpar;
    @FXML
    private MenuBar menu;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private ComboBox<String> cbuserid;
    @FXML
    private ComboBox<livraison> cblivid;
    @FXML
    private ComboBox<Integer> cbcommandeid;
    @FXML
    private ComboBox<String> cbetat;
    @FXML
    private TableView<livraison> tablelivraison;
    @FXML
    private TableColumn<livraison, Integer> coluserid;
    @FXML
    private TableColumn<livraison, Integer> collivid;
    @FXML
    private TableColumn<livraison, Integer> colcommandeid;
    @FXML
    private TableColumn<livraison, String> colnomliv;
    @FXML
    private TableColumn<livraison, String> coletat;
    
    
    UserService us = new UserService();
    LivraisonService ls = new LivraisonService();
    
    ObservableList<user> Users;
    ObservableList<String> UsersName;
    ObservableList<livraison> Livs;
    @FXML
    private Button btnnnn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Users = us.readAll();
        this.UsersName = us.GetNames();
        this.Livs = ls.readLivraison();
        cbuserid.setItems(UsersName);
        cblivid.setItems(Livs);
        cbcommandeid.setItems(FXCollections.observableArrayList(01,02,03));
        cbetat.setItems(FXCollections.observableArrayList("En cours", "Livrée", "Annulé"));
        //cbuserid.setCellFactory(value);
    }    


    @FXML
    private void preModSupp(MouseEvent event) {
    }

    @FXML
    private void updateTable(ActionEvent event) {
    }

    @FXML
    private void SearchUser(KeyEvent event) {
    }



    @FXML
    private void CreateLiv(ActionEvent event) {
    }

    @FXML
    private void ModifLiv(ActionEvent event) {
    }

    @FXML
    private void DeleteLiv(ActionEvent event) {
    }

    @FXML
    private void TriLiv(ActionEvent event) {
    }
    
}
