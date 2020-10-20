/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author x
 */
public class ProductManager implements DaoProductManager  {
   Connection connection = null;
    Statement statement = null;
    Connect connect = new Connect();
    
   public ProductManager(){
       connection = connect.getConnection();
   }
   
  
   @Override
   public void insert(Product product){
       
       String sql; 
       sql = " INSERT INTO product(name, price,addDate, categoryId) values(?,?,?,?) ";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
          
           preparedStatement.setString(1, product.getName());
           preparedStatement.setFloat(2, product.getPrice());
           preparedStatement.setString(3, product.getAddDate());
           preparedStatement.setInt(4, product.getCategoryId());
           //preparedStatement.setBytes(4, product.getImage());
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       
       }
   
   }
   
   @Override
   public void edit(int id,String name, float price, String addDate,int categoryId){
       String sql;
       
       sql = " UPDATE product SET name = ?, price = ?, addDate = ?, categoryId = ? where id = ? ";
       
       
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           
           preparedStatement.setString(1, name);
           preparedStatement.setFloat(2, price);
           preparedStatement.setString(3, addDate);
           preparedStatement.setInt(4, categoryId);
           preparedStatement.setInt(5, id);
           
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       }
       
       
       
   }
   
   @Override
   public Product search(int id){
       String sql;
       ResultSet resultSet;
       Product product;
       sql = " SELECT * FROM product where id = ? ";
       
       try{
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, id);
         resultSet = preparedStatement.executeQuery();
          if(resultSet.next()){
             product = new Product(resultSet.getInt("id"), resultSet.getNString("name"), resultSet.getFloat("price"), resultSet.getNString("addDate"), resultSet.getInt("categoryId"));
             return product;
           }
       }catch(SQLException e){
       }
    return null;
   }
   
   
   @Override
   public void delete(int id){
       String sql;
       sql = " DELETE FROM product where id = ? ";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, id);
           preparedStatement.executeUpdate();
           
       }catch(SQLException e){
       }
       
       
   }
   
   @Override
   public List<Product> getAll(){
       List<Product> list = new ArrayList<>();
       Product product;
       ResultSet resultSet;
       String sql;
       sql = " SELECT * FROM product ";
       
       try{
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         resultSet = preparedStatement.executeQuery();
         while(resultSet.next()){
               product = new Product(resultSet.getInt("id"), resultSet.getNString("name"), resultSet.getFloat("price"), resultSet.getNString("addDate"), resultSet.getInt("categoryId"));
               list.add(product);
           }
       }catch(SQLException e){
       }
       
       if(list.isEmpty())
           return null;
       return list;
       
     }
   
   @Override
   public List<Product> getAllByCategory(int categoryId){
       List<Product> list = new ArrayList<>();
       Product product;
       ResultSet resultSet;
       String sql;
       sql = " SELECT * FROM product where categoryId = ? ";
       
       try{
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, categoryId);
         resultSet = preparedStatement.executeQuery();
         while(resultSet.next()){
               product = new Product(resultSet.getInt("id"), resultSet.getNString("name"), resultSet.getFloat("price"), resultSet.getNString("addDate"), resultSet.getInt("categoryId"));
               list.add(product);
           }
       }catch(SQLException e){
       }
       
       if(list.isEmpty())
           return null;
       return list;
       
     }
   
   
    
}
