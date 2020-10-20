
package order.lineOrder;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import product.ProductManager;


public class OrderInfoManager {

    Connection connection = null;
    Statement statemant = null;
    Connect connect = new Connect();
    ProductManager productManager = new ProductManager(); 
    
    public OrderInfoManager(){
    connection = connect.getConnection();
    }
    
    public void insert(OrderInfo orderInfo){
    String sql = "INSERT INTO orderInfo(productId, productName, quantity,subTotal, orderId) values(?,?,?,?,?)";
    
    try{
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, orderInfo.getProductId());
    preparedStatement.setString(2, orderInfo.getProductName());
    preparedStatement.setInt(3, orderInfo.getQuantity());
    preparedStatement.setFloat(4, orderInfo.getSubTotal());
    preparedStatement.setInt(5, orderInfo.getOrderId());
    
    preparedStatement.executeUpdate();
    }catch(SQLException e){
    
    }
    }
    
    public void delete(int productId, int orderId){
       String sql;
       sql = " DELETE FROM orderinfo where productId = ? AND orderId = ?";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, productId);
           preparedStatement.setInt(2, orderId);
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
           
       }
    }
    
    public void editQuantity(int quantity, int productId, int orderId){
        String sql = "UPDATE orderInfo set quantity = ?, subTotal = ? where productId = ? AND orderId = ?";
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, quantity);
        preparedStatement.setFloat(2, quantity * productManager.search(productId).getPrice() );
        preparedStatement.setInt(3, productId);
        preparedStatement.setInt(4, orderId);
        preparedStatement.executeUpdate();
     
        }catch(SQLException e){
        }
        
    
    }
    
    public float getSubTotal(int productId, int orderId){
    String sql = "SELECT * FROM orderinfo where productId = ? AND orderId = ? ";
    ResultSet resultSet;
    float subTotal = 0;
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productId);
        preparedStatement.setInt(2, orderId);
        resultSet = preparedStatement.executeQuery();
        subTotal = resultSet.getFloat("subTotal");
    }catch(SQLException e){
    }
    return subTotal;
    }
    
    
    
    public float getTotalById(int orderId){
    float total = 0;
    String sql = "SELECT * FROM orderInfo where orderId = ?";
    ResultSet resultSet;
    
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, orderId);
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      total = total + resultSet.getFloat("subTotal");
      }
    }catch(SQLException e){
    }
    return total;
    }
    
    
    
     public List<OrderInfo> getAllByOrderId(int orderId){
    List<OrderInfo> list = new ArrayList<OrderInfo>();
    OrderInfo orderInfo;
    ResultSet resultSet;
    
    String sql = "SELECT * FROM orderInfo where orderId = ?";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, orderId);
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
      orderInfo = new OrderInfo(resultSet.getInt("productId"), resultSet.getNString("productName"), resultSet.getInt("quantity"), resultSet.getFloat("subTotal"), resultSet.getInt("orderId"));
      list.add(orderInfo);
      }
    }catch(SQLException e){
    }
    
    
    return list;
}
    
    
 
    
    
    
}
