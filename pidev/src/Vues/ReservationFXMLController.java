/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import entities.evenement;
import entities.reservation;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.EvenementService;
import services.ReservationService;
import services.UserService;

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
    private TableColumn<reservation, String> colnom;
    @FXML
    private TableColumn<reservation, String> colidclient;
    @FXML
    private TableColumn<reservation, String> colidevent;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbidclient;
    @FXML
    private ComboBox<String> cbev;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> nomevent;
    ObservableList<String> nomclient;
    ReservationService rv = new ReservationService();
    EvenementService ev = new EvenementService();
    UserService uv = new UserService();
    @FXML
    private RadioButton radtous;
    @FXML
    private ToggleGroup role;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     this.nomevent= ev.GetNamesEvent();
     this.nomclient= uv.GetNames();
     cbev.setItems(nomevent);
     cbidclient.setItems(nomclient);
     updateTable();
    }

    
    @FXML
    public void updateTable() {
        radtous.setSelected(true);
        ObservableList<reservation> Reser = rv.readReservation();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colidclient.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colidevent.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        tablereserv.setItems(Reser);
    }
    
    @FXML
    private void CreateEvent(ActionEvent event) {
    }

    @FXML
    private void ModifEvent(ActionEvent event) {
    }

    @FXML
    private void DeleteEvent(ActionEvent event) {
    }

    @FXML
    private void preModSupp(MouseEvent event) {
    }

    @FXML
    private void SearchUser(KeyEvent event) {
    }
    
    

}
