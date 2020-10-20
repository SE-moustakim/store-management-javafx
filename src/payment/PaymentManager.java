
package payment;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PaymentManager implements DaoPaymentManager {

    Connection connection = null;
    Statement statement = null;
    Connect connect = new Connect();
    
    public PaymentManager(){
    connection = connect.getConnection();
    }
    
    @Override
    public void insert(Payment payment){
    String sql;
    sql = "INSERT INTO payment(saleId,num,amount,date,type) values (?,?,?,?,?)";
    try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, payment.getSaleId());
           preparedStatement.setInt(2, payment.getNum());
           preparedStatement.setDouble(3, payment.getAmount());
           preparedStatement.setString(4, payment.getDate());
           preparedStatement.setString(5, payment.getType());
          
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       
       }
    
    }
    
    
    
    @Override
    public int getLastNum(int saleId){
    ResultSet resultSet;
    int lastNum = 0;
    String sql = "SELECT * FROM payment where saleId = ?";
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, saleId);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
           lastNum = resultSet.getInt("num");    
           }
        
    }
    catch(SQLException e){
    
    }
    return lastNum;
    }
    
    
    @Override
    public List<Payment> getAllBySaleId(int saleId){
    List<Payment> list = new ArrayList<Payment>();
    Payment payment;
    ResultSet resultSet;
    
    String sql = "SELECT * FROM payment where saleId = ?";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, saleId);
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      payment = new Payment(resultSet.getInt("id"),resultSet.getInt("saleId"),resultSet.getInt("num"),resultSet.getDouble("amount"),resultSet.getNString("date"),resultSet.getNString("type"));
      list.add(payment);
      }
    }catch(SQLException e){
    }
    
    
    return list;
}
    
    @Override
    public double getTotalPayment(int saleId){
    double total = 0;     
    ResultSet resultSet;
    
    String sql = "SELECT * FROM payment where saleId = ?";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, saleId);
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      total = total + resultSet.getDouble("amount"); 
      }
    }catch(SQLException e){
    }
    
    
    return total;
}
    
}
    

