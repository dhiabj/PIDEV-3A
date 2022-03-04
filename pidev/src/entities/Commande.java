/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;



/**
 *
 * @author zacha
 */
public class Commande {
    private int id;
    private String etat;
    private Date date;
    private float total;
    private int user_id; 
    private String nom;

    public Commande() {
    }

    public Commande(String etat) {
        this.etat = etat;
    }
    
    public Commande( String etat, Date date,float total, int user_id) {
        
        this.etat = etat;
        this.date = date;
        this.total = total;
        this.user_id = user_id;
    }

    public Commande(String etat, Date date, int user_id) {
        this.etat = etat;
        this.date = date;
        this.user_id = user_id;
    }

    public Commande(int id, String etat, float total) {
        this.id = id;
        this.etat = etat;
        this.total = total;
    }
    public Commande(int id, String etat, Date date, int user_id) {
        this.id = id;
        this.etat = etat;
        this.date = date;
       
        this.user_id = user_id;
    }
    public Commande(int id, String etat, Date date,float total, int user_id) {
        this.id = id;
        this.etat = etat;
        this.date = date;
        this.total = total;
        this.user_id = user_id;
    }
    
    public Commande(int id, String etat, Date date,float total, String nom) {
        this.id = id;
        this.etat = etat;
        this.date = date;
        this.total = total;
        this.nom = nom;
    }

    public Commande(int id, String etat) {
        this.id = id;
        this.etat = etat;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Commande(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
   
    

    public int getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate() {
        return date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", etat=" + etat + ", date=" + date +", total="+ total + ", nom=" + nom + '}';
    }
    
    
    
}
