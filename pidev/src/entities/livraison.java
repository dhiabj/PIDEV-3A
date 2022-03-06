/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nayrouz gggg
 */
public class livraison {

    private int id;
    private int user_id;
    private int livreur_id;
    private int commande_id;
    private String nom;
    private String etat;

    public livraison() {
    }

    public livraison(int id) {
        this.id = id;
    }

    public livraison(int user_id, int livreur_id, int commande_id, String nom, String etat) {
        this.user_id = user_id;
        this.livreur_id = livreur_id;
        this.commande_id = commande_id;
        this.nom = nom;
        this.etat = etat;
    }

    public livraison(int id, int user_id, int livreur_id, int commande_id, String nom, String etat) {
        this.id = id;
        this.user_id = user_id;
        this.livreur_id = livreur_id;
        this.commande_id = commande_id;
        this.nom = nom;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLivreur_id() {
        return livreur_id;
    }

    public void setLivreur_id(int livreur_id) {
        this.livreur_id = livreur_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "livraison{" + "id=" + id + ", user_id=" + user_id + ", livreur_id=" + livreur_id + ", commande_id=" + commande_id + ", nom=" + nom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final livraison other = (livraison) obj;
        return this.id == other.id;
    }

}
