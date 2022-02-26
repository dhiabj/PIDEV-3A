/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Menu;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.MenuService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Dhia
 */
public class UserMenuController implements Initializable {
    
    private Connection conn;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    @FXML
    private TextField txt_search;
    @FXML
    private Button btnSearch;
    @FXML
    private VBox chosenMenuCard;
    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_prix;
    @FXML
    private ImageView menuImage;
    @FXML
    private Button addCart;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnVegan;
    @FXML
    private Button btnNormal;
    @FXML
    private Label lb_description;
    @FXML
    private Text txt_description;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Image image;
    private MyListener myListener;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chosenMenuCard.setVisible(false);
        //System.out.println(menusVegan);
        conn = DataSource.getInstance().getCnx();
        }
    
    private void setChosenMenu(Menu menu){
        lb_titre.setText(menu.getTitre());
        lb_prix.setText(String.valueOf(menu.getPrix()) + MenuController.CURRENCY);
        txt_description.setText(menu.getDescription());
        File file = new File(menu.getImage());
        image = new Image(file.getAbsoluteFile().toURI().toString());
        menuImage.setImage(image);
    }
    
    private boolean clickedVegan = false;
    private boolean clickedNormal = false;
    List<Menu> foundMenus = new ArrayList<>();
    @FXML
    private void handleSearchButton(ActionEvent event) {
        if(clickedVegan){
            foundMenus.clear();
                String sql = "select * from menu where titre LIKE '%"+txt_search.getText()+"%' AND categorie= 'Vegan'";
                try {
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()){
                    foundMenus.add(new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6)));
                }
                    grid.getChildren().clear();
                    showMenu(foundMenus);
                }catch (SQLException ex) {
                    Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            if(txt_search.getText().length() == 0){
                grid.getChildren().clear();
                showMenu(menusVegan);
            }
        }
        if(clickedNormal){
            foundMenus.clear();
                String sql = "select * from menu where titre LIKE '%"+txt_search.getText()+"%' AND categorie= 'Normal'";
                try {
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()){
                    foundMenus.add(new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6)));
                }
                    grid.getChildren().clear();
                    showMenu(foundMenus);
                }catch (SQLException ex) {
                    Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            if(txt_search.getText().length() == 0){
                grid.getChildren().clear();
                showMenu(menusNormal);
            }
        }
        
    }

    @FXML
    private void handleAddCartButton(ActionEvent event) {
    }

    @FXML
    private void handleCartButton(ActionEvent event) {
    }
    
    MenuService ms = new MenuService();
   
    
    List<Menu> menusVegan = ms.afficherMenuVegan();
    List<Menu> menusNormal = ms.afficherMenuNormal();
    private void showMenu(List<Menu> menus){
        //menus.clear();
        //System.out.println(menus.isEmpty());
        
        if(menus.size()>0){
            setChosenMenu(menus.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Menu menu) {
                    setChosenMenu(menu);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
        for(int i=0; i<menus.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/vues/Menu.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            MenuController menuController = fxmlLoader.getController();
            menuController.setData(menus.get(i), myListener);
            
            if(column == 3){
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
    
    @FXML
    private void handleVeganButton(ActionEvent event) {
        chosenMenuCard.setVisible(true);
        chosenMenuCard.setStyle("-fx-background-color: 	#228B22; -fx-background-radius: 30;");
        grid.getChildren().clear();
        showMenu(menusVegan);
        clickedVegan=true;
        clickedNormal=false;
        //System.out.println(menusVegan);
    }

    @FXML
    private void handleNormalButton(ActionEvent event) {
        chosenMenuCard.setVisible(true);
        chosenMenuCard.setStyle("-fx-background-color: 	#F16C31; -fx-background-radius: 30;");
        grid.getChildren().clear();
        showMenu(menusNormal);
        clickedNormal=true;
        clickedVegan=false;
        //System.out.println(menusNormal);
    }
    
}
