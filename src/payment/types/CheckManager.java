
package payment.types;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckManager {

    Connection connection = null;
    Statement statement = null;
    Connect connect = new Connect();
    
    
    public CheckManager(){
    connection = connect.getConnection();
    }
    
    public void insert(Check check){
    String sql; 
       sql = " INSERT INTO checkpayment(FirstName, LastName, amount, bank, date) values(?,?,?,?,?) ";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, check.getFirstName());
           preparedStatement.setString(2, check.getLastName());
           preparedStatement.setFloat(3, check.getAmount());
           preparedStatement.setString(4, check.getBank());
           preparedStatement.setString(5, check.getDate());
           
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       
       }
    }
    
}
