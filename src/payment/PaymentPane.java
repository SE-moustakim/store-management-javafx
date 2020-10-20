
package payment;

import alert.NewAlert;
import customer.CustomerManager;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import order.OrderManager;
import payment.types.Check;


public class PaymentPane extends Pane {
    
    NewAlert alert = new NewAlert();
    Check check;
    CheckDialog checkDialog = new CheckDialog(check);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int saleId = 0;
    VBox vboxLabel = new VBox();
    VBox vboxFiled = new VBox();
    
    ComboBox<String> comboType = new ComboBox<>();
    ComboBox<Integer> comboSale = new ComboBox<>();
    ObservableList<String> dataComboType = FXCollections.observableArrayList();
    ObservableList<Integer> dataComboSale = FXCollections.observableArrayList();
    
    PaymentManager paymentManager = new PaymentManager();
    OrderManager orderManager = new OrderManager();
    CustomerManager customerManager = new CustomerManager();
    
    TableView table = new TableView();
    TableColumn<Payment, Integer> columnDate = new TableColumn("Date");
    TableColumn<Payment, Integer> columnNum = new TableColumn("Num");
    TableColumn<Payment, Double> columnAmount = new TableColumn("Amount");
    TableColumn<Payment, String> columnType = new TableColumn("Type");
    
    Label saleNumLabel = new Label("Payments for sale ");
    Label saleDateLabel = new Label("Date");
    Label customerLabel = new Label("Customer");
    Label totalLabel = new Label("Total");
    Label resteLabel = new Label("Reste");
    Label paymentNumLabel = new Label("Num");
    Label paymentDateLabel = new Label("Date");
    Label paymentAmountLabel = new Label("Amount");
    Label paymentTypeLabel = new Label("Type");
    Label newPayment = new Label("New Payment");
    Label totalPaidLabel = new Label("Total Paid");
    
    TextField saleNumField = new TextField();
    TextField saleDateField = new TextField();
    TextField customerField = new TextField();
    TextField totalField = new TextField();
    TextField resteField = new TextField();
    TextField paymentNumField = new TextField();
    TextField paymentDateField = new TextField();
    TextField paymentAmountField = new TextField();
    TextField paymentTypeField = new TextField();
    TextField totalPaidField = new TextField();
    
    Button saveButton = new  Button("Save", new ImageView(new Image(getClass().getResourceAsStream("/icons/save.png"))));
    Button cancelButton = new  Button("Cancel", new ImageView(new Image(getClass().getResourceAsStream("/icons/cancel.png"))));
    Button searchButton = new  Button("Search", new ImageView(new Image(getClass().getResourceAsStream("/icons/search1.png"))));
    
    ObservableList<Payment> data = FXCollections.observableArrayList();
    
    public PaymentPane(){
        table.getColumns().addAll(columnDate, columnNum, columnAmount, columnType);
        table.setItems(data);
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnNum.setCellValueFactory(new PropertyValueFactory<>("Num"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        comboType.setItems(dataComboType);
        comboSale.setItems(dataComboSale);
        
        saleNumLabel.setPrefSize(100, 30);
        //saleNumField.setPrefSize(80, 30);
        comboSale.setPrefSize(80, 30);
        saleDateLabel.setPrefSize(80, 30);
        saleDateField.setPrefSize(100, 25);
        customerLabel.setPrefSize(80, 40);
        customerField.setPrefSize(100, 25);
        totalLabel.setPrefSize(80, 40);
        totalField.setPrefSize(160, 40);
        resteLabel.setPrefSize(80, 40);
        resteField.setPrefSize(160, 40);
        paymentNumLabel.setPrefSize(80, 40);
        paymentNumField.setPrefSize(160, 40);
        paymentDateLabel.setPrefSize(80, 40);
        paymentDateField.setPrefSize(160, 40);
        paymentAmountLabel.setPrefSize(80, 40);
        paymentAmountField.setPrefSize(160, 40);
        paymentTypeLabel.setPrefSize(80, 40);
        //paymentTypeField.setPrefSize(160, 40);
        comboType.setPrefSize(160, 40);
        table.setPrefSize(400, 300);
        totalPaidLabel.setPrefSize(80, 30);
        totalPaidField.setPrefSize(105, 30);
        newPayment.setPrefSize(80, 40);
        saveButton.setPrefSize(100, 30);
        cancelButton.setPrefSize(100, 30);
        searchButton.setPrefSize(80, 30);
        
        totalField.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        totalField.setStyle("-fx-background-color: #ececec;");
        resteField.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        resteField.setStyle("-fx-background-color: #ececec;");
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        resteLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        newPayment.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        saleDateField.setStyle("-fx-background-color: #ececec;");
        customerField.setStyle("-fx-background-color: #ececec;");
        totalPaidField.setStyle("-fx-background-color: #ececec;");
        totalPaidLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        
        saleDateField.setEditable(false);
        customerField.setEditable(false);
        totalField.setEditable(false);
        resteField.setEditable(false);
        totalPaidField.setEditable(false);
        paymentNumField.setEditable(false);
        paymentDateField.setEditable(false);
        
        saleNumLabel.setTranslateX(20);
        saleNumLabel.setTranslateY(20);
        saleNumField.setTranslateX(120);
        saleNumField.setTranslateY(20);
        comboSale.setTranslateX(120);
        comboSale.setTranslateY(20);
        searchButton.setTranslateX(210);
        searchButton.setTranslateY(20);
        
        saleDateLabel.setTranslateX(20);
        saleDateLabel.setTranslateY(60);
        saleDateField.setTranslateX(120);
        saleDateField.setTranslateY(60);
        
        customerLabel.setTranslateX(20);
        customerLabel.setTranslateY(86);
        customerField.setTranslateX(120);
        customerField.setTranslateY(90);
        
        table.setTranslateX(20);
        table.setTranslateY(120);
        totalPaidLabel.setTranslateX(250);
        totalPaidLabel.setTranslateY(430);
        totalPaidField.setTranslateX(310);
        totalPaidField.setTranslateY(430);
        totalLabel.setTranslateX(500);
        totalLabel.setTranslateY(20);
        totalField.setTranslateX(550);
        totalField.setTranslateY(20);
        resteLabel.setTranslateX(500);
        resteLabel.setTranslateY(70);
        resteField.setTranslateX(550);
        resteField.setTranslateY(70);
        newPayment.setTranslateX(590);
        newPayment.setTranslateY(150);
        vboxFiled.setTranslateX(550);
        vboxFiled.setTranslateY(200);
        vboxFiled.setSpacing(5);
        vboxLabel.setTranslateX(500);
        vboxLabel.setTranslateY(200);
        vboxLabel.setSpacing(5);
        saveButton.setTranslateX(500);
        saveButton.setTranslateY(390);
        cancelButton.setTranslateX(610);
        cancelButton.setTranslateY(390);
        
        
        
        this.getChildren().addAll(saleNumLabel, comboSale);
        this.getChildren().addAll(saleDateLabel, saleDateField);
        this.getChildren().addAll(customerLabel, customerField);
        this.getChildren().add(table);
        this.getChildren().addAll(totalPaidLabel, totalPaidField);
        this.getChildren().add(newPayment);
        this.getChildren().addAll(totalLabel, totalField);
        this.getChildren().addAll(resteLabel, resteField);
        vboxFiled.getChildren().addAll(paymentNumField, paymentDateField, paymentAmountField, comboType);
        vboxLabel.getChildren().addAll(paymentNumLabel, paymentDateLabel, paymentAmountLabel, paymentTypeLabel);
        this.getChildren().addAll(vboxFiled, vboxLabel);
        this.getChildren().addAll(saveButton, cancelButton, searchButton);
        
       dataComboType.add("espece");
       dataComboType.add("cheque"); 
       dataComboType.add("carte"); 
        
       fillComboSale();
       
       showSelectedPaymentType();
       //checkDialog.display(true);
       
        saveButton.setOnAction(Action ->{createPayment();});
        cancelButton.setOnAction(Action -> {cancelPayment(); paymentAmountField.setEditable(true);});
        paymentAmountField.textProperty().addListener((obs, oldText, newText) -> {
            if(!paymentAmountField.getText().equals("") && isNumber()){
               
            if(Float.parseFloat(paymentAmountField.getText()) >= Float.parseFloat(resteField.getText())){
            paymentAmountField.setText(Float.parseFloat(resteField.getText()) + "");
            paymentAmountField.setEditable(false);
            }
            }
            
        });
       
        searchButton.setOnAction(Action -> {
        if(isSaleSelected()){
        int orderId = comboSale.getSelectionModel().getSelectedItem();     
        fillTheTable(orderId);
        
        paymentNumField.setText(paymentManager.getLastNum(orderId)+1 + "");
        
        paymentDateField.setText(dateFormat.format(new Date()));
        totalField.setText(orderManager.search(orderId).getTotal() + "");
        totalPaidField.setText(paymentManager.getTotalPayment(orderId) + "");
        resteField.setText(orderManager.search(orderId).getTotal() - paymentManager.getTotalPayment(orderId) + "");
        isOrderPayed();
        saleDateField.setText(orderManager.search(orderId).getDate());
        customerField.setText(customerManager.search(orderManager.search(orderId).getCustomerId()).getFirstName() + " " + customerManager.search(orderManager.search(orderId).getCustomerId()).getLastName());
        }    
        
        });
        
    }
    
    
    public void fillTheTable(int saleId){
    data.clear();
    for(int i = 0; i <paymentManager.getAllBySaleId(saleId).size(); i++){
    data.add(paymentManager.getAllBySaleId(saleId).get(i));
    }
    }
    
    public void createPayment(){
     if(isSaleSelected() && checkInputs()){
    alert.show("", "Are you sure to add this payment ?", Alert.AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    int num = paymentManager.getLastNum(comboSale.getSelectionModel().getSelectedItem())+1;
    String date = paymentDateField.getText();
    double amount = Double.parseDouble(paymentAmountField.getText());
    String type = comboType.getSelectionModel().getSelectedItem();
    paymentManager.insert(new Payment(0, comboSale.getSelectionModel().getSelectedItem(), num, amount, date, type));
    int orderId = comboSale.getSelectionModel().getSelectedItem();     
    fillTheTable(orderId);
    paymentNumField.setText(paymentManager.getLastNum(orderId)+1 + "");
    cancelPayment();
    totalPaidField.setText(paymentManager.getTotalPayment(orderId) + "");
    resteField.setText(orderManager.search(orderId).getTotal() - paymentManager.getTotalPayment(orderId) + "");
    isOrderPayed();
    alert.show("done !", "The payment is saved", Alert.AlertType.INFORMATION);
    }
    
 }
    
}
    
    public void cancelPayment(){
    paymentAmountField.clear();
    
    }
    
    public void fillComboSale(){
    for(int i = 0; i <orderManager.getAll().size(); i++){
    dataComboSale.add(orderManager.getAll().get(i).getId());
    }
    }
    
    
    public boolean isSaleSelected(){
      if(comboSale.getSelectionModel().getSelectedItem() == null){
       alert.show("Oops!", "please choose a sale!", Alert.AlertType.INFORMATION);
       return false;
      }
      return true;
      }
    
    public boolean checkInputs(){
     if(paymentAmountField.getText().equals("")){
       alert.show("Oops!", "Amount can not be empty!", Alert.AlertType.INFORMATION);
       return false;
      }
        
    else if(comboType.getSelectionModel().getSelectedItem() == null){
       alert.show("Oops!", "please choose the type of payment!", Alert.AlertType.INFORMATION);
       return false;
      }
     
     try {
            float amount = Float.parseFloat(paymentAmountField.getText());
            if(amount <= 0){
            alert.show("Oops!", "the amount cannot equal 0 !", Alert.AlertType.INFORMATION);
            return false;
            }
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "Amount should be a decimal number (40, 10.5)", Alert.AlertType.ERROR);
            return false;
        }
     
    }
    
    private boolean isNumber(){
     try {
            float amount = Float.parseFloat(paymentAmountField.getText());
            if(amount <= 0){
            alert.show("Oops!", "the amount cannot equal 0 !", Alert.AlertType.INFORMATION);
            return false;
            }
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "Amount should be a decimal number (40, 10.5)", Alert.AlertType.ERROR);
            return false;
        }
    }
    
    private void showSelectedPaymentType(){
    comboType.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(comboType.getSelectionModel().getSelectedIndex() == 1)
                checkDialog.display(true);
        });

    }
    
    private void isOrderPayed(){
    if(Float.parseFloat(resteField.getText()) == 0)
        paymentAmountField.setEditable(false);
    }
    
    
    
}
