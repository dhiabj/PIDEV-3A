/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Nayrouz
 */
public class UserService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public UserService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterUersonne(user u) {
        String req = "insert into user (nom,prenom) values ('" + u.getNom() + "','" + u.getPrenom() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajouterUserPst(user u) {
        String req = "insert into user (nom,prenom,adresse,num_tel,email,password,date,role) values (?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getAdresse());
            pst.setInt(4, u.getNum_tel());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getPassword());
            pst.setDate(7, u.getDate());
            pst.setString(8, u.getRole());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierUserPst(user u) {
        String req = "update user set nom = ? , prenom = ? , adresse= ? ,num_tel= ? ,email= ? ,password= ?, date = ?, role = ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getAdresse());
            pst.setInt(4, u.getNum_tel());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getPassword());
            pst.setDate(7, u.getDate());
            pst.setString(8, u.getRole());
            pst.setInt(9, u.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void suppUserPst(user u) {
        String req = "delete from user where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<user> readAll() {
        String req = "select * from user";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        public ObservableList<user> recherche(String searchby, String value) {
        String req = "select * from user where "+searchby+" like '%"+value+"%'";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
            public ObservableList<user> tri(String value) {
        String req = "select * from user order by "+value;

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
            
                public ObservableList<user> filterRole(String value) {
        String req = "select * from user where role = '"+value+"'";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
                public ObservableList<String> GetNames() {
        String req = "select nom,prenom from user";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("nom") + " " + rs.getString("prenom"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}


// public boolean MailExiste(String mail)
//    {
//         try
//        { ResultSet res= st.executeQuery("Select * from Fos_user where email='"+mail+"';");
//             return res.next();
//        }
//        catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return false;
//        
//        
//       
//    }
// public boolean UsernameExiste(String username)
//    {
//         try
//        { ResultSet res= st.executeQuery("Select * from Fos_user where username like '"+username+"';");
//             return res.next();
//        }
//        catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return false;
//        
//        
//       
//    }
//     public boolean TelephoneExiste(String numero)
//    {
//         try
//        { ResultSet res= st.executeQuery("Select * from Utilisateur where telephone='"+numero+"';");
//             return res.next();
//        }
//        catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return false;
//        
//        
//       
//    }
//     
