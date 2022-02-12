/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import utils.DataSource;
import entities.Ingredients;
import entities.Menu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dhia
 */
public class IngredientsService {
    private Statement st;
    private PreparedStatement pst;
    private Connection conn;
    
    public IngredientsService(){
        conn = DataSource.getInstance().getCnx();
    }
    
    public void ajouterIngredient(Ingredients i){
        String req="insert into ingredients(nom,quantite,menu_id) values (?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, i.getNom());
            pst.setInt(2, i.getQuantite());
            pst.setInt(3, i.getMenu_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Ingredients> afficherIngredient(){
        List<Ingredients> ingredient = new ArrayList<>();
        String sql="select * from ingredients";
        try {
            pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Ingredients i = new Ingredients();
                i.setId(rs.getInt("id"));
                i.setNom(rs.getString("nom"));
                i.setQuantite(rs.getInt("quantite"));
                i.setMenu_id(rs.getInt("menu_id"));
                ingredient.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ingredient;
    }
    
}
