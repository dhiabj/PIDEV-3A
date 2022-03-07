/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.LivraisonService;
import static pidev.Pidev.Userconnected;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class LivraisonClientController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView bqckbtn;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private Label nomLivraison;
    @FXML
    private Label nomLivreur;
    
    LivraisonService ls= new LivraisonService();
    UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id_client=Userconnected.getId();
        String NomLivraison = ls.GetNomLivraisionbyId(id_client);
        nomLivraison.setText(NomLivraison);
        int idLivrur = ls.GetIDLIVREURbyId(id_client);
        System.out.println(idLivrur);
        String NomLivreur = us.GetlivrbyId(idLivrur);
        nomLivreur.setText(NomLivreur);
        // TODO
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
    private void back(MouseEvent event) {
        GotoFXML("MainClientFXML", "ForU",event);
    }
    
}
