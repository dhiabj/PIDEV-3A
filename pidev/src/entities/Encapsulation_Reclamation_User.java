/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class Encapsulation_Reclamation_User {
    private static int id ;
    private static int idr ;
    private static String titre ; 
    private static String texte ; 
  

    public Encapsulation_Reclamation_User() {
    }

    public Encapsulation_Reclamation_User(String titre, String texte) {
        this.titre = titre;
        this.texte = texte;
       
    }
    

    public Encapsulation_Reclamation_User(int id, int idr , String titre, String texte) {
        this.id = id;
        this.idr = idr ; 
        this.titre = titre;
        this.texte = texte;
        
        
    }

    public Encapsulation_Reclamation_User(int id , String titre, String texte, String reponse) {
        this.id = id;
        
        this.titre = titre;
        this.texte = texte;
        
    }
    

    public static int getId() {
        return id;
    }
    public static int getIdr() {
        return idr;
    }

    public static String getTitre() {
        return titre;
    }

    public static String getTexte() {
        return texte;
    }

   
    

    public static void setId(int id) {
        Encapsulation_Reclamation_User.id = id;
    }
     public static void setIdr(int idr) {
        Encapsulation_Reclamation_User.idr = idr;
    }

    public static void setTitre(String titre) {
        Encapsulation_Reclamation_User.titre = titre;
    }

    public static void setTexte(String texte) {
        Encapsulation_Reclamation_User.texte = texte;
    }

    
    

    @Override
    public String toString() {
        return "Reclamation_user{" + "id=" + id +", idr" + idr + ", titre=" + titre + ", texte=" + texte   + '}';
    }
}
