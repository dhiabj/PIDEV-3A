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

/**
 *
 * @author USER
 */
public class EvenementService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public EvenementService() {
        conn = DataSource.getInstance().getCnx();

    }

    public void ajouterEventPst(evenement e) {
        String req = "insert into evenement (date,nbr_personnes,categorie) values (?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, e.getDate());
            pst.setInt(2, e.getNbr_personnes());
            pst.setString(3, e.getCategorie());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void modifierEvenementPst(evenement e) {
        String req = "update evenement set date = ? , nbr_personnes = ? , categorie= ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, e.getDate());
            pst.setInt(2, e.getNbr_personnes());
            pst.setString(3, e.getCategorie());
            pst.setInt(4, e.getId());
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
    
    public List<evenement> readEvent() {
        String req = "select * from evenement";

        List<evenement> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new evenement(rs.getInt("id"), rs.getDate("date"), rs.getInt("nbr_personnes"), rs.getString("categorie")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
}
