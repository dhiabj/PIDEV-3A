/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Nayrouz
 */
public class user {

    private int id;
    private String nom;
    private String prenom;
    private Date date;
    private String adresse;
    private int num_tel;
    private String email;
    private String password;
    private String image;
    private String role;

    public user() {
    }
     public user(int id, String nom, String prenom,/*String password,*/String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        //this.password = password;
    }

    public user(int id, String nom, String prenom, String email, String password, Date date, int num_tel, String adresse,String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
    }
    

    public user(String nom, String prenom, String email, String password, Date date, int num_tel, String adresse, String role) {//id auto increment
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", num_tel=" + num_tel + ", email=" + email + ", password=" + password + '}';
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final user other = (user) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
