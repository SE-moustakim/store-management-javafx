
package customer;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerManager implements DaoCustomerManager {
    
    Connection connection = null;
    Statement statemant = null;
    Connect connect = new Connect();
    
    public CustomerManager(){
    connection = connect.getConnection();
    } 
    
    
    @Override
    public void insert(Customer customer){
    String sql = "INSERT INTO customer(firstName, lastName, phone) values(?,?,?)";
    try{
    
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, customer.getFirstName());
    preparedStatement.setString(2, customer.getLastName());
    preparedStatement.setString(3, customer.getPhone());
    preparedStatement.executeUpdate();
    
    }catch(SQLException e){
    e.printStackTrace();
    }
    
    
    }
    
    @Override
    public void edit(int id, String firstName, String lastName, String phone){
    String sql = "UPDATE customer set firstName = ?, lastName = ?, phone = ? where id = ?";
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, firstName);
        preparedStatement.setNString(2, lastName);
        preparedStatement.setNString(3, phone);
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
    }catch(SQLException e){
    }
    }
    
    
    @Override
    public Customer search(int id){
    String sql = "SELECT * FROM customer where id = ?";
    ResultSet resultSet;
    Customer customer;
    
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
        customer = new Customer(id,resultSet.getNString("firstName"), resultSet.getNString("lastName"), resultSet.getNString("phone"));
        return customer;
        }
        
    }catch(SQLException e){
    }
    return null;
    }
    
    
    @Override
    public void delete(int id){
    String sql = "DELETE FROM customer where id = ?";
    try{
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    }catch(SQLException e){
    }
    }
    
    
    @Override
    public List<Customer> getAll(){
        List<Customer> list = new ArrayList<Customer>();
        Customer customer;
        ResultSet resultSet;
        String sql = "SELECT * FROM customer";
        
        try{
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         resultSet = preparedStatement.executeQuery();
         while(resultSet.next()){
         customer = new Customer(resultSet.getInt("id"), resultSet.getNString("firstName"), resultSet.getNString("lastName"), resultSet.getNString("phone"));
         list.add(customer);
         }   
        }catch(SQLException e){
        }
        
        
        if(list.size() == 0)
            return null;
        return list;
        
    
    }
    
    
    
    
}
