
package product;


public class Product {
    
    private int id;
    private String name;
    private float price;
    private byte[] image;
    private String addDate;
    private int categoryId;
    
    public Product(int id, String name, float price, String addDate, int categoryId){
    this.id = id;
    this.name = name;
    this.price = price;
    this.addDate = addDate;
    this.categoryId = categoryId;
    }
    
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public String getAddDate() {
        return addDate;
    }
    
    public int getCategoryId(){
    return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    
    
    
    
}
