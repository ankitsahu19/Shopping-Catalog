/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class DBConnection {
  private static Connection conn;
    
    
 static {
            try{
        
    Class.forName("oracle.jdbc.OracleDriver");
    System.out.println("Driver successefully loaded");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-8OKBQBO7:1521/XE","emedicine","medicine");
    
        }
  
    catch(Exception ex) {
          System.out.println("Exception In Opening Connnection In DBConnection");
        
    }
    }
        public static Connection getConnection()
        {
            return conn;
        }
        public static void closeConnection()
        {
            try
            {
                conn.close();
            }
            catch(Exception ex)
            {
                System.out.println("Exception in closing"+ex);
            }
        }  
}


