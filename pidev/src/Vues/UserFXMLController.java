/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import entities.user;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class UserFXMLController implements Initializable {

    @FXML
    private TableView<user> tableuser;
    @FXML
    private TableColumn<user, String> colnom;
    @FXML
    private TableColumn<user, String> colprenom;
    @FXML
    private TableColumn<user, String> colemail;
    @FXML
    private TableColumn<user, String> colpassword;
    @FXML
    private TableColumn<user, Date> coldate;
    @FXML
    private TableColumn<user, Integer> coltelephone;
    @FXML
    private TableColumn<user, String> coladresse;
    @FXML
    private TableColumn<user, String> colrole;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbtri;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfconfirmpass;
    @FXML
    private DatePicker date;
    @FXML
    private TextField tftelephone;
    @FXML
    private TextField tfadresse;
    @FXML
    private ComboBox<String> cbrole;
    @FXML
    private Button btncreate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;
    @FXML
    private Button btntri;
    @FXML
    private RadioButton radtous;
    @FXML
    private ComboBox<String> cbrechpar;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private MenuBar menu;
    @FXML
    private RadioButton radadmin;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton radclient;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbrole.setItems(FXCollections.observableArrayList("Admin", "Client", "Livreur"));
        cbrechpar.setItems(FXCollections.observableArrayList("nom", "prenom", "email", "num_tel", "adresse"));
        cbtri.setItems(FXCollections.observableArrayList("nom", "prenom", "email"));
        updateTable();
        // TODO
    }

    @FXML
    public void updateTable() {
        System.out.println("aaaaaaaaa");
        radtous.setSelected(true);
        ObservableList<user> users = us.readAll();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    public void init() {
        updateTable();
        tfnom.clear();
        tfprenom.clear();
        tfemail.clear();
        tfpassword.clear();
        tfconfirmpass.clear();
        tftelephone.clear();
        tfadresse.clear();
        date.setValue(null);
        cbrole.setValue(null);
    }

    public void AlertWindow(String title, String contenu, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void CreateUser(ActionEvent event) {
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
        String confirmPass = tfconfirmpass.getText();
        Date d = Date.valueOf(date.getValue());
        int tel = Integer.valueOf(tftelephone.getText());
        String adresse = tfadresse.getText();
        String role = cbrole.getSelectionModel().getSelectedItem();

        user u = new user(nom, prenom, email, password, d, tel, adresse, role);
        if (us.ajouterUserPst(u)) {
            AlertWindow("Ajouter " + role, role + " ajouté avec succés", AlertType.INFORMATION);
        } else {
            AlertWindow("Ajouter " + role, "Echec d'ajout", AlertType.ERROR);
        }

        init();
    }

    @FXML
    private void SearchUser(KeyEvent event) {
        String search = tfrecherche.getText();
        if (search == null) {
            updateTable();
        } else {
            String searchby = cbrechpar.getSelectionModel().getSelectedItem();
            ObservableList<user> users = us.recherche(searchby, search);
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
            coltelephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
            tableuser.setItems(users);
        }

    }

    @FXML
    private void preModSupp(MouseEvent event) {
        user u = tableuser.getSelectionModel().getSelectedItem();
        System.out.println(u.getId());

        tfnom.setText(u.getNom());
        tfprenom.setText(u.getPrenom());
        tfemail.setText(u.getEmail());
        tfpassword.setText(u.getPassword());
        tfconfirmpass.setText(u.getPassword());
        LocalDate d = Instant.ofEpochMilli(u.getDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        date.setValue(d);
        tftelephone.setText(String.valueOf(u.getNum_tel()));
        tfadresse.setText(u.getAdresse());
        cbrole.setValue(u.getRole());
    }

    @FXML
    private void ModifUser(ActionEvent event) {
        user u = tableuser.getSelectionModel().getSelectedItem();

        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
        String confirmPass = tfconfirmpass.getText();
        Date d = Date.valueOf(date.getValue());
        int tel = Integer.valueOf(tftelephone.getText());
        String adresse = tfadresse.getText();
        String role = cbrole.getSelectionModel().getSelectedItem();

        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setPassword(password);
        u.setDate(d);
        u.setNum_tel(tel);
        u.setAdresse(adresse);
        u.setRole(role);
        if (us.modifierUserPst(u)) {
            AlertWindow("Modifier " + role, role + " modifié avec succés", AlertType.INFORMATION);
        } else {
            AlertWindow("Modifier " + role, "Echec de modificaition", AlertType.ERROR);
        }
        init();
    }

    @FXML
    private void DeleteUser(ActionEvent event) {
        user u = tableuser.getSelectionModel().getSelectedItem();
        if (us.suppUserPst(u)) {
            AlertWindow("Supprimer " + u.getRole(), u.getRole() + " supprimé avec succés", AlertType.INFORMATION);
        } else {
            AlertWindow("Supprimer " + u.getRole(), "Echec de suppression", AlertType.ERROR);
        }
        init();
    }

    @FXML
    private void TriUsers(ActionEvent event) {
        String tri = cbtri.getSelectionModel().getSelectedItem();
        ObservableList<user> users = us.tri(tri);
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
    private void getAdmins(ActionEvent event) {
        ObservableList<user> users = us.filterRole("Admin");
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
    private void getClients(ActionEvent event) {
        ObservableList<user> users = us.filterRole("Client");
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

}
