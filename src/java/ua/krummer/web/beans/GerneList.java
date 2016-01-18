
package ua.krummer.web.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.krummer.web.db.Database;

public class GerneList implements Serializable {
        
    ArrayList<Gerne> gerneList = new ArrayList<Gerne>();
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet resSet = null;
    
    private ArrayList<Gerne> getGernes(){
        
        try{
            conn = Database.getConnection();
            stmt = conn.createStatement();
            
            resSet = stmt.executeQuery("select * from gerne order by name");
            
            while(resSet.next()){
                Gerne gerne = new Gerne();
                
                gerne.setName(resSet.getString("name"));
                gerne.setId(resSet.getLong("id"));
                gerneList.add(gerne);
            }
            
        } catch (SQLException e){
            Logger.getLogger(GerneList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{
                if (resSet != null){
                    resSet.close();
                }
                if (stmt != null){
                    stmt.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (Exception e){
                Logger.getLogger(GerneList.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        return gerneList;
    }
    
    public ArrayList<Gerne> getGerneList(){
        if (!gerneList.isEmpty()){
            return gerneList;
        } else {
            return getGernes();
        }
    }
}

