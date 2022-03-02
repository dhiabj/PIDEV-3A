/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author zacha
 */
public class MenuCommande {
    private int id;
    
    private Date date;
    private int command_id;  
    private int menu_id;  

    public MenuCommande() {
    }
 public MenuCommande(int id) {
     this.id=id;
    }
    public MenuCommande(int id, Date date, int command_id, int menu_id) {
        this.id = id;
        this.date = date;
        this.command_id = command_id;
        this.menu_id = menu_id;
    }

    public MenuCommande(int command_id, int menu_id) {
        this.command_id = command_id;
        this.menu_id = menu_id;
    }
    

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getCommand_id() {
        return command_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    @Override
    public String toString() {
        return "MenuCommande{" + "id=" + id + ", date=" + date + ", command_id=" + command_id + ", menu_id=" + menu_id + '}';
    }
    
    
    
    
}
