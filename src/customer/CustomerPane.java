
package customer;

import alert.NewAlert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import product.Product;


public class CustomerPane extends Pane {
    
    CustomerManager customerManager = new CustomerManager();
    
    NewAlert alert = new NewAlert();
    
    VBox vboxLabel = new VBox();
    VBox vboxField = new VBox();
    
    TableView table = new TableView();
    TableColumn<Customer, Integer> columnId = new TableColumn("ID");
    TableColumn<Customer, String> columnFName = new TableColumn("First Name");
    TableColumn<Customer, String> columnLName = new TableColumn("Last Name");
    TableColumn<Customer, String> columnPhone = new TableColumn("Phone");
    Label customerImage = new Label("No image found");
    Label idLabel = new Label("ID");
    Label FNameLabel = new Label("Firt Name");
    Label LNameLabel = new Label("Last Name");
    Label phoneLabel = new Label("Phone");
    Label mailLabel = new Label("Email");
    Label searchLabel = new Label("Search");
    TextField idField = new TextField();
    TextField FNameField = new TextField();
    TextField LNameField = new TextField();
    TextField phoneField = new TextField();
    TextField mailField = new TextField();
    TextField searchField = new TextField();
    Button updateImageButton = new Button("Update Image");
    Button insertButton = new Button("Add New", new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png"))));
    Button refreshButton = new Button("Refrech", new ImageView(new Image(getClass().getResourceAsStream("/icons/refresh.png"))));
    Button updateButton = new Button("Update", new ImageView(new Image(getClass().getResourceAsStream("/icons/update1.png"))));
    Button deleteButton = new Button("Delete", new ImageView(new Image(getClass().getResourceAsStream("/icons/cancel.png"))));
    Button exitButton = new Button("Exit", new ImageView(new Image(getClass().getResourceAsStream("/icons/quit.png"))));
    
    
    ObservableList<Customer> data = FXCollections.observableArrayList();
    NewCustomerDialog cutomerDialog = new NewCustomerDialog(data);
    public CustomerPane(){
   
        table.getColumns().addAll(columnId, columnFName, columnLName, columnPhone);
        table.setItems(data);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        customerImage.setPrefSize(270, 244);
        updateImageButton.setPrefSize(130, 35);
        idLabel.setPrefSize(50, 40);
        idField.setPrefSize(270, 40);
        FNameLabel.setPrefSize(80, 40);
        FNameField.setPrefSize(270, 40);
        LNameLabel.setPrefSize(80, 40);
        LNameField.setPrefSize(270, 40);
        phoneLabel.setPrefSize(50, 40);
        phoneField.setPrefSize(270, 40);
        mailLabel.setPrefSize(50, 40);
        mailField.setPrefSize(270, 40);
        deleteButton.setPrefSize(130, 30);
        updateButton.setPrefSize(130, 30);
        table.setPrefSize(480, 488);
        searchField.setPrefSize(255, 36);
        searchLabel.setPrefSize(115, 40);
        insertButton.setPrefSize(100, 30);
        refreshButton.setPrefSize(130, 40);
        exitButton.setPrefSize(130, 30);
        
        
        customerImage.setTranslateX(80);
        customerImage.setTranslateY(40);
        updateImageButton.setTranslateX(150);
        updateImageButton.setTranslateY(295);
        deleteButton.setTranslateX(710);
        deleteButton.setTranslateY(350);
        updateButton.setTranslateX(850);
        updateButton.setTranslateY(350);
        table.setTranslateX(120);
        table.setTranslateY(20);
        vboxLabel.setTranslateX(640);
        vboxLabel.setTranslateY(100);
        vboxField.setTranslateX(710);
        vboxField.setTranslateY(100);
        searchLabel.setTranslateX(170);
        searchLabel.setTranslateY(530);
        searchField.setTranslateX(220);
        searchField.setTranslateY(530);
        insertButton.setTranslateX(10);
        insertButton.setTranslateY(45);
        refreshButton.setTranslateX(920);
        refreshButton.setTranslateY(90);
        exitButton.setTranslateX(770);
        exitButton.setTranslateY(478);
        
        
        
        idField.setEditable(false);
        mailField.setEditable(false);
         
        
        customerImage.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        customerImage.setAlignment(Pos.CENTER);
        idField.setEditable(false);
        
        vboxLabel.setSpacing(10);
        vboxField.setSpacing(10);         
        
        
        vboxLabel.getChildren().addAll(idLabel, FNameLabel, LNameLabel, phoneLabel, mailLabel);
        vboxField.getChildren().addAll(idField, FNameField, LNameField, phoneField, mailField);
        
        this.getChildren().add(table);
        //this.getChildren().add(customerImage);
        //this.getChildren().add(updateImageButton);
        
        this.getChildren().addAll(vboxLabel, vboxField);
        
        this.getChildren().add(insertButton);
        //this.getChildren().add(refreshButton);
        this.getChildren().add(updateButton);
        this.getChildren().add(deleteButton);
        this.getChildren().add(searchField);
        this.getChildren().add(searchLabel);
        this.getChildren().add(exitButton);
        
        
        showCustomersInTheTable();
        showSelectedCustomer();
        showCustomer(0);
        
        insertButton.setOnAction(Action -> {cutomerDialog.display(true);});
        refreshButton.setOnAction(Action -> {showCustomersInTheTable();});
        updateButton.setOnAction(Action -> {updateCustomer();});
        deleteButton.setOnAction( Action -> {deleteCustomer();});
        exitButton.setOnAction(Action -> {System.exit(0);});
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            search();
        });
        
    }
    
    private void showCustomersInTheTable(){
    data.clear();
    for(int i = 0; i < customerManager.getAll().size(); i++){
    data.add(customerManager.getAll().get(i));
    }
    }
    
    private void showCustomer(int index){
    idField.setText(data.get(index).getId() + "");
    FNameField.setText(data.get(index).getFirstName() + "");
    LNameField.setText(data.get(index).getLastName() + "");
    phoneField.setText(data.get(index).getPhone() + "");
   }
    
    private void showSelectedCustomer(){
    table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showCustomer(table.getSelectionModel().getSelectedIndex());
            }
        });
    }
    
    private boolean isCustomerSelected(){
    if(table.getSelectionModel().getSelectedItem() == null)
        {
            alert.show("Oops!", "Select the customer that you want to update", Alert.AlertType.INFORMATION);
            return false;
        }
    return true;
    }
    
    private void updateCustomer(){
        if(isCustomerSelected() && checkInputs()){
        Customer selectedCustomer = (Customer) table.getSelectionModel().getSelectedItem();
        alert.show("", "Are you sure to update the customer ?", Alert.AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    customerManager.edit(Integer.parseInt(idField.getText()), FNameField.getText(), LNameField.getText(), phoneField.getText());
    //showCustomersInTheTable();
    selectedCustomer.setFirstName(FNameField.getText());
    selectedCustomer.setLastName(LNameField.getText());
    selectedCustomer.setPhone(phoneField.getText());
    table.refresh();
    alert.show("done", "Customer updated!", Alert.AlertType.INFORMATION);
    }    
        
        }
    
    }
    
    private void deleteCustomer(){
    if(isCustomerSelected()){
    alert.show("", "Are you sure to delete the customer ?", Alert.AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    customerManager.delete(Integer.parseInt(idField.getText()));
    //showCustomersInTheTable();
    data.remove(table.getSelectionModel().getSelectedItem());
    table.refresh();
    
        alert.show("", "Customer deleted !", Alert.AlertType.INFORMATION);

    }    
    
    }
    }
    
    private boolean isValid(String s) 
    { 
        Pattern p = Pattern.compile("(06|07|05)[0-9]{8}");  
        
        Matcher m = p.matcher(s); 
        return (m.find() && m.group().equals(s)); 
    } 
    
    private boolean checkInputs(){
    if(FNameField.getText().equals("")){
    alert.show("Oops!", "please enter the first name", Alert.AlertType.ERROR);
            return false;
    }
    
    else if(LNameField.getText().equals("")){
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
    private void search()
    {
        
        String keyword = searchField.getText();
        
        if (keyword.equals("")) {
            table.setItems(data);
        }
        
        else {
            ObservableList<Customer> filteredData = FXCollections.observableArrayList();
            for (Customer customer : data) {
                if(customer.getFirstName().contains(keyword) || customer.getLastName().contains(keyword))
                    filteredData.add(customer);
            }
            table.setItems(filteredData);
        }
    }
    
    
    
    
}
