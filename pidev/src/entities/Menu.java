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
    private String ingredients;
    private String categorie;
    private String image;
    private int mcId;
    
    public Menu (){
        
    }
    
    public Menu (int id){
        this.id=id;
    }
    
    public Menu (String titre, String description, float prix, String ingredients, String categorie, String image){
        this.titre=titre;
        this.description=description;
        this.prix=prix;
        this.ingredients=ingredients;
        this.categorie=categorie;
        this.image=image;
        
    }
    
    public Menu (int id, String titre, String description, float prix, String ingredients, String categorie, String image){
        this.id=id;
        this.titre=titre;
        this.description=description;
        this.prix=prix;
        this.ingredients=ingredients;
        this.categorie=categorie;
        this.image=image;
        
    }

    public Menu(int id, String titre, String description, float prix, String ingredients, String categorie, String image, int mcId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.ingredients = ingredients;
        this.categorie = categorie;
        this.image = image;
        this.mcId = mcId;
    }

    public int getMcId() {
        return mcId;
    }

    public void setMcId(int mcId) {
        this.mcId = mcId;
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", ingredients=" + ingredients + ", categorie=" + categorie + ", image=" + image + ", mcId=" + mcId + '}';
    }

    
    
    

}
