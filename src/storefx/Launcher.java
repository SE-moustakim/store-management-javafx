
package storefx;

import customer.CustomerDisplay;
import javafx.application.Application;
import order.OrderDisplay;
import payment.Payment;
import payment.PaymentDisplay;
import payment.PaymentManager;
import product.ProductDisplay;




public class Launcher{

    
    
    public static void main(String[] args) {
        Application.launch(OrderDisplay.class, args);
    }
    
    
   

}
