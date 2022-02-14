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
      private Date date;
     private int nbr_personnes;
     private String categorie;

     public evenement() {
         
     }
    public evenement(int id, Date date, int nbr_personnes, String categorie) {
        this.id = id;
        this.date = date;
        this.nbr_personnes = nbr_personnes;
        this.categorie = categorie;
    }
        public evenement(Date date, int nbr_personnes, String categorie) {
        this.date = date;
        this.nbr_personnes = nbr_personnes;
        this.categorie = categorie;
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

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", date=" + date + ", nbr_personnes=" + nbr_personnes + ", categorie=" + categorie + '}';
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
