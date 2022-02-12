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
import entities.reservation;
import services.ReservationService;
/**
 *
 * @author Nayrouz
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         DataSource ds1 = DataSource.getInstance();
         //user u1=new user(5,"karim","boubaker","elGhazella",55881122,"karim.boubaker@esprit.tn","karim123");
         //UserService ps=new UserService();
         //ps.ajouterUser(u1);
         //ps.ajouterUserPst(u1);
         //ps.modifierUserPst(u1);
         //ps.suppUserPst(u1);
         //ps.readAll().forEach(e->System.out.println(e));
         
         
         //Evenement
         
         EvenementService ev=new EvenementService();
         evenement e=new evenement(2,"Fev",6,"journée vegan");
         //ev.ajouterEventPst(e);
         //ev.modifierEvenementPst(e);
         //ev.suppEvenementPst(e);
         //ev.readEvent().forEach(u->System.out.println(u));
         
         //Reservation
         
         ReservationService rv=new ReservationService();
         reservation r=new reservation(1,4,1,"Neyrouz-reservation");
         //rv.ajouterReservation(r);
         //rv.readReservation().forEach(u->System.out.println(u));
         //rv.modifierReservationPst(r);
         rv.suppReservationPst(r);
         
         
         
         
         
         
         
    }
    
    
}
