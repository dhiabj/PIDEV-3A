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
        /*Menu m=new Menu("Pizza", "Pizza vegan",13.5F,"vegan");
        MenuService ms = new MenuService();
        ms.ajouterMenu(m);
        System.out.println(ms.afficherMenu());*/
        Ingredients i =new Ingredients("Eggs",20,1);
        IngredientsService is= new IngredientsService();
        is.ajouterIngredient(i);
        System.out.println(is.afficherIngredient());
    }
    
}
