/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Dhia
 */
public class Ingredients {
    private int id;
    private String nom;
    private int quantite;
    private int menu_id;
    
    public Ingredients(){
        
    }
    
    public Ingredients(int id){
        this.id=id;
    }
    
    public Ingredients (int id, String nom, int quantite, int menu_id){
        this.id=id;
        this.nom=nom;
        this.quantite=quantite;
        this.menu_id=menu_id;
    }
    
    public Ingredients (String nom, int quantite, int menu_id){
        this.nom=nom;
        this.quantite=quantite;
        this.menu_id=menu_id;
    }

    @Override
    public String toString() {
        return "Ingredients{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", menu_id=" + menu_id + '}';
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
    
    
}
