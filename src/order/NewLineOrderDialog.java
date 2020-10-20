
package order;

import alert.NewAlert;
import customer.Customer;
import customer.CustomerManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import order.lineOrder.LineOrder;
import order.lineOrder.OrderInfo;
import order.lineOrder.OrderInfoManager;
import product.Product;
import product.ProductManager;


public class NewLineOrderDialog extends Dialog {
    
    NewAlert alert = new NewAlert();
    
    CustomerManager customerManager = new CustomerManager();
    ProductManager productManager = new ProductManager();
    OrderManager orderManager = new OrderManager();
    OrderInfoManager orderInfoManager = new OrderInfoManager();
    //OrderPane orderPane = new OrderPane();
    
    TableView tableLineOrder = new TableView();
    List<LineOrder> listLineOrder = new ArrayList<LineOrder>();
    ObservableList<LineOrder> dataLineOrder = FXCollections.observableArrayList();
    
    float total = 0;
    int tva = 0;
    float totalTva = 0;
    
    Pane pane = new Pane();
    
    ComboBox<String> comboCustomer = new ComboBox<>();
    ComboBox<String> comboProduct = new ComboBox<>();
    
    ObservableList<String> dataComboCustomer = FXCollections.observableArrayList();
    ObservableList<String> dataComboProduct = FXCollections.observableArrayList();
    
    Button chooseCustomer = new Button("Select", new ImageView(new Image(getClass().getResourceAsStream("/icons/select.png"))));
    Button addLineOrderButton = new Button("Add", new ImageView(new Image(getClass().getResourceAsStream("/icons/add-product.png"))));
    Button removeLineOrderButton = new Button("Remove", new ImageView(new Image(getClass().getResourceAsStream("/icons/deleteLine.png"))));
    Button saveOrderButton = new Button("Save", new ImageView(new Image(getClass().getResourceAsStream("/icons/save.png"))));
    Button totalButton = new Button("total", new ImageView(new Image(getClass().getResourceAsStream("/icons/sum.png"))));
    
    Label orderFor = new Label("Order For");
    Label idCustomerLabel = new Label("ID");
    Label fNameCustomerLabel = new Label("F Name");
    Label lNameCustomerLabel = new Label("L Name");
    Label phoneCustomerLabel = new Label("Phone");
    Label productLabel = new Label("Product");
    Label quantityLabel = new Label("Quantity");
    Label totalLabel = new Label("Net Total");
    Label totalVatLabel = new Label("VAT Total");
    Label tvaLabel = new Label("VAT");
    TextField quantityField = new TextField();
    TextField idCustomerField = new TextField();
    TextField fNameCustomerField = new TextField();
    TextField lNameCustomerField = new TextField();
    TextField phoneCustomerField = new TextField();
    TextField totalField = new TextField();
    TextField totalVatField = new TextField();
    TextField tvaField = new TextField();
    
    
    TableColumn<LineOrder, Integer> columnProductId = new TableColumn("Poduct ID");
    TableColumn<LineOrder, Integer> columnQuantity = new TableColumn("Quantity");
    
    
    public NewLineOrderDialog(){
        
        
        tableLineOrder.getColumns().addAll(columnProductId, columnQuantity);
        tableLineOrder.setItems(dataLineOrder);
        columnProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
    // تم إضافته في الجدول Product الموجودة في كل كائن name سيُعرض فيه قيم الـ columnName هنا قلنا أن العامود
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableLineOrder.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    pane.setPrefSize(800, 500);
    comboCustomer.setPrefSize(200, 40);
    chooseCustomer.setPrefSize(200, 40);
    orderFor.setPrefSize(80, 40);
    orderFor.setFont(Font.font("Arial", FontWeight.BOLD,10));
    idCustomerLabel.setPrefSize(50, 40);
    idCustomerField.setPrefSize(200, 40);
    fNameCustomerLabel.setPrefSize(50, 40);
    fNameCustomerField.setPrefSize(200, 40);
    lNameCustomerLabel.setPrefSize(50, 40);
    lNameCustomerField.setPrefSize(200, 40);
    phoneCustomerLabel.setPrefSize(50, 40);
    phoneCustomerField.setPrefSize(200, 40);
    tableLineOrder.setPrefSize(270, 290);
    productLabel.setPrefSize(50, 40);
    productLabel.setFont(Font.font("Arial", FontWeight.BOLD,10));
    quantityLabel.setPrefSize(50, 40);
    quantityLabel.setFont(Font.font("Arial", FontWeight.BOLD,10));
    quantityField.setPrefSize(150, 40);
    addLineOrderButton.setPrefSize(150, 30);
    removeLineOrderButton.setPrefSize(150, 30);
    saveOrderButton.setPrefSize(150, 40);
    comboProduct.setPrefSize(150, 40);
    totalButton.setPrefSize(80, 30);
    totalButton.setFont(Font.font("Arial", FontWeight.BOLD,10));
    totalLabel.setPrefSize(80, 30);
    totalLabel.setFont(Font.font("Arial", FontWeight.BOLD,10));
    totalField.setPrefSize(80, 30);
    totalField.setFont(Font.font("Arial", FontWeight.BOLD,12));
    totalVatField.setPrefSize(80, 30);
    totalVatField.setFont(Font.font("Arial", FontWeight.BOLD,12));
    totalVatLabel.setPrefSize(80, 30);
    totalVatLabel.setFont(Font.font("Arial", FontWeight.BOLD,10));
    tvaField.setPrefSize(50, 30);
    tvaLabel.setPrefSize(40, 30);
    tvaLabel.setFont(Font.font("Arial", FontWeight.BOLD,10));    
    
    orderFor.setTranslateX(20);
    orderFor.setTranslateY(70);
    comboCustomer.setTranslateX(80);
    comboCustomer.setTranslateY(70);
    chooseCustomer.setTranslateX(80);
    chooseCustomer.setTranslateY(20);
    
    idCustomerLabel.setTranslateX(20);
    idCustomerLabel.setTranslateY(120);
    idCustomerField.setTranslateX(80);
    idCustomerField.setTranslateY(120);
    fNameCustomerLabel.setTranslateX(20);
    fNameCustomerLabel.setTranslateY(170);
    fNameCustomerField.setTranslateX(80);
    fNameCustomerField.setTranslateY(170);
    lNameCustomerLabel.setTranslateX(20);
    lNameCustomerLabel.setTranslateY(220);
    lNameCustomerField.setTranslateX(80);
    lNameCustomerField.setTranslateY(220);
    phoneCustomerLabel.setTranslateX(20);
    phoneCustomerLabel.setTranslateY(270);
    phoneCustomerField.setTranslateX(80);
    phoneCustomerField.setTranslateY(270);
    chooseCustomer.setTranslateX(80);
    chooseCustomer.setTranslateY(320);
    tableLineOrder.setTranslateX(300);
    tableLineOrder.setTranslateY(70);
    totalButton.setTranslateX(490);
    totalButton.setTranslateY(440);
    totalLabel.setTranslateX(430);
    totalLabel.setTranslateY(370);
    totalField.setTranslateX(490);
    totalField.setTranslateY(370);
    totalVatLabel.setTranslateX(430);
    totalVatLabel.setTranslateY(405);
    totalVatField.setTranslateX(490);
    totalVatField.setTranslateY(405);
    tvaField.setTranslateX(330);
    tvaField.setTranslateY(370);
    tvaLabel.setTranslateX(300);
    tvaLabel.setTranslateY(370);
    
    productLabel.setTranslateX(580);
    productLabel.setTranslateY(100);
    comboProduct.setTranslateX(630);
    comboProduct.setTranslateY(100);
    quantityLabel.setTranslateX(580);
    quantityLabel.setTranslateY(150);
    quantityField.setTranslateX(630);
    quantityField.setTranslateY(150);
    addLineOrderButton.setTranslateX(630);
    addLineOrderButton.setTranslateY(200);
    removeLineOrderButton.setTranslateX(630);
    removeLineOrderButton.setTranslateY(235);
    saveOrderButton.setTranslateX(630);
    saveOrderButton.setTranslateY(320);
    
    idCustomerField.setEditable(false);
    fNameCustomerField.setEditable(false);
    lNameCustomerField.setEditable(false);
    phoneCustomerField.setEditable(false);
    
    idCustomerField.setStyle("-fx-background-color: #ececec;");
    fNameCustomerField.setStyle("-fx-background-color: #ececec;");
    lNameCustomerField.setStyle("-fx-background-color: #ececec;");
    phoneCustomerField.setStyle("-fx-background-color: #ececec;");
    totalField.setStyle("-fx-background-color: #ececec;");
    totalField.setEditable(false);
    totalVatField.setStyle("-fx-background-color: #ececec;");
    totalVatField.setEditable(false);
    totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        this.setTitle("New Order");
        
    
        
    comboCustomer.setItems(dataComboCustomer);
    comboProduct.setItems(dataComboProduct);
    //pane.getChildren().add(comboCustomer);
    //pane.getChildren().add(comboProduct);
    pane.getChildren().add(chooseCustomer);
    pane.getChildren().add(orderFor);
    pane.getChildren().add(idCustomerLabel);
    pane.getChildren().add(idCustomerField);
    pane.getChildren().add(fNameCustomerField);
    pane.getChildren().add(lNameCustomerField);
    pane.getChildren().add(fNameCustomerLabel);
    pane.getChildren().add(lNameCustomerLabel);
    pane.getChildren().add(phoneCustomerLabel);
    pane.getChildren().add(phoneCustomerField);
    pane.getChildren().add(tableLineOrder);
    pane.getChildren().add(productLabel);
    pane.getChildren().add(comboProduct);
    pane.getChildren().add(quantityLabel);
    pane.getChildren().add(quantityField);
    pane.getChildren().add(totalButton);
    pane.getChildren().add(totalField);
    pane.getChildren().add(totalLabel);
    pane.getChildren().add(totalVatField);
    pane.getChildren().add(totalVatLabel);
    pane.getChildren().add(tvaField);
    pane.getChildren().add(tvaLabel);
    pane.getChildren().add(addLineOrderButton);
    pane.getChildren().add(removeLineOrderButton);
    pane.getChildren().add(saveOrderButton);
    this.getDialogPane().setContent(pane);
    
   fillTheComboCustomer();
   fillTheComboProduct();
   //showCustomer();
   
   addLineOrderButton.setOnAction(Action -> {addNewLineOrder();
   fillThetable();
   });
   removeLineOrderButton.setOnAction(Action -> {removeLineOrder();});
   totalButton.setOnAction(Action -> {
       
       if(checkTva()){
       totalTva = total + total * tva / 100;
       totalVatField.setText(totalTva + "");
       totalField.setText(total + "");
       }
       });
   
   
   
   saveOrderButton.setOnAction(action -> {createLineOrders();});
     
   
    }
    
    public void display(boolean value)
    {
        if(value == true) {
            
            this.showAndWait();
        }
        else {
            this.hide();
        }
    }
    
    public void fillTheComboCustomer(){
    for(int i = 0; i < customerManager.getAll().size(); i++){
    dataComboCustomer.add(customerManager.getAll().get(i).getFirstName()+" "+customerManager.getAll().get(i).getLastName());
    }
    
  }
    
    public void fillTheComboProduct(){
    for(int i = 0; i < productManager.getAll().size(); i++){
    dataComboProduct.add(productManager.getAll().get(i).getName());
    }
    }
/*    
    public void showCustomer(){
    
    
    idCustomerField.setText(customerManager.search(orderManager.getAll().get(orderPane.getSelectedOrderIndex()).getCustomerId()).getId()+ "");
    fNameCustomerField.setText(customerManager.search(orderManager.getAll().get(orderPane.getSelectedOrderIndex()).getCustomerId()).getFirstName()+ "");
    lNameCustomerField.setText(customerManager.search(orderManager.getAll().get(orderPane.getSelectedOrderIndex()).getCustomerId()).getLastName()+ "");
    phoneCustomerField.setText(customerManager.search(orderManager.getAll().get(orderPane.getSelectedOrderIndex()).getCustomerId()).getPhone()+ "");
        
    
    
    }
*/    
    public void addNewLineOrder(){
    if(isProductSelected()){
    LineOrder l;
    int quantity = Integer.parseInt(quantityField.getText());
    int index = comboProduct.getSelectionModel().getSelectedIndex();
    int productId = productManager.getAll().get(index).getId();
    l = new LineOrder(productId, quantity);
    if(!isLineOrderDuplicate(listLineOrder, l))
    listLineOrder.add(l);
    total = 0;
    quantityField.clear();
    for(int i = 0; i <listLineOrder.size(); i++){
    total = total + listLineOrder.get(i).getQuantity() * productManager.search(listLineOrder.get(i).getProductId()).getPrice();
    }
    }    
    
    }
    
    public void removeLineOrder(){
    if(tableLineOrder.getSelectionModel().getSelectedItem()!= null){
    listLineOrder.remove(tableLineOrder.getSelectionModel().getSelectedIndex());
    total = 0;
    for(int i = 0; i <listLineOrder.size(); i++){
    total = total + listLineOrder.get(i).getQuantity() * productManager.search(listLineOrder.get(i).getProductId()).getPrice();
    }
    fillThetable();
    }    
    
    }
    
    public void fillThetable(){
    dataLineOrder.clear();
    for(int i = 0; i < listLineOrder.size(); i ++){
    dataLineOrder.add(listLineOrder.get(i));
    }
    }
    
    public void createLineOrders(){
        if(isCustomerSelected() && isListNotNull()){
      
   for(int i = 0; i < listLineOrder.size(); i++){
   int productId = productManager.search(listLineOrder.get(i).getProductId()).getId();
   String productName = productManager.search(listLineOrder.get(i).getProductId()).getName();
   float subTotal = productManager.search(listLineOrder.get(i).getProductId()).getPrice() * listLineOrder.get(i).getQuantity(); 
   orderInfoManager.insert(new OrderInfo(productId, productName, listLineOrder.get(i).getQuantity(),subTotal, orderManager.getLastId()));
   }
   this.hide();
    
        }
        
}
    
    
    
    public boolean isCustomerSelected(){
    if(comboCustomer.getSelectionModel().getSelectedItem() == null){
    alert.show("Oops!", "please choose a customer!", Alert.AlertType.INFORMATION);
       return false;
      
    }
    return true;
    }
    
    public boolean isProductSelected(){
    if(comboProduct.getSelectionModel().getSelectedItem() == null){
    alert.show("Oops!", "please choose a product!", Alert.AlertType.INFORMATION);
       return false;
      
    }
    
    else if(quantityField.getText().equals("")){
    alert.show("Oops!", "please enter the quantity!", Alert.AlertType.INFORMATION);
       return false;
    }
    
     try {
            int quantity = Integer.parseInt(quantityField.getText());
            if(quantity <= 0){
            alert.show("Oops!", "the quantity cannot equal 0 !", Alert.AlertType.INFORMATION);
            return false;
            }
                
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "Quantity should be a natural number (10, 20)", Alert.AlertType.ERROR);
            return false;
        }
    
    }
    
    public boolean isListNotNull(){
    if(listLineOrder.size() == 0){
    alert.show("Oops!", "the list of line orders is empty !", Alert.AlertType.ERROR);
        return false;
    }
    return true;
    }
    
    public boolean checkTva(){
    if(tvaField.getText().equals("")){
    tva = 0;
    tvaField.setText("0");
    return true;
    }
    
    try {
            tva = Integer.parseInt(tvaField.getText());
            if(tva < 0 || tva > 100){
            alert.show("Oops!", "the TVA cannot should be negative", Alert.AlertType.INFORMATION);
            return false;
            }
                
            System.out.print(tva);
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "TVA should be a natural number (10, 20)", Alert.AlertType.ERROR);
            return false;
        }
    }
    
 
    public boolean isLineOrderDuplicate(List<LineOrder> list, LineOrder lineOrder){
    for(int i = 0; i < list.size(); i++){
    if(list.get(i).getProductId() == lineOrder.getProductId()){
    list.get(i).setQuantity(lineOrder.getQuantity() + list.get(i).getQuantity());
    System.out.println("line duplicate !");
    return true;
    }
    }
    return false;
    }
   
    
}
