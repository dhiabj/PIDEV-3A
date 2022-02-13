/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Reclamation_admin {
    private int idr ;
    private int id ; 
    private String reponse ; 

    public Reclamation_admin() {
    }

    public Reclamation_admin(int idr, String reponse) {
        this.idr = idr;
        this.reponse = reponse;
    }
    
    public Reclamation_admin(String reponse) {
        this.reponse = reponse;
    }

    public Reclamation_admin(int idr, int id, String reponse) {
        this.idr = idr;
        this.id = id;
        this.reponse = reponse;
    }

    public int getIdr() {
        return idr;
    }

    public int getId() {
        return id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    

  
}
