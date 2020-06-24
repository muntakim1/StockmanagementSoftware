/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
    
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Transactions (\n" +
"	\"Product_ID\"	INTEGER NOT NULL,\n" +
"	\"Type\"	TEXT NOT NULL,\n" +
"	\"Quantity\"	NUMERIC NOT NULL,\n" +
"	\"Weight\"	NUMERIC NOT NULL,\n" +
"	\"DATE\"	TEXT NOT NULL,\n" +
"	\"CRT\"	INTEGER NOT NULL,\n" +
"	PRIMARY KEY(\"Product_ID\")";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
