/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;
import java.sql.Date;
import entities.Commande;
import java.time.LocalDateTime;
import services.CommandeService;
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
        Date currentDate=new Date(2021-07-01);
        Commande c=new Commande(1,"en livraison",currentDate,1);
        Commande c1=new Commande(2,"en attente",currentDate,1);
        System.out.println(c);
        CommandeService cs=new CommandeService();
        /*cs.ajouterCommande(c1);*/
        Commande c2= new Commande(3,"En livraison",currentDate,1);
        Commande c3= new Commande(3);
        cs.supCommande(c3);
        cs.modifiercommande(c2);
       
        
    }
    
}
