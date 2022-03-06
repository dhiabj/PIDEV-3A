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
import entities.reservation;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


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
                list.add(new evenement(rs.getInt("id"), rs.getString("nom"), rs.getDate("date"), rs.getInt("nbr_personnes"), rs.getString("categorie"), rs.getString("description")));
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
        String req = "select * from evenement where categorie= '" + value + "'";

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new evenement(rs.getInt("id"), rs.getString("nom"), rs.getDate("date"), rs.getInt("nbr_personnes"), rs.getString("categorie"), rs.getString("description")));
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

    public int GetIdEvent(String value) {
        String req = "select id from evenement where nom = '" + value + "';";

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

    public String GetNomEventbyId(int idEvent) {
        String req = "select nom from evenement where id ='" + idEvent + "'";
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

    public String QR(String A) {

        try {
            String qrCodeData = "Event " + A + "";
            String filePath = "C:\\Users\\Nayrouz\\OneDrive\\Desktop\\PIDEV-3A\\pidev\\src\\QR\\" + A + ".png";

            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();

            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            return filePath;
        } catch (Exception e) {
            System.err.println(e);
            return "";
        }

    }
}
