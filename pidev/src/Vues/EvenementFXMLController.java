/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.EvenementService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class EvenementFXMLController implements Initializable {

    @FXML
    private MenuBar menu;
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
    @FXML
    private Button btncreate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;
    @FXML
    private TableView<evenement> tableevent;
    @FXML
    private DatePicker datecb;
    @FXML
    private ComboBox<Integer> cbnbper;
    @FXML
    private TextField tfnom;
    @FXML
    private ComboBox<String> cbcatego;
    @FXML
    private TextField tfdescrip;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private RadioButton radvegan;
    @FXML
    private RadioButton radtous;
    
    
    /**
     * Initializes the controller class.
     */
    
    EvenementService ev = new EvenementService();
    @FXML
    private ToggleGroup groupby;
    @FXML
    private Button btnReturnMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbnbper.setItems((FXCollections.observableArrayList(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)));
        cbcatego.setItems(FXCollections.observableArrayList("Vegan", "Non Vegan", "Both"));
        updateTable();
        // TODO
    }

    @FXML
    public void updateTable() {
        radtous.setSelected(true);
        ObservableList<evenement> Events = ev.readEvent();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colpers.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));
        colcatego.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        coldescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableevent.setItems(Events);
    }

    public void init() {
        updateTable();
        tfnom.clear();
        tfdescrip.clear();
        datecb.setValue(null);
        cbcatego.setValue(null);
        cbnbper.setValue(null);
    }

    @FXML
    private void CreateEvent(ActionEvent event) {
        
        String nom = tfnom.getText();
        Date d = Date.valueOf(datecb.getValue());
        Integer nb_personne = cbnbper.getSelectionModel().getSelectedItem();
        String categorie = cbcatego.getSelectionModel().getSelectedItem();
        String description = tfdescrip.getText();

        evenement e1 = new evenement(nom, d, nb_personne, categorie, description);
        String path =ev.QR(nom);
        System.out.println(path);
        ev.ajouterEventPst(e1);
        init();
    }

    @FXML
    private void preModSupp(MouseEvent event) {
        evenement e = tableevent.getSelectionModel().getSelectedItem();
        System.out.println(e.getId());

        tfnom.setText(e.getNom());
        LocalDate d = Instant.ofEpochMilli(e.getDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        datecb.setValue(d);
        cbnbper.setValue(e.getNbr_personnes());
        cbcatego.setValue(e.getCategorie());
        tfdescrip.setText(e.getDescription());
    }

    @FXML
    private void ModifEvent(ActionEvent event) {
        evenement e = tableevent.getSelectionModel().getSelectedItem();

        String nom = tfnom.getText();
        Date d = Date.valueOf(datecb.getValue());
        Integer nb_personne = cbnbper.getSelectionModel().getSelectedItem();
        String Categorie = cbcatego.getSelectionModel().getSelectedItem();
        String Description = tfdescrip.getText();

        e.setNom(nom);
        e.setDate(d);
        e.setNbr_personnes(nb_personne);
        e.setCategorie(Categorie);
        e.setDescription(Description);
        ev.modifierEvenementPst(e);
        init();
        
    }

    @FXML
    private void DeleteEvent(ActionEvent event) {
        evenement e = tableevent.getSelectionModel().getSelectedItem();
        ev.suppEvenementPst(e);
        init();
    }

    @FXML
    private void getEventsVegan(ActionEvent event) {
        radvegan.setSelected(true);
        radvegan.requestFocus();
        ObservableList<evenement> Events = ev.filterVegan("Vegan");
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colpers.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));
        colcatego.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        coldescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableevent.setItems(Events);
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
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("MainFXML", "ForU",event);
    }
    

}
