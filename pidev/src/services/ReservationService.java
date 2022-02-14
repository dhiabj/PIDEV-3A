/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.reservation;
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
 * @author USER
 */
public class ReservationService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ReservationService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterReservation(reservation r) {

        String req = "insert into reservation (user_id,event_id,nom) values (?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getEvent_id());
            pst.setInt(2, r.getUser_id());
            pst.setString(3, r.getNom());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierReservationPst(reservation r) {
        String req = "update reservation set nom= ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom());
            pst.setInt(2, r.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void suppReservationPst(reservation r) {
        String req = "delete from reservation where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<reservation> readReservation() {
        String req = "select * from reservation";

        List<reservation> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new reservation(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("event_id"), rs.getString("nom")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
