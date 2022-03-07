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
public class Encapsulation_Reclamation_Admin {
    
    private static int idr ;
    private static int id ; 
    private static String reponse ; 

    public Encapsulation_Reclamation_Admin() {
    }

   

    public Encapsulation_Reclamation_Admin(int id, String reponse) {
        this.id = id;
        this.reponse = reponse;
    }

    public Encapsulation_Reclamation_Admin(int id) {
        this.id = id;
    }
    
    public Encapsulation_Reclamation_Admin(String reponse) {
        this.reponse = reponse;
    }

    public Encapsulation_Reclamation_Admin(int idr, int id, String reponse) {
        this.idr = idr;
        this.id = id;
        this.reponse = reponse;
    }

    public static int getIdr() {
        return idr;
    }

    public static int getId() {
        return id;
    }

    public static String getReponse() {
        return reponse;
    }

    public static void setIdr(int idr) {
        Encapsulation_Reclamation_Admin.idr = idr;
    }

    public static void setId(int id) {
       Encapsulation_Reclamation_Admin.id = id;
    }

    public static void setReponse(String reponse) {
        Encapsulation_Reclamation_Admin.reponse = reponse;
    }
    

    }

