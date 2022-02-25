/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Ingredients;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.IngredientsService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Dhia
 */
public class AdminIngredientsController implements Initializable {
    private Connection conn;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Ingredients> data;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btn_addIngredient;
    @FXML
    private TableView<Ingredients> tableIngredient;
    @FXML
    private TableColumn<?, ?> column_nom;
    @FXML
    private TableColumn<?, ?> column_qte;
    @FXML
    private Button btn_updateIngredient;
    @FXML
    private Button btn_deleteIngredient;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txt_nom;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_qte;
    @FXML
    private Spinner<Integer> sp_qte;
    @FXML
    private TextField txt_search;
    SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
    Alert alertError = new Alert(AlertType.ERROR);
    Alert alertInfo = new Alert(AlertType.INFORMATION);
    Alert alertCon = new Alert(AlertType.CONFIRMATION);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance().getCnx();
        data = FXCollections.observableArrayList();
        valueFactory.setValue(1);
        sp_qte.setValueFactory(valueFactory);
        searchIngredient();
        init();
    }
    
    IngredientsService is= new IngredientsService();
    @FXML
    private void handleAddIngredient(ActionEvent event) {
        boolean isNomString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_nom, error_nom, "Le nom doit être une chaine de caractères");
        if(isNomString){
            String nom = txt_nom.getText();
            int quantite = sp_qte.getValue();

            Ingredients i = new Ingredients(nom,quantite);
            if(is.ajouterIngredient(i)){
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Ingredient ajouté avec succès");
                alertInfo.showAndWait();
                init();
            }else{
                alertError.setTitle("Error");
                alertError.setHeaderText("Message");
                alertError.setContentText("L'ingredient "+nom+" existe déjà");
                alertError.showAndWait();
            }
        }
            
    }

    @FXML
    private void handleUpdateIngredient(ActionEvent event) {
        boolean isNomString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_nom, error_nom, "Le nom doit être une chaine de caractères");
        if(isNomString){
            Ingredients il = tableIngredient.getItems().
                get(tableIngredient.getSelectionModel().getSelectedIndex());
            String nom = txt_nom.getText();
            int quantite = sp_qte.getValue();
            int id = il.getId();
        
            Ingredients iu = new Ingredients(id,nom,quantite);
            if(is.modifierIngredient(iu)){
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Ingredient modifié avec succès");
                alertInfo.showAndWait();
                init();
            }else{
                alertError.setTitle("Error");
                alertError.setHeaderText("Message");
                alertError.setContentText("L'ingredient "+nom+" existe déjà");
                alertError.showAndWait();
            }
        }
    }

    @FXML
    private void handleDeleteIngredient(ActionEvent event) {
        Ingredients il = tableIngredient.getItems().
                get(tableIngredient.getSelectionModel().getSelectedIndex());
        int id = il.getId();
        Ingredients iu = new Ingredients(id);
        alertCon.setTitle("Supprimer Ingredient");
        alertCon.setHeaderText("Êtes-vous sûr?");
        alertCon.setContentText("Êtes-vous sûr de vouloir supprimer cet ingredient?");
        ButtonType supprimer = new ButtonType("Supprimer");
	ButtonType annuler = new ButtonType("Annuler");
        alertCon.getButtonTypes().clear();
        alertCon.getButtonTypes().addAll(supprimer, annuler);
        Optional<ButtonType> option = alertCon.showAndWait();
        if (option.get() == supprimer) {
         if(is.suppIngredient(iu)){
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Ingredient supprimé avec succès");
                alertInfo.showAndWait();
                init();
            }
      } 
    }

    @FXML
    private void handleReturnMenu(ActionEvent event) throws Exception {
        SceneChanger.changeToScene(getClass(), event, "AdminInterface.fxml");
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        init();
    }
    
    public void setCellTable(){
        column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        column_qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    }
    
    public void loadDataFromDataBase(){
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from ingredients");
            rs = pst.executeQuery();
            while (rs.next()){
                data.add(new Ingredients(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableIngredient.setItems(data);
    }
    
    private void setCellValueFromTableToTextField(){
        tableIngredient.setOnMouseClicked(e -> {
            Ingredients il = tableIngredient.getItems().
                    get(tableIngredient.getSelectionModel().getSelectedIndex());
            txt_nom.setText(il.getNom());
            valueFactory.setValue(il.getQuantite());
            sp_qte.setValueFactory(valueFactory);
            btn_addIngredient.setDisable(true);
            btn_updateIngredient.setDisable(false);
            btn_deleteIngredient.setDisable(false);
        });
    }
    
    private void searchIngredient(){
        txt_search.setOnKeyReleased(e->{
            if(txt_search.getText().equals("")){
                loadDataFromDataBase();
            }
            else{
                data.clear();
                String sql = "select * from ingredients where nom LIKE '%"+txt_search.getText()+"%'"
                            + "UNION select * from ingredients where quantite Like '%"+txt_search.getText()+"%'";
                try {
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()){
                    data.add(new Ingredients(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
                tableIngredient.setItems(data);
                }catch (SQLException ex) {
                    Logger.getLogger(AdminIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    
    public void init(){
        setCellTable();
        loadDataFromDataBase();
        btn_addIngredient.setDisable(false);
        btn_updateIngredient.setDisable(true);
        btn_deleteIngredient.setDisable(true);
        setCellValueFromTableToTextField();
        txt_nom.clear();
        valueFactory.setValue(1);
        
    }
    
}
