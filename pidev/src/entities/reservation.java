/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class reservation {

    private int id_reservation;
    private int id_user;
    private int id_event;
    private String Nom_reservation;

    public reservation() {
    }

    public reservation(int id_reservation, int id_user, int id_event, String Nom_reservation) {
        this.id_reservation = id_reservation;
        this.id_user = id_user;
        this.id_event = id_event;
        this.Nom_reservation = Nom_reservation;
    }

    public reservation(int id_user, int id_event, String Nom_reservation) {
        this.id_user = id_user;
        this.id_event = id_event;
        this.Nom_reservation = Nom_reservation;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_reservation() {
        return Nom_reservation;
    }

    public void setNom_reservation(String Nom_reservation) {
        this.Nom_reservation = Nom_reservation;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", id_user=" + id_user + ", id_event=" + id_event + ", Nom_reservation=" + Nom_reservation + '}';
    }

   

    
    
    
   
}
