
package database;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {
    
    
    
   private static final String userName = "root";
   private static final String password = "";
   private static final String connString = "jdbc:mysql://localhost:3306/storejava";
   
   public Connect(){
     }
   
   public Connection getConnection(){
       Connection conn = null;
       try{
           conn = DriverManager.getConnection(connString, userName, password);
          // System.out.println("connected");
       }catch(Exception e){
           System.out.println(e);
       }
       
       return conn;
   }
}
