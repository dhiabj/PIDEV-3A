/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import static com.sun.media.jfxmediaimpl.MediaUtils.error;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entities.Commande;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.CommandeService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author zacha
 */
public class AdminCommandeController implements Initializable {
private Connection conn;
private  PreparedStatement pst;
private ResultSet rs;
private ObservableList<Commande> data;

 

    @FXML
    private TableView<Commande> tableid;
    @FXML
    private TableColumn<?, ?> columnEtat;
    @FXML
    private TableColumn<?, ?> ColumnDate;
    @FXML
    private TableColumn<?, ?> Columniduser;
    private ComboBox<String> etatcombos;
    
    
    
ObservableList<String> etatlist = FXCollections.observableArrayList("en attente","Livré","annuler");
    @FXML
    private ComboBox<String> etatcombo;
    @FXML
    private Button ajout;
    @FXML
    private TextField id_user;
    @FXML
    private DatePicker date;
    
    CommandeService cs=new CommandeService();
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Label etat_error;
    @FXML
    private Label error_date;
    @FXML
    private Label error_user;
    @FXML
    private TableColumn<?, ?> toltalcol;
    @FXML
    private void handleAddMenu(ActionEvent event)
    {
        boolean isDateEmpty = validation.DatePickerValidation.isDatePickerNotEmpty(date, error_date, "Choisir un date valide");
        boolean isUserNumber = validation.TextFieldValidation.isTextFieldTypeNumber(id_user, error_user, "Choisir un nombre valide");
        if(isDateEmpty && isUserNumber){
            String etat1=etatcombo.getValue();
        Date Dates=Date.valueOf(date.getValue());
        int user=parseInt(id_user.getText());
        
        Date currentDate=new Date(2021-07-01); 
        Commande c=new Commande(etat1,Dates,user);
        
         cs.ajouterCommande(c);
         init();
        }
        
    }
    
    @FXML
    private void handleModiferCommande(ActionEvent event) {
       
        
            Commande c = tableid.getSelectionModel().getSelectedItem();
            String etat = etatcombo.getValue();
            Date Dates=Date.valueOf(date.getValue());
            int user=parseInt(id_user.getText());
            int id = c.getId();

            Commande cu = new Commande(id,etat,Dates,user);
            cs.modifiercommande(cu);
            init();
            }
        
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    conn=DataSource.getInstance().getCnx();
    data=FXCollections.observableArrayList();
       loadDataFromDataBase();
       
        setCellTable();
        
        etatcombo.setValue("en attente");
        etatcombo.setItems(etatlist);
      
        
        
    
        // TODO
    }    

    
    
       public void loadDataFromDataBase(){
        data.clear();
        try {
            pst = conn.prepareStatement("select c.id,c.etat,c.date,c.total,u.email as user from commande as c left join user as u on c.user_id=u.id");
            
            rs = pst.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(4));
                data.add(new Commande(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getFloat(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableid.setItems(data);
        System.out.println(data);
    }
        public void setCellTable(){
        columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        toltalcol.setCellValueFactory(new PropertyValueFactory<>("total"));
       Columniduser.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
        
    }
        public void init(){
            
        setCellTable();
        loadDataFromDataBase();
        
    }

    @FXML
    private void handleSupprimerCommande(ActionEvent event) {
        Commande c = tableid.getSelectionModel().getSelectedItem();
        int id = c.getId();
        Commande cd=new Commande(id);
        cs.supCommande(cd);
        init();
    }

   
}
