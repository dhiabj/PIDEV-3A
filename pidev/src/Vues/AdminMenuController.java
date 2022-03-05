/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Menu;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private TextField txt_ingredients;
    @FXML
    private ComboBox<String> txt_categorie;
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
    private TableColumn<?, ?> column_ingredients;
    @FXML
    private Label error_titre;
    @FXML
    private Label error_description;
    @FXML
    private Label error_prix;
    @FXML
    private Label error_ingredients;
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
    @FXML
    private Button btnBrowser;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageView;
    private Image image;
    @FXML
    private Label error_image;
    @FXML
    private TextField txt_search;
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    Alert alertCon = new Alert(Alert.AlertType.CONFIRMATION);
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance().getCnx();
        data = FXCollections.observableArrayList();
        txt_categorie.getItems().addAll("Vegan","Normal");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")  
        );
        searchMenu();
        init();
    }
    
    MenuService ms = new MenuService();
    @FXML
    private void handleAddMenu(ActionEvent event){
        
        boolean isTitreString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_titre, error_titre, "Le titre doit être une chaine de caractères");
        boolean isDescriptionString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_description, error_description, "La description doit être une chaine de caractères");
        boolean isPrixNumber = validation.TextFieldValidation.isTextFieldTypeNumber
        (txt_prix, error_prix, "Le prix doit être un nombre");
        boolean isIngredientsString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_ingredients, error_ingredients, "Les ingredients doit être une chaine de caractères");
        boolean isCategorieEmpty = validation.ComboBoxValidation.isComboBoxNotEmpty
        (txt_categorie, error_categorie, "La catégorie est requis");
        boolean isImageViewEmpty = validation.ImageViewValidation.isImageViewEmpty
        (imageView, error_image, "Vous devez sélectionner une image");
        if(isTitreString && isDescriptionString && isPrixNumber && isIngredientsString && isCategorieEmpty && isImageViewEmpty){
        
            String titre = txt_titre.getText();
            String description = txt_description.getText();
            float prix = Float.valueOf(txt_prix.getText());
            String ingredients = txt_ingredients.getText();
            String categorie = txt_categorie.getValue();
            String imageIn = file.getAbsolutePath();

            Menu m = new Menu(titre,description,prix,ingredients,categorie,imageIn);
            if(ms.ajouterMenu(m)){
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Menu ajouté avec succès");
                alertInfo.showAndWait();
                init();
            }else{
                alertError.setTitle("Error");
                alertError.setHeaderText("Message");
                alertError.setContentText("Le menu "+titre+" existe déjà");
                alertError.showAndWait();
            }
        }
    }
    
    @FXML
    private void handleUpdateMenu(ActionEvent event) {
        boolean isTitreString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_titre, error_titre, "Le titre doit être une chaine de caractères");
        boolean isDescriptionString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_description, error_description, "La description doit être une chaine de caractères");
        boolean isPrixNumber = validation.TextFieldValidation.isTextFieldTypeNumber
        (txt_prix, error_prix, "Le prix doit être un nombre");
        boolean isIngredientsString = validation.TextFieldValidation.isTextFieldTypeString
        (txt_ingredients, error_ingredients, "Les ingredients doit être une chaine de caractères");
        boolean isCategorieEmpty = validation.ComboBoxValidation.isComboBoxNotEmpty
        (txt_categorie, error_categorie, "La catégorie est requis");
        if(isTitreString && isDescriptionString && isPrixNumber && isIngredientsString && isCategorieEmpty){
        
            Menu m = tableMenu.getItems().get(tableMenu.getSelectionModel().getSelectedIndex());
            String titre = txt_titre.getText();
            String description = txt_description.getText();
            float prix = Float.valueOf(txt_prix.getText());
            String ingredients = txt_ingredients.getText();
            String categorie = txt_categorie.getValue();
            String imageIn = m.getImage();
            if(file!=null){
                imageIn = file.getAbsolutePath();
            }
            int id = m.getId();
            Menu mu = new Menu(id,titre,description,prix,ingredients,categorie,imageIn);
            if(ms.modifierMenu(mu)){
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Menu modifié avec succès");
                alertInfo.showAndWait();
                init();
            }else{
                alertError.setTitle("Error");
                alertError.setHeaderText("Message");
                alertError.setContentText("Le menu "+titre+" existe déjà");
                alertError.showAndWait();
            }
        }
    }
    
    @FXML
    private void handleDeleteMenu(ActionEvent event) {
        Menu m = tableMenu.getItems().get(tableMenu.getSelectionModel().getSelectedIndex());
        int id = m.getId();
        
        Menu md = new Menu(id);
        alertCon.setTitle("Supprimer Menu");
        alertCon.setHeaderText("Êtes-vous sûr?");
        alertCon.setContentText("Êtes-vous sûr de vouloir supprimer ce menu?");
        ButtonType supprimer = new ButtonType("Supprimer");
	ButtonType annuler = new ButtonType("Annuler");
        alertCon.getButtonTypes().clear();
        alertCon.getButtonTypes().addAll(supprimer, annuler);
        Optional<ButtonType> option = alertCon.showAndWait();
        if (option.get() == supprimer) {
        if(ms.suppMenu(md)){
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Menu supprimé avec succès");
                alertInfo.showAndWait();
                init();
            }
        }
    }
    
    public void setCellTable(){
        column_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        column_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        column_ingredients.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
    }
    
    public void loadDataFromDataBase(){
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from menu");
            rs = pst.executeQuery();
            while (rs.next()){
                data.add(new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7)));
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
        txt_titre.clear();
        txt_description.clear();
        txt_prix.clear();
        txt_ingredients.clear();
        txt_categorie.valueProperty().set(null);
        imageView.setImage(null);
    }
    
    private void setCellValueFromTableToTextField(){
        tableMenu.setOnMouseClicked(e -> {
            Menu ml = tableMenu.getItems().get(tableMenu.getSelectionModel().getSelectedIndex());
            txt_titre.setText(ml.getTitre());
            txt_description.setText(ml.getDescription());
            txt_prix.setText(String.valueOf(ml.getPrix()));
            txt_ingredients.setText(ml.getIngredients());
            txt_categorie.getSelectionModel().select(ml.getCategorie());
            btn_updateMenu.setDisable(false);
            btn_deleteMenu.setDisable(false);
            btn_addMenu.setDisable(true);
            showMenuImage(ml.getId());
        });
    }
    
    private void searchMenu(){
        txt_search.setOnKeyReleased(e->{
            if(txt_search.getText().equals("")){
                loadDataFromDataBase();
            }
            else{
                data.clear();
                String sql = "select * from menu where titre LIKE '%"+txt_search.getText()+"%'"
                        + "UNION select * from menu where description Like '%"+txt_search.getText()+"%'"
                        + "UNION select * from menu where prix Like '%"+txt_search.getText()+"%'"
                        + "UNION select * from menu where categorie Like '%"+txt_search.getText()+"%'"
                        + "UNION select * from menu where ingredients Like '%"+txt_search.getText()+"%'";
                try {
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()){
                    data.add(new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
                tableMenu.setItems(data);
                }catch (SQLException ex) {
                    Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    
    private void showMenuImage(int id){
        try {
            pst = conn.prepareStatement("select image from menu where id = ?");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()){
                String imageIn = rs.getString("image");
                File imageOut = new File(imageIn);
                
                
                image = new Image(imageOut.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                imageView.setImage(image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void handleReturnMenu(ActionEvent event) throws Exception {
        SceneChanger.changeToScene(getClass(), event, "MainFXML.fxml");
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        init();
    }
    
    @FXML
    private void handleBtnBrowser(ActionEvent event) {
        stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if(file != null){
            //System.out.println(getFilePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(),
                    imageView.getFitWidth(), imageView.getFitHeight(), true, true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
        }
    }
    

}
