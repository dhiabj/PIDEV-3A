/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.evenement;
import entities.reservation;
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

        String req = "insert into reservation (id_user,id_event,Nom_reservation) values (?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getId_event());
            pst.setInt(2, r.getId_user());
            pst.setString(3, r.getNom_reservation());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierReservationPst(reservation r) {
        String req = "update reservation set Nom_reservation= ?  where id_reservation = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom_reservation());
            pst.setInt(2, r.getId_reservation());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void suppReservationPst(reservation r) {
        String req = "delete from reservation where id_reservation = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getId_reservation());
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
                list.add(new reservation(rs.getInt("id_reservation"), rs.getInt("id_user"), rs.getInt("id_event"), rs.getString("Nom_reservation")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
