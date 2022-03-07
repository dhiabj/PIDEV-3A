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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.UserRecService;

public class UserRecService  {
    
   
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    AdminService AdminRep = new AdminService();

    private final Connection conn;

    public UserRecService()  {
        conn = DataSource.getInstance().getCnx();
    }


     
    public void ajouterReclamation(Reclamation_user r) {
        String req = "insert into reclamation_user (Titre,Texte,user_id) values (?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getTitre());
            pst.setString(2, r.getTexte());
            pst.setInt(3, r.getUser_id());
            
          
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserRecService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
      public void suppRec(Reclamation_user rec) {
          
        String req = "delete from reclamation_user where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, rec.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserRecService.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    } 
      
      public int getnbRec(int id){
        try {
            String req ="select COUNT(*) nb from reclamation_user where user_id= "+id;
            Statement st = conn.createStatement();
            rs=st.executeQuery(req);
            rs.next();
            System.out.println(rs.getInt("nb"));
            return rs.getInt("nb");
        } catch (SQLException ex) {
            Logger.getLogger(UserRecService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
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
            Logger.getLogger(UserRecService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
        public List <Reclamation_user> readAll()  {
        String req="select * from reclamation_user";
        List<Reclamation_user> list= new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation_user ru=new Reclamation_user();
                ru.setId(rs.getInt("id"));
                ru.setIdr(rs.getInt("idr"));
                ru.setTexte(rs.getString("texte"));
                ru.setTitre(rs.getString("titre"));
                list.add(ru);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reclamation_user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
             public List <Reclamation_user> readAllbyiduser(int id)  {
        String req="select * from reclamation_user  where user_id= '" + id + "'";
        List<Reclamation_user> list= new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation_user ru=new Reclamation_user();
                ru.setId(rs.getInt("id"));
                ru.setIdr(rs.getInt("idr"));
                ru.setTexte(rs.getString("texte"));
                ru.setTitre(rs.getString("titre"));
                list.add(ru);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reclamation_user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
    
        
        
       /* public Reclamation_user displayById(String id) {
        id = '"'+id+'"';
         String req="select * from piste where id ="+id;
         Reclamation_user p=new Reclamation_user();
        try {

            rs=ste.executeQuery(req);
            while(ste.next()){
                
                p.setId(ste.getInt("id"));
                p.setName(ste.getString("Titre"));
                p.setDemo_link(ste.getString("Texte"));
               
           }  
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }*/

    }
   
     
