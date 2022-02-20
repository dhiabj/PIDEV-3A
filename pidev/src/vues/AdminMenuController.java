/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import dialog.AlertDialog;
import entities.Menu;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.MenuService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Dhia
 */
public class AdminMenuController implements Initializable {
    
    private Connection conn;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Menu> data;

    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_prix;
    @FXML
    private ChoiceBox<String> txt_categorie;
    @FXML
    private Button btn_addMenu;
    @FXML
    private TableView<Menu> tableMenu;
    @FXML
    private TableColumn<?, ?> column_titre;
    @FXML
    private TableColumn<?, ?> column_description;
    @FXML
    private TableColumn<?, ?> column_prix;
    @FXML
    private TableColumn<?, ?> column_categorie;
    @FXML
    private Label error_titre;
    @FXML
    private Label error_description;
    @FXML
    private Label error_prix;
    @FXML
    private Label error_categorie;
    @FXML
    private Button btn_updateMenu;
    @FXML
    private Button btn_deleteMenu;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private Button btnCancel;
    
    
    MenuService ms = new MenuService();
    @FXML
    private void handleAddMenu(ActionEvent event){
        
        boolean isTitreString = validation.TextFieldValidation.isTextFieldTypeString(txt_titre, error_titre, "Le titre doit être une chaine de caractères");
        boolean isDescriptionString = validation.TextFieldValidation.isTextFieldTypeString(txt_description, error_description, "La description doit être une chaine de caractères");
        boolean isPrixNumber = validation.TextFieldValidation.isTextFieldTypeNumber(txt_prix, error_prix, "Le prix doit être un nombre");
        boolean isCategorieEmpty = validation.ChoiceBoxValidation.isChoiceBoxNotEmpty(txt_categorie, error_categorie, "La catégorie est requis");
        if(isTitreString && isDescriptionString && isPrixNumber && isCategorieEmpty){
        
            String titre = txt_titre.getText();
            String description = txt_description.getText();
            float prix = Float.valueOf(txt_prix.getText());
            String categorie = txt_categorie.getValue();

            Menu m = new Menu(titre,description,prix,categorie);
            if(ms.ajouterMenu(m)){
                AlertDialog.display("Info", "Menu ajouté avec succès");
                init();
            }
        }
    }
    
    @FXML
    private void handleUpdateMenu(ActionEvent event) {
        boolean isTitreString = validation.TextFieldValidation.isTextFieldTypeString(txt_titre, error_titre, "Le titre doit être une chaine de caractères");
        boolean isDescriptionString = validation.TextFieldValidation.isTextFieldTypeString(txt_description, error_description, "La description doit être une chaine de caractères");
        boolean isPrixNumber = validation.TextFieldValidation.isTextFieldTypeNumber(txt_prix, error_prix, "Le prix doit être un nombre");
        boolean isCategorieEmpty = validation.ChoiceBoxValidation.isChoiceBoxNotEmpty(txt_categorie, error_categorie, "La catégorie est requis");
        if(isTitreString && isDescriptionString && isPrixNumber && isCategorieEmpty){
        
            Menu m = tableMenu.getSelectionModel().getSelectedItem();
            String titre = txt_titre.getText();
            String description = txt_description.getText();
            float prix = Float.valueOf(txt_prix.getText());
            String categorie = txt_categorie.getValue();
            int id = m.getId();

            Menu mu = new Menu(id,titre,description,prix,categorie);
            if(ms.modifierMenu(mu)){
                AlertDialog.display("Info", "Menu modifié avec succès");
                init();
            }
        }
    }
    
    @FXML
    private void handleDeleteMenu(ActionEvent event) {
        Menu m = tableMenu.getSelectionModel().getSelectedItem();
        int id = m.getId();
        
        Menu md = new Menu(id);
        if(ms.suppMenu(md)){
                AlertDialog.display("Info", "Menu supprimé avec succès");
                init();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance().getCnx();
        data = FXCollections.observableArrayList();
        txt_categorie.getItems().addAll("Vegan","Normal");
        init();
    }

    public void setCellTable(){
        column_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        column_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    }
    
    public void loadDataFromDataBase(){
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from menu");
            rs = pst.executeQuery();
            while (rs.next()){
                data.add(new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableMenu.setItems(data);
    }
    
    public void init(){
        setCellTable();
        loadDataFromDataBase();
        btn_addMenu.setDisable(false);
        btn_updateMenu.setDisable(true);
        btn_deleteMenu.setDisable(true);
        setCellValueFromTableToTextField();
    }
    
    private void setCellValueFromTableToTextField(){
        tableMenu.setOnMouseClicked(e -> {
            Menu ml = tableMenu.getItems().get(tableMenu.getSelectionModel().getSelectedIndex());
            txt_titre.setText(ml.getTitre());
            txt_description.setText(ml.getDescription());
            txt_prix.setText(String.valueOf(ml.getPrix()));
            txt_categorie.getSelectionModel().select(ml.getCategorie());
            btn_updateMenu.setDisable(false);
            btn_deleteMenu.setDisable(false);
            btn_addMenu.setDisable(true);
        });
    
}

    @FXML
    private void handleReturnMenu(ActionEvent event) throws Exception {
        utility.SceneChanger.changeToScene(getClass(), event, "AdminInterface.fxml");
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        init();
        txt_titre.clear();
        txt_description.clear();
        txt_prix.clear();
        txt_categorie.valueProperty().set(null);
    }

    

    

}
