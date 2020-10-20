
package product.category;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import product.Product;


public class CategoryManager {

Connection connection = null;
    Statement statement = null;
    Connect connect = new Connect();
    
    public CategoryManager(){
    connection = connect.getConnection();
    }
    
    public void insert(Category category){
    
        String sql = " INSERT INTO category(name) values(?) ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getName());
            
            preparedStatement.executeUpdate();
            
        }
        catch(SQLException e){
        
        }
    }
    
    public Category search(int id){
    ResultSet resultSet;
    Category category;
    String sql = " SELECT * FROM category where id = ? ";
    try{
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
     preparedStatement.setInt(1, id);
     resultSet = preparedStatement.executeQuery();
     while(resultSet.next()){
     category = new Category(resultSet.getInt("id"), resultSet.getNString("name"));
     return category;
     }
    }catch(SQLException e){
    
    }
    return null;
    }
    
    
    
    public List<Category> getAll(){
    List<Category> list = new ArrayList<Category>();
       Category category;
       ResultSet resultSet;
       String sql;
       sql = " SELECT * FROM category ";
       
       try{
       PreparedStatement preparedStatement = connection.prepareStatement(sql);
         resultSet = preparedStatement.executeQuery();
         while(resultSet.next()){
         category = new Category(resultSet.getInt("id"), resultSet.getNString("name"));
         list.add(category);
         
         }
       }
       catch(SQLException e){
       
       }
       
       if(list.size() == 0)
           return null;
       return list;
    }
    
    
    
}
