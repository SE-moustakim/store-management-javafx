
package order.lineOrder;

import product.Product;


public class LineOrder {

int productId;
int quantity;


public LineOrder(int productId, int quantity){

     this.productId = productId;
     this.quantity = quantity;
     
}

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    



}
