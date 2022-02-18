/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.user;
import utils.DataSource;
import services.UserService;
import services.EvenementService;
import entities.evenement;
import entities.livraison;
import entities.reservation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ReservationService;
import services.LivraisonService;
/**
 *
 * @author Nayrouz
 */
public class Pidev extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         //DataSource ds1 = DataSource.getInstance();
         launch(args);
         
         //user u1=new user(5,"karim","boubaker","elGhazella",55881122,"karim.boubaker@esprit.tn","karim123");
         //UserService ps=new UserService();
         //ps.ajouterUser(u1);
         //ps.ajouterUserPst(u1);
         //ps.modifierUserPst(u1);
         //ps.suppUserPst(u1);
         //ps.readAll().forEach(e->System.out.println(e));
         
         
         //Evenement
         
//         EvenementService ev=new EvenementService();
//         evenement e=new evenement(2,"Fev",6,"journée vegan");
         //ev.ajouterEventPst(e);
         //ev.modifierEvenementPst(e);
         //ev.suppEvenementPst(e);
         //ev.readEvent().forEach(u->System.out.println(u));
         
         //Reservation
         
//         ReservationService rv=new ReservationService();
//         reservation r=new reservation(1,4,1,"Neyrouz-reservation");
         //rv.ajouterReservation(r);
         //rv.readReservation().forEach(u->System.out.println(u));
         //rv.modifierReservationPst(r);
         //rv.suppReservationPst(r);
         
         //Livraison
          //livraison l=new livraison(6,5,1,1,"nayrouz_Liv","livrée");//ajout uniquement
          //livraison l=new livraison(4,5,1,1,"new nayrouz_Livraison");
          //livraison l=new livraison(4);//supp
          //LivraisonService LA=new LivraisonService();
          //LA.ajouterLivraisonPst(l);
         //LA.modifierLivraisonPst(l);
          //LA.readLivraison().forEach(u->System.out.println(u));
         //LA.suppLivraisonPst(l);
         
         
         
         
         
         
         
         
         
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("/Vues/MainFXML.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setTitle("FORU");
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    
    
}
