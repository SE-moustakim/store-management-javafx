
package product;

import java.util.List;


public interface DaoProductManager {

public void insert(Product product);
public void edit(int id,String name, float price, String addDate,int categoryId);
public Product search(int id);
public void delete(int id);
public List<Product> getAll();
 public List<Product> getAllByCategory(int categoryId);
    
}
