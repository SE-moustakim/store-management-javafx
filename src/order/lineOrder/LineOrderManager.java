
package order.lineOrder;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LineOrderManager {

    Connection connection = null;
    Statement statemant = null;
    Connect connect = new Connect();
    
    public LineOrderManager(){
    connection = connect.getConnection();
    }
    
    private void insert(LineOrder lineOrder, int orderId){
    String sql = "INSERT INTO lineorder(productId, quantity, orderId) values(?,?,?)";
    
    try{
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, lineOrder.getProductId());
    preparedStatement.setInt(2, lineOrder.getQuantity());
    preparedStatement.setInt(3, orderId);
    
    preparedStatement.executeUpdate();
    }catch(SQLException e){
    
    }
    }
    
    public void editQuantity(int quantity, int orderId, int productId){
    String sql = "UPDATE line_order set quantity = ? where orderId = ? AND productId = ? ";
     try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, quantity);
      preparedStatement.setInt(2, orderId);
      preparedStatement.setInt(3, productId);
      
      preparedStatement.executeUpdate();
      
    }catch(SQLException e){
    }
    }
    
    public List<LineOrder> getAllByOrderId(int orderId){
    List<LineOrder> list = new ArrayList<LineOrder>();
    LineOrder lineOrder;
    ResultSet resultSet;
    
    String sql = "SELECT * FROM line_order where orderId = ?";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, orderId);
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      //lineOrder = new LineOrder(resultSet.getInt("productId"), resultSet.getInt("quantity"), resultSet.getInt("orderId"));
      //list.add(lineOrder);
      }
    }catch(SQLException e){
    }
    
    
    return list;
}
    
public int getLastId(){
ResultSet resultSet;
int lastId = 0;   
    String sql = "SELECT * FROM table_order";
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
