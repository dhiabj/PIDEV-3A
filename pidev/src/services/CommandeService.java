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
    public static int id;
    
    public CommandeService(){
    conn=DataSource.getInstance().getCnx();
    }
    public void ajouterCommande (Commande c){
    String generatedColumns[] = { "ID" };
    String req="insert into commande(etat,date,total,user_id) values (?,?,?,?)";
     try {
            pst = conn.prepareStatement(req, generatedColumns);
            pst.setString(1, c.getEtat());
            pst.setDate(2, c.getDate());
            pst.setFloat(3, c.getTotal());
            pst.setInt(4, c.getUser_id());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
            id = rs.getInt(1);
            
            
            //System.out.println("Inserted ID -" + id); // display inserted record
        }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifiercommande(Commande c)
    {
    String req="update commande set etat= ? ,date= ? ,user_id= ?  where id= ? ";
    
     try {
            pst = conn.prepareStatement(req);
          
            pst.setString(1, c.getEtat());
            pst.setDate(2, c.getDate());
            pst.setInt(3, c.getUser_id());
            pst.setInt(4, c.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean modifierEtat(Commande c)
    {
    String req="update commande set etat= ?, total= ? where id= ? ";
    
     try {
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getEtat());
            pst.setFloat(2, c.getTotal());
            pst.setInt(3, c.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void supCommande(Commande c)
    {
    String req="delete  from commande where id= ?";
    try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public List <Commande> afficherCommande(){
    List <Commande> commandes= new ArrayList<>();
    String sql="select * from commande where etat='non valide'";
    try { pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
    
    while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setEtat(rs.getString("etat"));
                c.setDate(rs.getDate("date"));
                c.setTotal(rs.getFloat("total"));
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
