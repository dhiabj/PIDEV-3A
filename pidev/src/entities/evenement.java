/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class evenement {

    private int id;
    String nom;
    private Date date;
    private int nbr_personnes;
    private String categorie;
    private String description;

    public evenement() {

    }

    public evenement(int id, String nom, Date date, int nbr_personnes, String categorie, String description) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.nbr_personnes = nbr_personnes;
        this.categorie = categorie;
        this.description = description;
    }

    public evenement(String nom, Date date, int nbr_personnes, String categorie, String description) {
        this.nom = nom;
        this.date = date;
        this.nbr_personnes = nbr_personnes;
        this.categorie = categorie;
        this.description = description;
    }

    public evenement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", nbr_personnes=" + nbr_personnes + ", categorie=" + categorie + ", description=" + description + '}';
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final evenement other = (evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
