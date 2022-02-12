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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String req = "insert into user (nom,prenom,adresse,num_tel,email,password) values (?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getAdresse());
            pst.setInt(4, u.getNum_tel());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getPassword());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierUserPst(user u) {
        String req = "update user set nom = ? , prenom = ? , adresse= ? ,num_tel= ? ,email= ? ,password= ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getAdresse());
            pst.setInt(4, u.getNum_tel());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getPassword());
            pst.setInt(7, u.getId());
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
    public List<user> readAll() {
        String req = "select * from user";

        List<user> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getInt("num_tel"), rs.getString("email"), rs.getString("password")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
