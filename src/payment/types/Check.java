
package payment.types;


public class Check {

private int id;

private int orderId;
private String FirstName;
private String LastName;
private float amount;
private String bank;
private String date;





public Check(int id, String FirstName, String LastName, float amount, String bank, String date){

    
     this.id = id;
     this.FirstName = FirstName;
     this.LastName = LastName;
     this.amount = amount; 
     this.bank = bank;
     this.date = date;
     
}

    public int getId() {
        return id;
    }

    

    public int getOrderId() {
        return orderId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    

    public float getAmount() {
        return amount;
    }

    public String getBank() {
        return bank;
    }
    
    

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
    
}
