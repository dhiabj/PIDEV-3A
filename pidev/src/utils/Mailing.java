/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.user;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nayrouz
 */
public class Mailing {
    
            public static void sendMail(user c) throws Exception {

        Properties prop = new Properties();
        final String moncompteEmail = "foruesprit2022@gmail.com";
        final String psw = "esprit123";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(moncompteEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(c.getEmail()));
            msg.setSubject("ForU");
            msg.setText("Bonjour Mr/Mme "+ c.getNom()+", votre inscription est validé ! " );
            System.out.println(c.getEmail());
            System.out.println("mail sent");

            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }

         }   
        
//     public static void sendMail() throws Exception {
//
//        System.out.println("Preparing to send email");
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        String myAccountEmail = "foruesprit2022@gmail.com";
//        String password = "esprit123";
//
//        Session session = Session.getInstance(properties, new Authenticator() {
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//
//        Message message = prepareMessage(session, myAccountEmail, myAccountEmail);
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//    }
//
//    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
//        Message message = new MimeMessage(session);
//
//        try {
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Test");
//            message.setText("Test Test");
//        } catch (Exception e) {
//            Logger.getLogger(Mailing.class.getName(), e.getMessage());
//        }
//        return message;
//    }
//
//    
}
