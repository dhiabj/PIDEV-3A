/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.Ingredients;
import entities.Menu;
import services.IngredientsService;
import services.MenuService;
import utils.DataSource;

/**
 *
 * @author Dhia
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource ds1 = DataSource.getInstance();
        Menu m=new Menu("Pizza", "Pizza vegan",13.5F,"vegan");
        Menu m1=new Menu(1,"Pizza", "Pizza peperoni",14.5F,"normal");
        Menu m2=new Menu(1);
        MenuService ms = new MenuService();
        //ms.ajouterMenu(m);
        //ms.modifierMenu(m1);
        //ms.suppMenu(m2);
        System.out.println(ms.afficherMenu());
        Ingredients i =new Ingredients("Eggs",20,2);
        Ingredients i1 =new Ingredients(1,"tomatos",30,3);
        Ingredients i2 =new Ingredients(1);
        IngredientsService is= new IngredientsService();
        //is.ajouterIngredient(i);
        //is.modifierIngredient(i1);
        is.suppIngredient(i2);
        System.out.println(is.afficherIngredient());
    }
    
}
