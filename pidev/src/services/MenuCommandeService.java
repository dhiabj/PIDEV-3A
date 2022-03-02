/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Menu;
import entities.MenuCommande;
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
public class MenuCommandeService {
     private Statement st;
    private PreparedStatement pst;
    private Connection conn;
    public MenuCommandeService(){
    conn=DataSource.getInstance().getCnx();
    }
    public void ajouterMenuCommande (MenuCommande c){
    String req="insert into menu_commande (command_id,menu_id) values (?,?)";
     try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getCommand_id());
            pst.setInt(2, c.getMenu_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierMenucommande(MenuCommande c)
    {
    String req="update menu_commande set date= ? ,command_id= ? ,menu_id= ?  where id= ? ";
    
     try {
            pst = conn.prepareStatement(req);
          
           
            pst.setDate(1, c.getDate());
            pst.setInt(2, c.getCommand_id());
            pst.setInt(3, c.getMenu_id());
            pst.setInt(4, c.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public float sum(MenuCommande c)
   {
       int id=c.getCommand_id();
       String sql="SELECT SUM(m.prix) from menu m,menu_commande mc where mc.menu_id=m.id AND command_id=52";
   float sum1 = 0;
    try { pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
    
    while(rs.next()){
         sum1 =rs.getFloat("1");
                   return sum1;
            }
    }
    
    
    catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
   return sum1;

}
    
    public boolean supCommande(MenuCommande c)
    {
    String req="delete from menu_commande where id= ?";
    try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuCommandeService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public List <Menu> afficherMenuCommande(){
        List <Menu> Menucommandes= new ArrayList<>();
    String sql="select mc.id as mcId,m.id,m.titre,m.ingredients,m.prix,m.image from menu as m left join menu_commande as mc on m.id=mc.menu_id left join commande as c on mc.command_id=c.id Where c.etat='non valide'";
    try { pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
    
    while(rs.next()){
                Menu m = new Menu();
                m.setId(rs.getInt("id"));
                m.setTitre(rs.getString("titre"));
                m.setIngredients(rs.getString("ingredients"));
                m.setPrix(rs.getFloat("prix"));
                m.setImage(rs.getString("image"));
                m.setMcId(rs.getInt("mcId"));
                Menucommandes.add(m);
            }
    }
    
    
    catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    return  Menucommandes;
    }
    
     public List <MenuCommande> afficherMenuCommandeId(){
        List <MenuCommande> Menucommandes= new ArrayList<>();
    String sql="select mc.id from menu as m left join menu_commande as mc on m.id=mc.menu_id left join commande as c on mc.command_id=c.id Where c.etat='non valide'";
    try { pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
    
    while(rs.next()){
                MenuCommande mc = new MenuCommande();
                mc.setId(rs.getInt("id"));
                Menucommandes.add(mc);
            }
    }
    
    
    catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    return  Menucommandes;
    }
    
    private float total;
    public Float afficherTotal(){
    String sql="select sum(m.prix) as total from menu as m left join menu_commande as mc on m.id=mc.menu_id left join commande as c on mc.command_id=c.id Where c.etat='non valide'";
    try { pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
    
    while(rs.next()){
                this.total = rs.getFloat("total");
            }
    }
    
    
    catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    return  total;
    }
  
}