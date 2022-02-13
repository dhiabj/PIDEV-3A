/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import entities.Reclamation_user ;
import java.util.ArrayList;
import java.util.List;
public class UserService  {
    
   
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private final Connection conn;

    public UserService()  {
        conn = DataSource.getInstance().getCnx();
    }


     
    public void ajouterReclamation(Reclamation_user r) {
        String req = "insert into reclamation_user (Titre,Texte) values (?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getTitre());
            pst.setString(2, r.getTexte());
            
          
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
      public void suppRec(Reclamation_user rec) {
        String req = "delete from reclamation_user where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, rec.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    } 
       
      public void modifierRec(Reclamation_user u) {
        String req = "update reclamation_user set Titre = ? , Texte = ?   where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getTitre());
            pst.setString(2, u.getTexte());
            pst.setInt(3, u.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
        public List <Reclamation_user> readAll() {
        String req = "select * from reclamation_user";

        List<Reclamation_user> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new Reclamation_user(rs.getInt("id"), rs.getInt("idr") , rs.getString("Titre"), rs.getString("Texte")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    }
   
     
