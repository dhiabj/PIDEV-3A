/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author zacha
 */
public class CommandeService {
    private Statement st;
    private PreparedStatement pst;
    private Connection conn;
    
    public CommandeService(){
    conn=DataSource.getInstance().getCnx();
    }
    public void ajouterCommande (Commande c){
    String req="insert into commande(etat,date,user_id) values (?,?,?)";
     try {
            pst = conn.prepareStatement(req);
            pst.setString(2, c.getEtat());
            pst.setDate(3, c.getDate());
            pst.setInt(4, c.getUser_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List <Commande> afficherCommande(){
        List <Commande> commandes= new ArrayList<>();
    String sql="select * from commande";
    try { pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
    
    while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setEtat(rs.getString("etat"));
                c.setDate(rs.getDate("date"));
                c.setUser_id(rs.getInt("user_id"));
                
                commandes.add(c);
            }
    }
    
    
    catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    return  commandes;
    } 
}
