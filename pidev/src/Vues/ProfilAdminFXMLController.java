/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.user;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static pidev.Pidev.Userconnected;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ProfilAdminFXMLController implements Initializable {

    private String Imguser;
    @FXML
    private MenuBar menu;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfadresse;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private ImageView UserImg;
    @FXML
    private Label ImageName;
    @FXML
    private ImageView ImageUserLogedIn;
    @FXML
    private Label UserName;
    @FXML
    private Text logout;
    @FXML
    private ImageView ImageUserLogedIn1;
    UserService us = new UserService();
    @FXML
    private ImageView bqckbtn;
    @FXML
    private Button upload;
    @FXML
    private Button ModifUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        tfnom.setText(Userconnected.getNom());
        tfprenom.setText(Userconnected.getPrenom());
        tfadresse.setText(Userconnected.getAdresse());
        ImageName.setText(Userconnected.getImage());
        

        UserName.setText(Userconnected.getPrenom()+" "+Userconnected.getNom());
        
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
    private void ModifUser(ActionEvent event)  throws IOException, Exception {

        user u = new user(Userconnected.getId(), tfnom.getText(), tfprenom.getText(),tfadresse.getText());
        Userconnected.setNom(tfnom.getText());
        Userconnected.setPrenom(tfprenom.getText());
        Userconnected.setAdresse(tfadresse.getText());
        if (us.update(Userconnected)) {
            UserName.setText(Userconnected.getPrenom()+" "+Userconnected.getNom());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme"+ UserName.getText()+ " "+ " Vos donnés personelles sont modifiés !", ButtonType.CLOSE);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                alert.show();
                
        }
    }


    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:\\Users\\Nayrouz\\OneDrive\\Desktop\\PIDEV-3A\\pidev\\src\\Ressources" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Coucou");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("C:\\Users\\Nayrouz\\OneDrive\\Desktop\\PIDEV-3A\\pidev\\src\\Ressources" + f.getName());
//            System.out.println(file.toURI().toString());
            UserImg.setImage(new Image(file.toURI().toString()));
            Imguser = f.getName();
            System.out.println(Imguser);
            ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }
    }

    @FXML
    private void UpdateProfile(MouseEvent event) {
         GotoFXML("ProfilAdminFXML", "ForU",event);
    }
    
    @FXML
    private void ResetPassword(MouseEvent event) {
        GotoFXML("ResetPasswordAdmin", "ForU",event);
    }

    @FXML
    private void DeleteAccount(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (us.suppUserPst(Userconnected)) {
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    LoginFXMLController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }
            }

        }
    }

    @FXML
    private void Logout(MouseEvent event)throws IOException, Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deconnexion");
        alert.setContentText("Voulez-vous vraiment Déconnecter?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        Userconnected.setId(0);
        Userconnected.setPrenom("");
        Userconnected.setNom("");
        Userconnected.setEmail("");
        Userconnected.setPassword("");
        Userconnected.setAdresse("");
        Userconnected.setImage("");
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginFXMLController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }}

    @FXML
    private void backbtnmenu(MouseEvent event) {
        GotoFXML("MainFXML", "ForU",event);
    }
    
}
