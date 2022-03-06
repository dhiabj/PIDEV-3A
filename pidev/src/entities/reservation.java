/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.UserService;

/**
 *
 * @author USER
 */
public class reservation {

    private int id;
    private int user_id;
    private int event_id;
    private String nom;

    public reservation() {
    }

    public reservation(int id, int user_id, int event_id, String nom) {
        this.id = id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.nom = nom;
    }

    public reservation(int id) {
        this.id = id;
    }

    public reservation(int user_id, int event_id, String nom) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.nom = nom;
    }

    public reservation(String nom, Integer id_client, Integer event_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", user_id=" + user_id + ", event_id=" + event_id + ", nom=" + nom + '}';
    }

}
