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
;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public boolean ajouterReservation(reservation r) {

        String req = "insert into reservation (user_id,event_id,nom) values (?,?,?)";
        if (checkfull(r.getEvent_id()))
                {
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getUser_id());
            pst.setInt(2, r.getEvent_id());
            pst.setString(3, r.getNom());
            pst.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
            

    }   else return false;
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

    public ObservableList<reservation> readReservation() {
        String req = "select * from reservation";

        ObservableList<reservation> list = FXCollections.observableArrayList();
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

    public reservation recherche(String value) {
        String req = "select * from reservation where nom = '" + value + "'";

        reservation reser = new reservation();
        try {
            ste = conn.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return reser;
    }

    public ObservableList<String> GetNamesReservation() {
        String req = "select nom from reservation";

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

    public reservation CheckedReservedBefore(int id) {
        String req = "select * from reservation where user_id= '" + id + "'";

        reservation reser = new reservation();
        try {
            ste = conn.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reser;
    }
    //select count(R.id) as nbr_reseration , E.nbr_personnes as nbr_max from reservation R inner join evenement E on E.id=R.event_id;

        public int CountNbrReser(int id) {
        int nbr = 0;
        String req = "select count(id) as nbr_reservation  from reservation where event_id =" + id + "";
            try {
                ste = conn.createStatement();
                rs = ste.executeQuery(req);
                while (rs.next()) {//parcourir le resultset
                    nbr = rs.getInt("nbr_reservation");
                }
            }catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

                return nbr;
            }
        
         public int Nb_personnes(int id) {
        int nbr = 0;
        String req = "select nbr_personnes from evenement where id=" + id + "";
            try {
                ste = conn.createStatement();
                rs = ste.executeQuery(req);
                while (rs.next()) {//parcourir le resultset
                    nbr = rs.getInt("nbr_personnes");
                }
            }catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

                return nbr;
            }
         public boolean checkfull(int id){
             if ((CountNbrReser(id))<(Nb_personnes(id)))
             {
                 return true;
             }
          else   
        return false;
             }
         

        }
