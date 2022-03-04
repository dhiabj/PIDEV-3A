/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Commande;
import entities.Menu;
import entities.MenuCommande;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import services.CommandeService;
import services.MenuCommandeService;

/**
 * FXML Controller class
 *
 * @author zacha
 */
public class PanierInterfaceController implements Initializable {

    @FXML
    private Button Validerbtn;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label total;
    private MyListener myListener;
    private int mcId;
    private float totalP;
    
    MenuCommandeService mcs = new MenuCommandeService();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(panierList);
        grid.getChildren().clear();
        showPanier(mcs.afficherMenuCommande());
        total.setText(String.valueOf(mcs.afficherTotal()) + MenuController.CURRENCY);
        totalP = mcs.afficherTotal();
        //System.out.println(mcs.afficherMenuCommande());
        // TODO
    }
    
    private void showPanier(List<Menu> menus){
        //menus.clear();
        //System.out.println(menus.isEmpty());
        if(menus.size()>0){
            myListener = new MyListener() {
                @Override
                public void onClickListener(Menu menu) {
                    mcId = menu.getMcId();
                    MenuCommandeService mcs = new MenuCommandeService();
                    MenuCommande mc = new MenuCommande(mcId);
                    //System.out.println(PanierInterfaceController.mcId);
                    mcs.supCommande(mc);
                    //grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
                    //showPanier(panierList);
                    grid.getChildren().clear();
                    showPanier(mcs.afficherMenuCommande());
                    total.setText(String.valueOf(mcs.afficherTotal()) + MenuController.CURRENCY);
                    totalP = mcs.afficherTotal();
                    //System.out.println(mcs.afficherMenuCommande());
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
        for(int i=0; i<menus.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/vues/PanierMenu.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PanierMenuController panierMenuController = fxmlLoader.getController();
            panierMenuController.setData(menus.get(i), myListener);
            
            if(column == 1){
                column = 0;
                row++;
            }
            grid.add(anchorPane, column++, row);
            
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
            
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            GridPane.setMargin(anchorPane, new Insets(10));
            }
            } catch (IOException ex) {
                Logger.getLogger(UserMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        //System.out.println(menus);
    }
    
    CommandeService cs = new CommandeService();
    ObservableList<Commande> commandeList = FXCollections.observableList(cs.afficherCommande());
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private void handleValiderBtn(ActionEvent event) throws Exception {
        int oldId = commandeList.get(0).getId();
        Commande c = new Commande(oldId,"valide",totalP);
        if(cs.modifierEtat(c)){
               
                grid.getChildren().clear();
                showPanier(mcs.afficherMenuCommande());
                total.setText(String.valueOf(mcs.afficherTotal()) + MenuController.CURRENCY);
                 SceneChanger.changeToSceneWindow(getClass(), event, "validcommand.fxml");
        }
        //System.out.println(commandeList );
    }
    
}
