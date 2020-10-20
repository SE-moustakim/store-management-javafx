
package customer;

import java.util.List;

public interface DaoCustomerManager {

public void insert(Customer customer);
public void edit(int id, String firstName, String lastName, String phone);
public Customer search(int id);
public void delete(int id);
public List<Customer> getAll();
    
}
