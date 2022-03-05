/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.MD5Utils;
import entities.livraison;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public boolean ajouterUserPst(user u) {
        String req = "insert into user (nom,prenom,adresse,num_tel,email,password,date,role) values (?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getAdresse());
            pst.setInt(4, u.getNum_tel());
            pst.setString(5, u.getEmail());
            pst.setString(6, MD5Utils.cryptage(u.getPassword()));
            pst.setDate(7, u.getDate());
            pst.setString(8, u.getRole());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean modifierUserPst(user u) {
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
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean suppUserPst(user u) {
        String req = "delete from user where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
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
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public user getUserbyEmailPass(String email, String pass) {

        String req = "select * from user where email = '" + email + "' and password = '" + MD5Utils.cryptage(pass) + "'";

        user u = new user();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            if (rs.first()) {
                u = new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role"));
            }
            System.out.println(u);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return u;
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
    
    public ObservableList<String> GetNamesLivreur() {
        String req = "SELECT concat(nom,' ',prenom) as full_name from user where role='Livreur'";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("full_name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public ObservableList<String> GetNamesClient() {
        String req = "SELECT concat(nom,' ',prenom) as full_name from user where role='Client'";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("full_name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<user> recherche(String searchby, String value) {
        String req = "select * from user where " + searchby + " like '%" + value + "%'";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<user> tri(String value) {
        String req = "select * from user order by " + value;

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<user> filterRole(String value) {
        String req = "select * from user where role = '" + value + "'";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getDate("date"), rs.getInt("num_tel"), rs.getString("adresse"), rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean MailExiste(String mail) {
        try {
            ResultSet res = ste.executeQuery("Select * from user where email='" + mail + "';");
            return res.next();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;

    }

    public int GetIdUser(String value) {
        String req = "select id from user where CONCAT(nom,' ',prenom) = '" + value + "';";

        int id = 0;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                id = rs.getInt("id");

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String GetNomutilisateurbyId(int id) {
        String req = "select nom from user where id ='" + id + "'";
        String nom = null;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                nom = rs.getString("nom");

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }

    public boolean ResetPassword(String pass, int id) throws SQLException {
        String sql = "UPDATE user SET password=? WHERE id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, MD5Utils.cryptage(pass));
            pst.setInt(2, id);

            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean update(user t){
        String sql = "UPDATE user SET nom = ? , prenom = ? , adresse = ? WHERE id = ?";
        try {
            pst = conn.prepareStatement(sql);
            System.out.println(t);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getAdresse());

            pst.setInt(4, t.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
public int rowUSER(){
        ObservableList<user> liste = FXCollections.observableArrayList();
        String req = "SELECT * FROM user";
        int i=0;
        
        try {
            conn = DataSource.getInstance().getCnx();
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            user user1;
            while (rs.next()){
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (ste != null) {
        try {
            ste.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return i;
        
}
}
