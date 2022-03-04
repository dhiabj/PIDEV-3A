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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
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
    private Text total;
    private MyListener myListener;
    private int mcId;
    private float totalP;
    
    MenuCommandeService mcs = new MenuCommandeService();
    @FXML
    private TextField txt_code;
    @FXML
    private Button btnCode;
    @FXML
    private Text totalNew;
    private boolean b = false;
    
    
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
                    b=false;
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
        Image img = new Image("/ressources/delivery-truck-copy.png");
        int oldId = commandeList.get(0).getId();
        Commande c = new Commande(oldId,"valide",totalP);
        if(cs.modifierEtat(c)){
               
                grid.getChildren().clear();
                showPanier(mcs.afficherMenuCommande());
                totalNew.setText(String.valueOf(mcs.afficherTotal()) + MenuController.CURRENCY);
                //SceneChanger.changeToSceneWindow(getClass(), event, "validcommand.fxml");
                Notifications notificationBuilder = Notifications.create()
                        .title("Commande validé")
                        .text("Commande validé avec succes !")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.show();
        }
        //System.out.println(commandeList );
    }
    
    String code = "0000";
    @FXML
    private void handleCodeButton(ActionEvent event) throws Exception {
        if(txt_code.getText().equals(code) && b==false){
            b = true;
            totalP=totalP*0.75f;
            total.setStyle("-fx-strikethrough: true");
            totalNew.setText(String.valueOf(totalP) + MenuController.CURRENCY);
        }
        else{
                SceneChanger.changeToSceneWindow(getClass(), event, "alrtcode.fxml");}
    }
    
}
