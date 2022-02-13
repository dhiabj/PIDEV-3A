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
public class Reclamation_user {
    private int id ;
    private int idr ;
    private String titre ; 
    private String texte ; 
  

    public Reclamation_user() {
    }

    public Reclamation_user(String titre, String texte) {
        this.titre = titre;
        this.texte = texte;
       
    }
    

    public Reclamation_user(int id, int idr , String titre, String texte) {
        this.id = id;
        this.idr = idr ; 
        this.titre = titre;
        this.texte = texte;
        
        
    }

    public Reclamation_user(int id , String titre, String texte, String reponse) {
        this.id = id;
        
        this.titre = titre;
        this.texte = texte;
        
    }
    

    public int getId() {
        return id;
    }
    public int getIdr() {
        return idr;
    }

    public String getTitre() {
        return titre;
    }

    public String getTexte() {
        return texte;
    }

   
    

    public void setId(int id) {
        this.id = id;
    }
     public void setIdr(int idr) {
        this.idr = idr;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    
    

    @Override
    public String toString() {
        return "Reclamation_user{" + "id=" + id +", idr" + idr + ", titre=" + titre + ", texte=" + texte   + '}';
    }
    
    
}
