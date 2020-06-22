/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author munta
 */
public class ConnectionManager {
    public static Connection Connect(){
        
        Connection conn=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("Database Connected");
        } catch (ClassNotFoundException | SQLException ex) {
           System.out.println(ex+"");
        }
        
        return conn;
    }
    
}
