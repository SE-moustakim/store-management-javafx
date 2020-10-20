
package order;

import alert.NewAlert;
import customer.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import order.lineOrder.LineOrderManager;
import order.lineOrder.OrderInfo;
import order.lineOrder.OrderInfoManager;
import product.ProductManager;


public class OrderPane extends Pane {
    
    OrderManager orderManager = new OrderManager();
    LineOrderManager lineOrderManager = new LineOrderManager();
    OrderInfoManager orderInfoManager = new OrderInfoManager();
    ProductManager productManager = new ProductManager();
    CustomerManager customerManager = new CustomerManager();
    NewLineOrderDialog lineOrderDialog = new NewLineOrderDialog();
    NewAlert alert = new NewAlert();
    
    VBox vboxLabel = new VBox();
    VBox vboxField = new VBox();
    
    
    TableView tableOrder = new TableView();
    TableView tableLineOrder = new TableView();
    
    ComboBox<String> comboCustomer = new ComboBox<>();
    ObservableList<String> dataComboCustomer = FXCollections.observableArrayList();
    
    
    TableColumn<Order, Integer> columnOrderId = new TableColumn("Order ID");
    TableColumn<Order, Integer> columnCustomerId = new TableColumn("Customer ID");
    TableColumn<Order, String> columnDescription = new TableColumn("Description");
    TableColumn<Order, Float> columnTotal = new TableColumn("Total");
    TableColumn<Order, String> columnDate = new TableColumn("Date");
    
    TableColumn<OrderInfo, String> columnProductName = new TableColumn("Poduct Name");
    TableColumn<OrderInfo, Integer> columnQuantity = new TableColumn("Quantity");
    //TableColumn columnProductName = new TableColumn("Name");
    
    
    Label idLabel = new Label("ID");
    Label nameLabel = new Label("Name");
    Label priceLabel = new Label("Price");
    Label quantityLabel = new Label("Quantity");
    Label searchLabel = new Label("Search");
    Label customerLabel = new Label("Customer");
    Label subTotalLabel = new Label("SubTotal");
    Label subTotalField = new Label("");
    Label orderForLabel = new Label("Orders For");
    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField priceField = new TextField();
    TextField quantityField = new TextField();
    TextField searchField = new TextField();
    TextField customerField = new TextField();
    
    Button insertButton = new Button("Add Order", new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png"))));
    Button refreshButton = new Button("Refrech", new ImageView(new Image(getClass().getResourceAsStream("/icons/refresh.png"))));
    Button chooseCustomer = new Button("search", new ImageView(new Image(getClass().getResourceAsStream("/icons/search1.png"))));
    Button updateButton = new Button("Update", new ImageView(new Image(getClass().getResourceAsStream("/icons/update1.png"))));
    Button addLineOrderButton = new Button("Add");
    Button deleteButton = new Button("Delete", new ImageView(new Image(getClass().getResourceAsStream("/icons/cancel.png"))));
    Button exitButton = new Button("Exit", new ImageView(new Image(getClass().getResourceAsStream("/icons/exit.png"))));
    
    ObservableList<Order> dataOrder = FXCollections.observableArrayList();
    NewOrderDialog orderDialog = new NewOrderDialog(dataOrder);
    ObservableList<OrderInfo> dataLineOrder = FXCollections.observableArrayList();
    
    private int selectedOrderIndex = 0;
    
    public OrderPane(){
    // table هنا وضعنا الأعمدة الأربعة في الكائن
        tableOrder.getColumns().addAll(columnOrderId, columnCustomerId, columnDescription, columnTotal, columnDate);
    // table سيتم عرضها في الكائن data هنا قمنا بتحديد أن البيانات التي ستكون موجودة في الكائن
        tableOrder.setItems(dataOrder);
    // تم إضافته في الجدول Product الموجودة في كل كائن id سيُعرض فيه قيم الـ columnId هنا قلنا أن العامود
        columnOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
    // تم إضافته في الجدول Product الموجودة في كل كائن name سيُعرض فيه قيم الـ columnName هنا قلنا أن العامود
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    // تم إضافته في الجدول Product الموجودة في كل كائن price سيُعرض فيه قيم الـ columnPrice هنا قلنا أن العامود
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    // هنا قمنا بجعل حجم أعمدة الجدول ينقسم بالتساوي على عددهم مع إبقاء المستخدم قادر على تعديل أحجامهم بواسطة الفأرة
        tableOrder.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    
    // table هنا وضعنا الأعمدة الأربعة في الكائن
        tableLineOrder.getColumns().addAll(columnProductName, columnQuantity);
    // table سيتم عرضها في الكائن data هنا قمنا بتحديد أن البيانات التي ستكون موجودة في الكائن
        tableLineOrder.setItems(dataLineOrder);
    // تم إضافته في الجدول Product الموجودة في كل كائن id سيُعرض فيه قيم الـ columnId هنا قلنا أن العامود
        columnProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
    // تم إضافته في الجدول Product الموجودة في كل كائن name سيُعرض فيه قيم الـ columnName هنا قلنا أن العامود
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    // هنا قمنا بجعل حجم أعمدة الجدول ينقسم بالتساوي على عددهم مع إبقاء المستخدم قادر على تعديل أحجامهم بواسطة الفأرة
        tableLineOrder.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        comboCustomer.setItems(dataComboCustomer);

        
        // AllProductsPane هنا قمنا بتحديد حجم كل شيء سيتم إضافته في الحاوية التي يمثلها الكائن الذي ننشئه من الكلاس
        
        customerLabel.setPrefSize(60, 40);
        customerField.setPrefSize(150, 30);
        idLabel.setPrefSize(50, 40);
        idField.setPrefSize(100, 40);
        nameLabel.setPrefSize(50, 40);
        nameField.setPrefSize(270, 40);
        priceLabel.setPrefSize(50, 40);
        priceField.setPrefSize(270, 40);
        quantityLabel.setPrefSize(50, 40);
        quantityField.setPrefSize(200, 40);
        deleteButton.setPrefSize(130, 30);
        updateButton.setPrefSize(130, 30);
        addLineOrderButton.setPrefSize(130, 40);
        tableOrder.setPrefSize(520, 505);
        tableLineOrder.setPrefSize(270, 244);
        subTotalLabel.setPrefSize(50, 40);
        subTotalField.setPrefSize(50, 40);
        searchField.setPrefSize(255, 36);
        searchLabel.setPrefSize(115, 40);
        insertButton.setPrefSize(130, 30);
        refreshButton.setPrefSize(130, 40);
        orderForLabel.setPrefSize(80, 40);
        orderForLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        comboCustomer.setPrefSize(130, 30);
        chooseCustomer.setPrefSize(130, 20);
        exitButton.setPrefSize(130, 40);
        
        // AllProductsPane هنا قمنا بتحديد مكان ظهور كل شيء سيتم إضافته في الحاوية التي يمثلها الكائن الذي ننشئه من الكلاس
        
        deleteButton.setTranslateX(770);
        deleteButton.setTranslateY(575);
        //addLineOrderButton.setTranslateX(20);
        //addLineOrderButton.setTranslateY(575);
        updateButton.setTranslateX(900);
        updateButton.setTranslateY(575);
        tableOrder.setTranslateX(150);
        tableOrder.setTranslateY(40);
        customerLabel.setTranslateX(650);
        customerLabel.setTranslateY(40);
        customerField.setTranslateX(830);
        customerField.setTranslateY(40);
        
        tableLineOrder.setTranslateX(770);
        tableLineOrder.setTranslateY(81);
        subTotalLabel.setTranslateX(770);
        subTotalLabel.setTranslateY(320);
        subTotalField.setTranslateX(880);
        subTotalField.setTranslateY(320);
        vboxLabel.setTranslateX(720);
        vboxLabel.setTranslateY(350);
        vboxField.setTranslateX(770);
        vboxField.setTranslateY(350);
        searchLabel.setTranslateX(260);
        searchLabel.setTranslateY(575);
        searchField.setTranslateX(330);
        searchField.setTranslateY(577);
        insertButton.setTranslateX(10);
        insertButton.setTranslateY(200);
        refreshButton.setTranslateX(920);
        refreshButton.setTranslateY(90);
        orderForLabel.setTranslateX(10);
        orderForLabel.setTranslateY(40);
        comboCustomer.setTranslateX(10);
        comboCustomer.setTranslateY(70);
        chooseCustomer.setTranslateX(10);
        chooseCustomer.setTranslateY(105);
        exitButton.setTranslateX(920);
        exitButton.setTranslateY(575);
        
        customerField.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        customerField.setStyle("-fx-background-color: #ececec;");
        
        idField.setEditable(false);
        nameField.setEditable(false);
        priceField.setEditable(false);
        customerField.setEditable(false);
        
        
        vboxLabel.setSpacing(10);
        vboxField.setSpacing(10); 
        
        
        vboxLabel.getChildren().addAll(idLabel, nameLabel, priceLabel, quantityLabel);
        vboxField.getChildren().addAll(idField, nameField, priceField, quantityField);
        this.getChildren().addAll(vboxLabel , vboxField);
        
        this.getChildren().add(tableOrder);
        this.getChildren().add(tableLineOrder);
        this.getChildren().add(subTotalLabel);
        this.getChildren().add(subTotalField);
        //this.getChildren().add(customerLabel);
        this.getChildren().add(customerField);
        this.getChildren().add(insertButton);
        //this.getChildren().add(refreshButton);
        this.getChildren().add(comboCustomer);
        this.getChildren().add(orderForLabel);
        this.getChildren().add(chooseCustomer);
       // this.getChildren().add(addLineOrderButton);
        this.getChildren().add(updateButton);
        this.getChildren().add(deleteButton);
        this.getChildren().add(searchField);
        this.getChildren().add(searchLabel);
        
        //this.getChildren().add(exitButton);

    showOrdersInTheTable();
    
    //showLinesOrderInTheTable(1);
   
    showSelectedOrder();
    //showLineOrder(1);
    showSelectedLineOrder();
    fillTheComboCustomer();
    insertButton.setOnAction(Action -> {orderDialog.display(true);});
    updateButton.setOnAction(Action -> {updateQuantity();});
    deleteButton.setOnAction(Action -> {deleteLineOrder();});
    //addLineOrderButton.setOnAction(Action -> {lineOrderDialog.display(true);});
    refreshButton.setOnAction(Action -> {showOrdersInTheTable();});
    searchField.textProperty().addListener((obs, oldText, newText) -> {
            search();
        });
    chooseCustomer.setOnAction(Action -> {showSelectedCustomer();});
    exitButton.setOnAction(Action -> {System.exit(0);});
//showSubTotal();
    
    //System.out.println(orderInfoManager.getAllByOrderId(16));
    }
    
    private void showOrdersInTheTable(){
    dataOrder.clear();
    for(int i = 0; i < orderManager.getAll().size(); i++){
    dataOrder.add(orderManager.getAll().get(i));
    }

    }
    
    private void showLinesOrderInTheTable(int OrderId){
    dataLineOrder.clear();
    for(int i = 0; i < orderInfoManager.getAllByOrderId(OrderId).size(); i++ ){
    dataLineOrder.add(orderInfoManager.getAllByOrderId(OrderId).get(i));
    }
    
    }
    
    
    
    
    private void showSelectedOrder(){
    tableOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //showProduct(tableOrder.getSelectionModel().getSelectedIndex());
                selectedOrderIndex = tableOrder.getSelectionModel().getSelectedIndex();
                showLinesOrderInTheTable(dataOrder.get(selectedOrderIndex).getId());
                int customerId = dataOrder.get(selectedOrderIndex).getCustomerId() ;
                customerField.setText(customerManager.search(customerId).getFirstName() + " " + customerManager.search(customerId).getLastName());
            }
        });
    }
    
    
    
    private void showLineOrder(int index)
    {
        //idField.setText(productManager.search(1).getId() + "");
        //productManager.search(5).getId();
        
       //idField.setText(productManager.search(dataLineOrder.get(index).getProductId()).getId() + ""); 
       idField.setText(productManager.search(dataLineOrder.get(index).getProductId()).getId()+ "");
       nameField.setText(productManager.search(dataLineOrder.get(index).getProductId()).getName());
       priceField.setText(productManager.search(dataLineOrder.get(index).getProductId()).getPrice() + "");
       quantityField.setText(dataLineOrder.get(index).getQuantity() + "");
       //showSubTotal();
       subTotalField.setText(dataLineOrder.get(index).getSubTotal() + "");
    
    }
    
    private void showSelectedLineOrder(){
     tableLineOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //showProduct(tableOrder.getSelectionModel().getSelectedIndex());
                showLineOrder(tableLineOrder.getSelectionModel().getSelectedIndex());
                //showSubTotal();
                //showLinesOrderInTheTable(dataOrder.get(tableOrder.getSelectionModel().getSelectedIndex()).getId());
            }
        });
    }
    
    private void showSubTotal(){
       
    subTotalField.setText(Float.parseFloat(priceField.getText()) * Integer.parseInt(quantityField.getText()) + "" );
    }
    
    private void updateQuantity(){
        if(checkInputs()){
    OrderInfo selectedLineOrder = (OrderInfo) tableLineOrder.getSelectionModel().getSelectedItem();
    Order selectedOrder = dataOrder.get(selectedOrderIndex);
    alert.show("done!", "Are you sure to update the quantity ?", Alert.AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    int productId = Integer.parseInt(idField.getText());
    int quantity = Integer.parseInt(quantityField.getText());
    float subTotal = productManager.search(productId).getPrice() * quantity;
    orderInfoManager.editQuantity(quantity, productId, dataOrder.get(selectedOrderIndex).getId());
    float updatedTotal = orderInfoManager.getTotalById(dataOrder.get(selectedOrderIndex).getId());
    orderManager.editTotal(dataOrder.get(selectedOrderIndex).getId(), updatedTotal);
    
    //System.out.println(alert.getAction().get());
    //showOrdersInTheTable();
    selectedOrder.setTotal(updatedTotal);
    subTotalField.setText(subTotal+"");
    tableOrder.refresh();
    //showSubTotal();
    //showSelectedOrder();
    //System.out.println(selectedOrderIndex);
    //showLinesOrderInTheTable(dataOrder.get(selectedOrderIndex).getId());
    selectedLineOrder.setQuantity(quantity);
    tableLineOrder.refresh();
    alert.show("done!", "Quantity updated", Alert.AlertType.INFORMATION);
    //System.out.println(selectedOrderIndex);
    }
    
        }
    
    
    }
    
    private void deleteLineOrder(){
    if(checkInputs()){
    OrderInfo selectedLineOrder = (OrderInfo) tableLineOrder.getSelectionModel().getSelectedItem();
    Order selectedOrder = dataOrder.get(selectedOrderIndex);    
    alert.show("done!", "Are you sure to delete this line order ?", Alert.AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    int productId = Integer.parseInt(idField.getText());
    orderInfoManager.delete(productId, dataOrder.get(selectedOrderIndex).getId());
    float updatedTotal = orderInfoManager.getTotalById(dataOrder.get(selectedOrderIndex).getId());
    orderManager.editTotal(dataOrder.get(selectedOrderIndex).getId(), updatedTotal);
    //showOrdersInTheTable();
    //System.out.println(dataOrder.get(selectedOrderIndex).getId());
    //System.out.println(productId);
    dataLineOrder.remove(selectedLineOrder);
    tableLineOrder.refresh();
    selectedOrder.setTotal(updatedTotal);
    tableOrder.refresh();
    alert.show("done!", "Line order deleted!", Alert.AlertType.INFORMATION);
    //showLinesOrderInTheTable(dataOrder.get(selectedOrderIndex).getId());
    }
    
    }
    }
    
    /*
    private float getTotal(int orderId){
    float total = 0;
    for(int i = 0; i < orderInfoManager.getAllByOrderId(orderId).size(); i++){
    total = total + productManager.search(orderInfoManager.getAllByOrderId(orderId).get(i).getProductId()).getPrice() * orderInfoManager.getAllByOrderId(orderId).get(i).getQuantity();
    }
    return total;
    }*/
    
    private boolean checkInputs()
    {
        if(tableLineOrder.getSelectionModel().getSelectedItem() == null){
        alert.show("Oops!", "Please choose the line order to update/delete", Alert.AlertType.INFORMATION);
            return false;
        }
        
        else if (quantityField.getText().equals("")) {
            alert.show("Oops!", "Quantity field cannot be empty!", Alert.AlertType.INFORMATION);
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
            alert.show("Oops!", "Quantity should be a natural number (40, 10)", Alert.AlertType.ERROR);
            return false;
        }
    }
    
     private void search()
    {
        String keyword = searchField.getText();
        
        if (keyword.equals("")) {
            tableOrder.setItems(dataOrder);
        }
        
        else {
            ObservableList<Order> filteredData = FXCollections.observableArrayList();
            for (Order order : dataOrder) {
                if(order.getDescription().contains(keyword))
                    filteredData.add(order);
            }
            tableOrder.setItems(filteredData);
        }
    }
     
     public void fillTheComboCustomer(){
    dataComboCustomer.add("<All>");
    for(int i = 0; i < customerManager.getAll().size(); i++){
    dataComboCustomer.add(customerManager.getAll().get(i).getFirstName()+" "+customerManager.getAll().get(i).getLastName());
    }
    
  }
     public void showOrdersInTheTableByCustomer(int customerId){
        dataOrder.clear();
        for(int i = 0 ; i < orderManager.getAllByCustomerId(customerId).size() ; i++){
          dataOrder.add(orderManager.getAllByCustomerId(customerId).get(i));
        }
    
    }
     
      public void showSelectedCustomer(){
    if(comboCustomer.getSelectionModel().getSelectedItem() != null){
    if(comboCustomer.getSelectionModel().getSelectedIndex() == 0)
    showOrdersInTheTable();
    else
    showOrdersInTheTableByCustomer(customerManager.getAll().get(comboCustomer.getSelectionModel().getSelectedIndex() - 1).getId());
   
    }
    
    
    }

    public int getSelectedOrderIndex() {
        return selectedOrderIndex;
    }
    
    
    
      
    
}
