/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Dhia
 */
public class MenuService {
    private PreparedStatement pst;
    private final Connection conn;
    
    public MenuService(){
        conn = DataSource.getInstance().getCnx();
    }
    
    public boolean ajouterMenu(Menu m){
        String req="insert into menu(titre,description,prix,categorie,image) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getTitre());
            pst.setString(2, m.getDescription());
            pst.setFloat(3, m.getPrix());
            pst.setString(4, m.getCategorie());
            pst.setString(5, m.getImage());
            pst.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean modifierMenu(Menu m) {
        String req = "update menu set titre = ? , description = ? , prix= ? ,categorie= ?, image= ? where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getTitre());
            pst.setString(2, m.getDescription());
            pst.setFloat(3, m.getPrix());
            pst.setString(4, m.getCategorie());
            pst.setString(5, m.getImage());
            pst.setInt(6, m.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            
        }

    }
    
    public boolean suppMenu(Menu m) {
        String req = "delete from menu where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, m.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public List<Menu> afficherMenu(){
        List<Menu> menus = new ArrayList<>();
        String sql="select * from menu";
        try {
            pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Menu m = new Menu();
                m.setId(rs.getInt("id"));
                m.setTitre(rs.getString("titre"));
                m.setDescription(rs.getString("description"));
                m.setPrix(rs.getFloat("prix"));
                m.setCategorie(rs.getString("categorie"));
                menus.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return menus;
    }
    
}
