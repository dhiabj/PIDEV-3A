/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.livraison;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import utils.DataSource;

/**
 *
 * @author Nayrouz
 */
public class LivraisonService {
    
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public LivraisonService() {
        conn = DataSource.getInstance().getCnx();

    }

    public Boolean ajouterLivraisonPst(livraison l) {
        String req = "insert into livraison (user_id,livreur_id,commande_id,nom,etat) values (?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, l.getUser_id());
            pst.setInt(2, l.getLivreur_id());
            pst.setInt(3, l.getCommande_id());
            pst.setString(4, l.getNom());
            pst.setString(5, l.getEtat());
            pst.executeUpdate();
             return true;

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public void modifierLivraisonPst(livraison l) {
        String req = "update livraison set user_id = ? , livreur_id = ? , commande_id= ? , nom= ? , etat = ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, l.getUser_id());
            pst.setInt(2, l.getLivreur_id());
            pst.setInt(3, l.getCommande_id());
            pst.setString(4, l.getNom());
            pst.setString(5, l.getEtat());
            pst.setInt(6, l.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void suppLivraisonPst(livraison l) {
        String req = "delete from livraison where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, l.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public ObservableList<livraison> filterEtat(String value) {
        String req = "select * from livraison where etat = '" + value + "'";

        ObservableList<livraison> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new livraison(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("livreur_id"), rs.getInt("commande_id") ,rs.getString("nom"), rs.getString("etat")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<livraison> readLivraison() {
        String req = "select * from livraison";

        ObservableList<livraison> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new livraison(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("livreur_id"), rs.getInt("commande_id") ,rs.getString("nom"), rs.getString("etat")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
}
