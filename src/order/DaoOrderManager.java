
package order;

import java.util.List;


public interface DaoOrderManager {

public void insert(Order order);
public Order search(int id);
public List<Order> getAll();

}
