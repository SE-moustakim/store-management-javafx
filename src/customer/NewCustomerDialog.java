
package customer;

import alert.NewAlert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class NewCustomerDialog extends Dialog {
    
    
    CustomerManager customerManager = new CustomerManager();
    NewAlert alert = new NewAlert();

    Pane pane = new Pane();
    Label customerImage = new Label("No image Selected");
    Label FnameLabel = new Label("First Name");
    Label LnameLabel = new Label("Last Name");
    Label phoneLabel = new Label("Phone");
    TextField FnameField = new TextField();
    TextField LnameField = new TextField();
    TextField phoneField = new TextField();
    Button chooseImageButton = new Button("choose Image", new ImageView(new Image(getClass().getResourceAsStream("/icons/add-image.png"))));
    Button addButton = new Button("Save", new ImageView(new Image(getClass().getResourceAsStream("/icons/add-product.png"))));
    
    ObservableList data;
    
    public NewCustomerDialog(ObservableList data){
    
        this.data = data;
        
        pane.setPrefSize(350, 340);
        customerImage.setPrefSize(224, 224);
        FnameLabel.setPrefSize(80, 40);
        FnameField.setPrefSize(250, 40);
        LnameLabel.setPrefSize(80, 40);
        LnameField.setPrefSize(250, 40);
        phoneLabel.setPrefSize(80, 40);
        phoneField.setPrefSize(250, 40);
        chooseImageButton.setPrefSize(224, 35);
        addButton.setPrefSize(250, 35);
        
        customerImage.setTranslateX(36);
        customerImage.setTranslateY(40);
        
        FnameLabel.setTranslateX(50);
        FnameLabel.setTranslateY(30);
        FnameField.setTranslateX(50);
        FnameField.setTranslateY(65);
        LnameLabel.setTranslateX(50);
        LnameLabel.setTranslateY(110);
        LnameField.setTranslateX(50);
        LnameField.setTranslateY(145);
        phoneLabel.setTranslateX(50);
        phoneLabel.setTranslateY(190);
        phoneField.setTranslateX(50);
        phoneField.setTranslateY(225);
        addButton.setTranslateX(50);
        addButton.setTranslateY(280);
        
        customerImage.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        customerImage.setAlignment(Pos.CENTER);
        
        //pane.getChildren().add(customerImage);
        //pane.getChildren().add(chooseImageButton);
        pane.getChildren().add(FnameLabel);
        pane.getChildren().add(LnameLabel);
        pane.getChildren().add(phoneLabel);
        pane.getChildren().add(FnameField);
        pane.getChildren().add(LnameField);
        pane.getChildren().add(phoneField);
        pane.getChildren().add(addButton);
        
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        this.setTitle("Add New Customer");
        this.getDialogPane().setContent(pane);
    
        FnameField.requestFocus();
        
        addButton.setOnAction(Action -> {createCustomer();
        });
    }
    
     public void display(boolean value)
    {
        if(value == true) {this.showAndWait();}
        else {this.hide();}
    }
     
     
     public void createCustomer(){
     if(checkInputs()){
     alert.show("", "Are you sure to create this customer ?", Alert.AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    customerManager.insert(new Customer(0, FnameField.getText(), LnameField.getText(), phoneField.getText()));
    showCustomerInTheTable();
    clearDate();
    this.hide();
    alert.show("", "Customer created", Alert.AlertType.INFORMATION);

    }    
     
     }
     }
     
      public boolean isValid(String s) 
    { 
        Pattern p = Pattern.compile("(06|07|05)[0-9]{8}");  
        
        Matcher m = p.matcher(s); 
        return (m.find() && m.group().equals(s)); 
    } 
      
    public boolean checkInputs(){
    if(FnameField.getText().equals("")){
    alert.show("Oops!", "please enter the first name", Alert.AlertType.ERROR);
            return false;
    }
    
    else if(LnameField.getText().equals("")){
    alert.show("Oops!", "please enter the last name", Alert.AlertType.ERROR);
            return false;
    }
    
    else if(phoneField.getText().equals("")){
    alert.show("Oops!", "please enter the phone number", Alert.AlertType.ERROR);
            return false;
    }
    
    else if(!isValid(phoneField.getText())){
    alert.show("Oops!", "Please enter a valid phone number",Alert.AlertType.ERROR);
            return false;
    }
    
    return true;
    }  
     
  private void  showCustomerInTheTable(){
  data.add(customerManager.getAll().get(customerManager.getAll().size() - 1));
  }  
  
  private void clearDate(){
  FnameField.clear();
  LnameField.clear();
  phoneField.clear();
  }
     
    
}
