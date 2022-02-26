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
import entities.evenement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class EvenementService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;
    public Date date;

    public EvenementService() {
        conn = DataSource.getInstance().getCnx();

    }

    public void ajouterEventPst(evenement e) {
        String req = "insert into evenement (nom,date,nbr_personnes,categorie,description) values (?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, e.getNom());
            pst.setDate(2, e.getDate());
            pst.setInt(3, e.getNbr_personnes());
            pst.setString(4, e.getCategorie());
            pst.setString(5, e.getDescription());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierEvenementPst(evenement e) {
        String req = "update evenement set nom=? , date = ? , nbr_personnes = ? , categorie= ? , description= ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, e.getNom());
            pst.setDate(2, e.getDate());
            pst.setInt(3, e.getNbr_personnes());
            pst.setString(4, e.getCategorie());
            pst.setString(5, e.getDescription());
            pst.setInt(6, e.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void suppEvenementPst(evenement e) {
        String req = "delete from evenement where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, e.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<evenement> readEvent() {
        String req = "select * from evenement";

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new evenement(rs.getInt("id"),rs.getString("nom"), rs.getDate("date"), rs.getInt("nbr_personnes"), rs.getString("categorie"), rs.getString("description")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void modifierUserPst(evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    public ObservableList<evenement> filterVegan(String value) {
        String req = "select * from evenement where categorie= '"+value+"'";

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new evenement(rs.getInt("id"),rs.getString("nom"), rs.getDate("date"), rs.getInt("nbr_personnes"), rs.getString("categorie"), rs.getString("description")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        public ObservableList<String> GetNamesEvent() {
        String req = "select nom from evenement";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("nom"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    }



