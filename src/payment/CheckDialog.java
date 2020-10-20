
package payment;

import alert.NewAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import payment.types.Check;
import payment.types.CheckManager;


public class CheckDialog extends Dialog{
    
    Pane pane = new Pane();
    NewAlert alert = new NewAlert();
    CheckManager checkManager = new CheckManager(); 
    
    
    VBox vboxLabel = new VBox();
    VBox vboxFiled = new VBox();
    
    Label firstNameLabel = new Label("First Name");
    Label lastNameLabel = new Label("Last Name");
    Label amountLabel = new Label("Amount");
    Label bankLabel = new Label("Bank");
    Label dateLabel = new Label("Date");
    
    TextField firstNameField = new TextField();
    TextField lastNameField = new TextField();
    TextField amountField = new TextField();
    ComboBox<String> comboBank = new ComboBox<>();
    ObservableList<String> dataComboBank = FXCollections.observableArrayList();
    DatePicker datePicker = new DatePicker();
    
    
    Button saveButton = new  Button("Save", new ImageView(new Image(getClass().getResourceAsStream("/icons/save.png"))));
    
    Check check;
    
    public CheckDialog(Check check) {
    this.check = check;
    comboBank.setItems(dataComboBank);
    
    pane.setPrefSize(400, 340);
        
        firstNameLabel.setPrefSize(80, 40);
        firstNameField.setPrefSize(270, 40);
        lastNameLabel.setPrefSize(80, 40);
        lastNameField.setPrefSize(270, 40);
        amountLabel.setPrefSize(80, 40);
        amountField.setPrefSize(270, 40);
        bankLabel.setPrefSize(80, 40);
        comboBank.setPrefSize(270, 40);
        dateLabel.setPrefSize(80, 40);
        datePicker.setPrefSize(270, 40);
        saveButton.setPrefSize(100, 30);
        
        vboxLabel.setTranslateX(10);
        vboxLabel.setTranslateY(20);
        vboxFiled.setTranslateX(80);
        vboxFiled.setTranslateY(20);
        vboxLabel.setSpacing(10);
        vboxFiled.setSpacing(10);
        saveButton.setTranslateX(150);
        saveButton.setTranslateY(300);
        
        
        pane.getChildren().addAll(vboxLabel, vboxFiled, saveButton);
        vboxLabel.getChildren().addAll(firstNameLabel, lastNameLabel, amountLabel, bankLabel, dateLabel);
        vboxFiled.getChildren().addAll(firstNameField, lastNameField, amountField, comboBank, datePicker);
        
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        this.setTitle("Check Informations");
        this.getDialogPane().setContent(pane);
        
        fillComboBank();
        
        saveButton.setOnAction(Action ->{createCheck();});
        
    }
    
    
    
    public void display(boolean value)
    {
    if(value == true) {this.showAndWait();}
        else{this.hide();}
    }
    
    
    public boolean checkInputs(){
    if(firstNameField.getText().equals("") || lastNameField.getText().equals("")){
    alert.show("Oops !", "Please enter the last and first name", Alert.AlertType.ERROR);
    return false;
    }
    
    else if(datePicker.getEditor().getText().equals("")){
    alert.show("Oops !", "Plese choose the date", Alert.AlertType.ERROR);
    return false;
    }
    
    else if(comboBank.getSelectionModel().getSelectedIndex() == -1){
    alert.show("Oops !", "Please choode your bank", Alert.AlertType.ERROR);
    return false;
    }
    
    try {
            Float.parseFloat(amountField.getText());
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "Amount should be a decimal number (40, 10.5)", Alert.AlertType.ERROR);
            return false;
        }
    
    
        
    }
    
    private void createCheck(){
    if(checkInputs()){
    check = new Check(0,firstNameField.getText(), lastNameField.getText(), Float.parseFloat(amountField.getText()), comboBank.getSelectionModel().getSelectedItem(), datePicker.getEditor().getText());
    //System.out.println(check);
    checkManager.insert(check);
    alert.show("done !", "check created ", Alert.AlertType.CONFIRMATION);
    this.hide();
    }
    }
    
    private void fillComboBank(){
    dataComboBank.add("BMCE");
    dataComboBank.add("CIH");
    }
    
    
    
    
   
    
    
    
    
}
