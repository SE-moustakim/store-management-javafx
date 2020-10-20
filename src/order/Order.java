
package order;

import order.lineOrder.LineOrder;
import customer.Customer;
import java.util.List;


public class Order {

     private int id;
    List<LineOrder> listLineOrder;
    private int CustomerId;
    private String description;
    private float total;
    private String date;
    
    
    public Order(int id, int CustomerId, String description, float total, String date){
        this.id = id;
        
        this.CustomerId = CustomerId;
        this.description = description;
        this.total = total;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public List<LineOrder> getListLineOrder() {
        return listLineOrder;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getDescription() {
        return description;
    }
    
    public float getTotal(){
    return total;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListLineOrder(List<LineOrder> listLineOrder) {
        this.listLineOrder = listLineOrder;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
    
    
    
    
    
    
    
    
}
