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
    
    public Ingredients(){
        
    }
    
    public Ingredients(int id){
        this.id=id;
    }
    
    public Ingredients(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }

    public Ingredients(int id, String nom, int quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Ingredients{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + '}';
    }
    
}
