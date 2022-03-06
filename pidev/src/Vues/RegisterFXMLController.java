/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import vues.MainFXMLController;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class RegisterFXMLController implements Initializable {

    
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private PasswordField tfconfirmpass;
    @FXML
    private DatePicker date;
    @FXML
    private TextField tftelephone;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfemail;

    UserService us = new UserService();
    @FXML
    private Label newaccout;
    @FXML
    private Label error_numtel;
    @FXML
    private Button connexion;
    @FXML
    private Button fermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean whenMatchesTenDigitsNumber_thenCorrect(String number) {
        Pattern pattern = Pattern.compile("^\\d{8}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
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
    private void ajout(ActionEvent event) throws Exception {

        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
        String confirmPass = tfconfirmpass.getText();
        Date d = Date.valueOf(date.getValue());
        int tel = Integer.valueOf(tftelephone.getText());
        String adresse = tfadresse.getText();
        String role = "Client";
        if (whenMatchesTenDigitsNumber_thenCorrect(tftelephone.getText())) {
            user u = new user(nom, prenom, email, password, d, tel, adresse, role);
            if (us.ajouterUserPst(u)) {
                utils.Mailing.sendMail(u);
                AlertWindow("ForU", "Bienvenu " + prenom, Alert.AlertType.INFORMATION);
            } else {
                AlertWindow("ForU", "Essayer une autre fois", Alert.AlertType.ERROR);
            }
            Stage stage = (Stage) fermer.getScene().getWindow();
            stage.close();
            GotoFXML("LoginFXML", "ForU", event);
             }else{System.out.println("number is wrong!!!");}
//        }else{System.out.println("number is wrong!!!");}
        //error_numtel
    }

    @FXML
    private void gotoLogin(MouseEvent event) {
        GotoFXML("LoginFXML", "ForU", event);
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
         Stage stage = (Stage) fermer.getScene().getWindow();
         stage.close();
    }

}
