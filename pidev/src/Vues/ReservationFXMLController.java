/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.evenement;
import entities.reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import static java.sql.DriverManager.println;
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
import javafx.scene.control.Label;
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
import services.EvenementService;
import services.ReservationService;
import services.UserService;
import static pidev.Pidev.Userconnected;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private MenuBar menur;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btncreate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;

    @FXML
    private TableView<reservation> tablereserv;
    @FXML
    private TableColumn<reservation, String> colnomreser;
    @FXML
    private TableColumn<reservation, String> colidclient;
    @FXML
    private TableColumn<reservation, String> colidevent;
    @FXML
    private ComboBox<String> cbev;

    @FXML
    private RadioButton radtous;
    @FXML
    private ToggleGroup role;
    @FXML
    private TableView<evenement> tableevent;
    @FXML
    private TableColumn<evenement, String> colnom;
    @FXML
    private TableColumn<evenement, Date> coldate;
    @FXML
    private TableColumn<evenement, Integer> colpers;
    @FXML
    private TableColumn<evenement, String> colcatego;
    @FXML
    private TableColumn<evenement, String> coldescrip;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> nomevent;
    ObservableList<String> nomclient;
    ObservableList<String> nomREservation;
    ReservationService rv = new ReservationService();
    EvenementService ev = new EvenementService();
    UserService uv = new UserService();
    @FXML
    private Button btnpremodsupp;
    @FXML
    private ComboBox<String> cbnomreservation;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private Label UserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nomevent = ev.GetNamesEvent();
        this.nomclient = uv.GetNames();
        this.nomREservation = rv.GetNamesReservation();
        cbev.setItems(nomevent);
        UserName.setText(Userconnected.getPrenom()+" "+Userconnected.getNom());
        cbnomreservation.setItems(nomREservation);
        updateTable();
    }
    private void GotoFXML(String vue, String title,Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage =(Stage)((Node) aEvent.getSource()).getScene().getWindow() ;
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void updateTable() {
        radtous.setSelected(true);
        ObservableList<reservation> Reser = rv.readReservation();
        colnomreser.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colidclient.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colidevent.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        tablereserv.setItems(Reser);
        updateTableEvent();
    }

    public void updateTableEvent() {
        radtous.setSelected(true);
        ObservableList<evenement> Events = ev.readEvent();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colpers.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));
        colcatego.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        coldescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableevent.setItems(Events);
    }

    @FXML
    private void CreateReser(ActionEvent event) {
        String nom = tfnom.getText();
        int user_id = Userconnected.getId();
        System.out.println(user_id);
        String nomEV = cbev.getSelectionModel().getSelectedItem();
        int event_id = ev.GetIdEvent(nomEV);
        System.out.println(event_id);

        reservation r = new reservation(user_id, event_id, nom);

        if (rv.ajouterReservation(r)) {
            AlertWindow("Ajout avec succées", "Nouvelle Reservation pour " + Userconnected.getNom() +" " + Userconnected.getPrenom(), Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Echec d'ajout","L'évenement atteint le nombre maximum des personnes , Monsieur " +Userconnected.getNom() +" " + Userconnected.getPrenom(), Alert.AlertType.ERROR);
        }

        init();
    }

    public void init() {
        updateTable();
        tfnom.clear();
        cbev.setValue(null);
        updateTable();

    }


    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void ModifReser(ActionEvent event) {
        reservation r = tablereserv.getSelectionModel().getSelectedItem();

        String nom1 = tfnom.getText();
        String nomUser =  UserName.getText();
        int user_id = uv.GetIdUser(nomUser);
        String nomEvent = cbev.getSelectionModel().getSelectedItem();
        int event_id = ev.GetIdEvent(nomEvent);

        r.setNom(nom1);
        r.setUser_id(user_id);
        r.setEvent_id(event_id);
        rv.modifierReservationPst(r);
        init();

    }

   


    @FXML
    private void preModSuppReser(MouseEvent event) {
        reservation r = tablereserv.getSelectionModel().getSelectedItem();
        System.out.println(r.getId());
        tfnom.setText(r.getNom());
        String nomEvent = ev.GetNomEventbyId(r.getEvent_id());
        cbev.setValue(nomEvent);

    }

    @FXML
    private void DeleteReser(ActionEvent event) {
        reservation r = tablereserv.getSelectionModel().getSelectedItem();
        rv.suppReservationPst(r);
        init();
    }

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("MainClientFXML", "ForU",event);
    }



}
