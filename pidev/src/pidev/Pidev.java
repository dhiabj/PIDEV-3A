/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import utils.DataSource;
import services.EvenementService;
import entities.evenement;
import entities.reservation;
import java.sql.Date;
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
         Date currentDate=new Date(2021-07-01);
         
         
         
         //Evenement
         
         EvenementService ev=new EvenementService();
         evenement e=new evenement(1);
         //ev.ajouterEventPst(e);
         //ev.modifierEvenementPst(e);
         ev.suppEvenementPst(e);
         //ev.readEvent().forEach(u->System.out.println(e));
         
         //Reservation
         
         ReservationService rv=new ReservationService();
         reservation r=new reservation(1);
         //rv.ajouterReservation(r);
         //rv.readReservation().forEach(u->System.out.println(u));
         //rv.modifierReservationPst(r);
         //rv.suppReservationPst(r);
         
         
         
         
         
         
         
    }
    
    
}
