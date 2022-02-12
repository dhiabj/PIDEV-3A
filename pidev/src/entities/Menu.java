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
public class Menu {
    private int id;
    private String titre;
    private String description;
    private float prix;
    private String categorie;
    
    public Menu (){
        
    }
    
    public Menu (String titre, String description, float prix, String categorie){
        this.titre=titre;
        this.description=description;
        this.prix=prix;
        this.categorie=categorie;
        
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", categorie=" + categorie + '}';
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
