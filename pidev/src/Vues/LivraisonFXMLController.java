/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.livraison;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static pidev.Pidev.Userconnected;
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
    private ComboBox<String> cblivid;
    @FXML
    private ComboBox<Integer> cbcommandeid;
    @FXML
    private ComboBox<String> cbetat;
    @FXML
    private TableView<livraison> tablelivraison;
    @FXML
    private TableColumn<livraison, Integer> colcommandeid;
    @FXML
    private TableColumn<livraison, String> coletat;
    @FXML
    private TableColumn<livraison, String> colnomlivraison;
    @FXML
    private TableColumn<livraison, Integer> coluserid;
    @FXML
    private TableColumn<livraison, Integer> collivreurid;

    @FXML
    private Button btnReturnMenu;
    @FXML
    private RadioButton radencours;
    @FXML
    private RadioButton radtous;
    @FXML
    private RadioButton radlivre;
    @FXML
    private TextField tfnomlivraison;
    @FXML
    private RadioButton radannulle;
    @FXML
    private ToggleGroup etat;

    UserService us = new UserService();
    LivraisonService ls = new LivraisonService();

    ObservableList<user> Users;
    ObservableList<String> UsersName;
    ObservableList<String> Livs;
    ObservableList<String> nomclient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        this.Users = us.readAll();
        this.UsersName = us.GetNamesClient();
        this.Livs = us.GetNamesLivreur();
        cbuserid.setItems(UsersName);
        cblivid.setItems(Livs);
        cbcommandeid.setItems(FXCollections.observableArrayList(01, 02, 03));
        cbetat.setItems(FXCollections.observableArrayList("En cours", "Livrée", "Annulé"));
        cbrechpar.setItems(FXCollections.observableArrayList("nom", "etat"));
        cbtri.setItems(FXCollections.observableArrayList("nom", "etat"));
        //cbuserid.setCellFactory(value);
    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void preModSupp(MouseEvent event) {
        livraison l1 = tablelivraison.getSelectionModel().getSelectedItem();
        System.out.println(l1.getId());

        tfnomlivraison.setText(l1.getNom());
        String nomuser = us.GetNomutilisateurbyId(l1.getUser_id());
        cbuserid.setValue(nomuser);
        String nomlivreur = us.GetNomutilisateurbyId(l1.getLivreur_id());
        cblivid.setValue(nomlivreur);
        cbcommandeid.setValue(l1.getCommande_id());
        cbetat.setValue(l1.getEtat());
    }

    @FXML
    private void updateTable() {
        radtous.setSelected(true);
        ObservableList<livraison> Livraison1 = ls.readLivraison();
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(Livraison1);
    }

    public void init() {
        updateTable();
        cbuserid.setValue(null);
        cbcommandeid.setValue(null);
        cblivid.setValue(null);
        cbetat.setValue(null);
        cbrechpar.setValue(null);
        cbtri.setValue(null);
    }

    @FXML
    private void SearchUser(KeyEvent event) {
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void CreateLiv(ActionEvent event) {
        String nom_livraison = tfnomlivraison.getText();
        String user_nom = cbuserid.getSelectionModel().getSelectedItem();
        String livreur_nom = cblivid.getSelectionModel().getSelectedItem();
        int commande_id = cbcommandeid.getSelectionModel().getSelectedItem();
        String etat = cbetat.getSelectionModel().getSelectedItem();
        int user_id = us.GetIdUser(user_nom);
        int livrur_id = us.GetIdUser(livreur_nom);

        livraison l = new livraison(user_id, livrur_id, commande_id, nom_livraison, etat);

        if (ls.ajouterLivraisonPst(l)) {
            AlertWindow("Ajout avec succées", "Nouvelle Livraison ", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Echec d'ajout", "Essayer une autre fois ", Alert.AlertType.ERROR);
        }

        init();
    }

    @FXML
    private void ModifLiv(ActionEvent event) {
        livraison l1 = tablelivraison.getSelectionModel().getSelectedItem();

        String nom_livraison = tfnomlivraison.getText();
        String user_nom = cbuserid.getSelectionModel().getSelectedItem();
        String livreur_nom = cblivid.getSelectionModel().getSelectedItem();
        int commande_id = cbcommandeid.getSelectionModel().getSelectedItem();
        String etat = cbetat.getSelectionModel().getSelectedItem();
        int user_id = us.GetIdUser(user_nom);
        int livrur_id = us.GetIdUser(livreur_nom);

        l1.setNom(nom_livraison);
        l1.setLivreur_id(user_id);
        l1.setLivreur_id(livrur_id);
        l1.setCommande_id(commande_id);
        l1.setEtat(etat);
        ls.modifierLivraisonPst(l1);
        init();
    }

    @FXML
    private void DeleteLiv(ActionEvent event) {
        livraison l1 = tablelivraison.getSelectionModel().getSelectedItem();
        ls.suppLivraisonPst(l1);
        init();
    }

    @FXML
    private void TriLiv(ActionEvent event) {
    }

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("MainFXML", "ForU", event);
    }

    @FXML
    private void getEncours(ActionEvent event) {
        ObservableList<livraison> livraison = ls.filterEtat("En cours");
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(livraison);

    }

    @FXML
    private void getLivree(ActionEvent event) {
        ObservableList<livraison> livraison = ls.filterEtat("Livrée");
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(livraison);
    }

    @FXML
    private void getannulee(ActionEvent event) {
        ObservableList<livraison> livraison = ls.filterEtat("Annulé");
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(livraison);
    }

}
