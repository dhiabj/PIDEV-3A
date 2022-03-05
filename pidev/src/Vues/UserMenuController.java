/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Commande;
import entities.Menu;
import entities.MenuCommande;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.CommandeService;
import services.MenuCommandeService;
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
    private Text txt_ingredients;
    @FXML
    private Label lb_ingredients;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Image image;
    private MyListener myListener;
    ObservableList<Commande> commandeList; 
    MenuService ms = new MenuService();
    ObservableList<Menu> allMenus = FXCollections.observableList(ms.afficherAllMenus());
    @FXML
    private Button btnAll;
    @FXML
    private ImageView bqckbtn;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(commandeList.isEmpty());
        //System.out.println(menusVegan);
        showMenu(allMenus);
        conn = DataSource.getInstance().getCnx();
        commandeList = FXCollections.observableList(cs.afficherCommande());
        //MenuCommandeService mcs=new MenuCommandeService();
        //MenuCommande c=new MenuCommande(52);
        //System.out.println(mcs.sum(c));
    
        
       
        }
    
    private void setChosenMenu(Menu menu){
        lb_titre.setText(menu.getTitre());
        lb_prix.setText(String.valueOf(menu.getPrix()) + MenuController.CURRENCY);
        txt_description.setText(menu.getDescription());
        txt_ingredients.setText(menu.getIngredients());
        File file = new File(menu.getImage());
        image = new Image(file.getAbsoluteFile().toURI().toString());
        menuImage.setImage(image);
    }
    
    ObservableList<Menu> menusVegan = FXCollections.observableList(ms.afficherMenuVegan());
    ObservableList<Menu> menusNormal = FXCollections.observableList(ms.afficherMenuNormal());
    
    private int menuId;
    private void showMenu(ObservableList<Menu> menus){
        //menus.clear();
        //System.out.println(menus.isEmpty());
        if(menus.size()>0){
            setChosenMenu(menus.get(0));
            menuId = menus.get(0).getId();
            myListener = new MyListener() {
                @Override
                public void onClickListener(Menu menu) {
                    //System.out.println(menuId);
                    menuId = menu.getId();
                    setChosenMenu(menu);
                    if("Vegan".equals(menu.getCategorie())){
                        chosenMenuCard.setStyle("-fx-background-color: 	#228B22; -fx-background-radius: 30;");
                    }else{
                        chosenMenuCard.setStyle("-fx-background-color: 	#FF4433; -fx-background-radius: 30;");
                    }
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
        //System.out.println(menuId);
        chosenMenuCard.setStyle("-fx-background-color: 	#228B22; -fx-background-radius: 30;");
        grid.getChildren().clear();
        showMenu(menusVegan);
        //System.out.println(menusVegan);
    }

    @FXML
    private void handleNormalButton(ActionEvent event) {
        //System.out.println(menuId);
        chosenMenuCard.setStyle("-fx-background-color: 	#FF4433; -fx-background-radius: 30;");
        grid.getChildren().clear();
        showMenu(menusNormal);
        //System.out.println(menusNormal);
    }
    
    @FXML
    private void handleAllButton(ActionEvent event) {
        grid.getChildren().clear();
        showMenu(allMenus);
        chosenMenuCard.setStyle("-fx-background-color: 	#F16C31; -fx-background-radius: 30;");
    }
    
    List<Menu> listMenusFound = new ArrayList<>();
    ObservableList<Menu> menusFound = FXCollections.observableList(listMenusFound);
    @FXML
    private void handleSearchButton(ActionEvent event) {
         if(txt_search.getText().length() != 0){
                menusFound.clear();
                String sql = "select * from menu where titre LIKE '%"+txt_search.getText()+"%'";
                try {
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()){
                    menusFound.add(new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
                    grid.getChildren().clear();
                    showMenu(menusFound);
                }catch (SQLException ex) {
                    Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }
    
    CommandeService cs = new CommandeService();
    MenuCommandeService mcs = new MenuCommandeService();
    
    java.util.Date date=new java.util.Date();
    java.sql.Date sqlDate=new java.sql.Date(date.getTime());
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private void handleAddCartButton(ActionEvent event) throws Exception  {
        commandeList = FXCollections.observableList(cs.afficherCommande());
        if(commandeList.size()==0){
            Commande c = new Commande("non valide",sqlDate,1);
            cs.ajouterCommande(c);
            MenuCommande mcNew = new MenuCommande(CommandeService.id,menuId);
            mcs.ajouterMenuCommande(mcNew);
            
        }else{
            int oldId = commandeList.get(0).getId();
            MenuCommande mcOld = new MenuCommande(oldId,menuId);
            mcs.ajouterMenuCommande(mcOld);
            //System.out.println(commandeList);
            
        }
                 SceneChanger.changeToSceneWindow(getClass(), event, "validmenu.fxml");
        //System.out.println(commandeList.isEmpty());
            
  
        
    }

    @FXML
    private void handleCartButton(ActionEvent event) throws Exception {
        SceneChanger.changeToSceneWindow(getClass(), event, "PanierInterface.fxml");
    }

    @FXML
    private void backbtnmenu(MouseEvent event) throws Exception {
        GotoFXML("MainClientFXML", "ForU",event);
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
    
    
    
    
}
