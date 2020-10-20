
package order.lineOrder;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OrderInfo {
    
    

private int orderId;
private int productId;
private String productName;
private int quantity;
private float subTotal;

   public OrderInfo(int productId, String productName, int quantity, float subTotal, int orderId){
       this.productId = productId;
       this.productName = productName;
       this.quantity = quantity;
       this.subTotal = subTotal;
       this.orderId = orderId;
       
}

    public int getOrderId() {
        return orderId;
    }
    
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }  

    public float getSubTotal() {
        return subTotal;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    


}
