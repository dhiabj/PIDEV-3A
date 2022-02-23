/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.DataSource;
import entities.Ingredients;
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
    private PreparedStatement pst;
    private final Connection conn;
    
    public IngredientsService(){
        conn = DataSource.getInstance().getCnx();
    }
    
    public boolean ajouterIngredient(Ingredients i){
        String req="insert into ingredients(nom,quantite) values (?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, i.getNom());
            pst.setInt(2, i.getQuantite());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean modifierIngredient(Ingredients i) {
        String req = "update ingredients set nom = ? , quantite = ? where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, i.getNom());
            pst.setInt(2, i.getQuantite());
            pst.setInt(3, i.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(IngredientsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public boolean suppIngredient(Ingredients i) {
        String req = "delete from ingredients where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, i.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(IngredientsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
                ingredient.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ingredient;
    }
    
}
