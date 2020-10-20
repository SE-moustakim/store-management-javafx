
package payment;

import java.util.List;



public interface DaoPaymentManager {

    public void insert(Payment payment);
    public int getLastNum(int saleId);
    public List<Payment> getAllBySaleId(int saleId);
    public double getTotalPayment(int saleId);
    
    
}
