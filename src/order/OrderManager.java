
package order;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderManager implements DaoOrderManager {
    Connection connection = null;
    Statement statemant = null;
    Connect connect = new Connect();

public OrderManager(){
connection = connect.getConnection();
}



@Override
public void insert(Order order){
String sql; 
       sql = " INSERT INTO order_table(customerId, description,total, date) values(?,?,?,?) ";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
          
           preparedStatement.setInt(1, order.getCustomerId());
           preparedStatement.setString(2, order.getDescription());
           preparedStatement.setFloat(3, order.getTotal());
           preparedStatement.setString(4, order.getDate());
           //preparedStatement.setBytes(4, product.getImage());
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       
       }
}

public void editTotal(int orderId, float total){

String sql = "UPDATE order_table SET total = ? where id = ?";

        try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
          
           preparedStatement.setFloat(1, total);
           preparedStatement.setInt(2, orderId);
           
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       
       }
}


    @Override
    public Order search(int id){
       String sql;
       ResultSet resultSet;
       Order order;
       sql = " SELECT * FROM order_table where id = ? ";
       
       try{
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, id);
         resultSet = preparedStatement.executeQuery();
          if(resultSet.next()){
             order = new Order(resultSet.getInt("id"), resultSet.getInt("customerId"), resultSet.getNString("description"), resultSet.getFloat("total"), resultSet.getNString("date"));
             return order;
           }
       }catch(SQLException e){
         e.printStackTrace();
       }
    return null;
   }


    
    @Override
    public List<Order> getAll(){
    
    List<Order> list = new ArrayList<Order>();
    Order order;
    ResultSet resultSet;
    
    String sql = "SELECT * FROM order_table";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      order = new Order(resultSet.getInt("id"), resultSet.getInt("customerId"), resultSet.getNString("description"), resultSet.getFloat("total"), resultSet.getNString("date"));
      list.add(order);
      }
    }catch(SQLException e){
    }
    
    
    return list;
}
    
     public List<Order> getAllByCustomerId(int customerId){
    
    List<Order> list = new ArrayList<Order>();
    Order order;
    ResultSet resultSet;
    
    String sql = "SELECT * FROM order_table where customerId = ?";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      order = new Order(resultSet.getInt("id"), resultSet.getInt("customerId"), resultSet.getNString("description"), resultSet.getFloat("total"), resultSet.getNString("date"));
      list.add(order);
      }
    }catch(SQLException e){
    }
    
    
    return list;
}
    
       public int getLastId(){
ResultSet resultSet;
int lastId = 0;   
    String sql = "SELECT * FROM order_table";
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
           lastId = resultSet.getInt("id");     
           }
        
    }
    catch(SQLException e){
    
    }
    return lastId;
}


}
