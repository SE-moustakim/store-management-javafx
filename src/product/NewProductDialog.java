/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import alert.NewAlert;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import product.category.CategoryManager;

/**
 *
 * @author x
 */
public class NewProductDialog extends Dialog {
    
    ProductManager productManager = new ProductManager();
    CategoryManager categoryManager = new CategoryManager();
    NewAlert alert = new NewAlert();
   
    
    ComboBox<String> comboCategory = new ComboBox<>();
    ObservableList<String> dataComboCategory= FXCollections.observableArrayList();
    
    Pane pane = new Pane();
    Label nameLabel = new Label("Name");
    Label productImage = new Label("No image Selected");
    Label priceLabel = new Label("Price ( MAD )");
    Label categoryLabel = new Label("Category");
    TextField nameField = new TextField();
    TextField priceField = new TextField();
    Button chooseImageButton = new Button("choose Image", new ImageView(new Image(getClass().getResourceAsStream("/icons/add-image.png"))));
    Button addButton = new Button("Add Product", new ImageView(new Image(getClass().getResourceAsStream("/icons/add-product.png"))));
    
    ObservableList data;
    
    public NewProductDialog(ObservableList data){
    
        this.data = data;
        comboCategory.setItems(dataComboCategory);
        
        
        pane.setPrefSize(350, 340);
        productImage.setPrefSize(224, 224);
        nameLabel.setPrefSize(80, 40);
        nameField.setPrefSize(270, 40);
        priceLabel.setPrefSize(80, 40);
        priceField.setPrefSize(270, 40);
        categoryLabel.setPrefSize(80, 40);
        comboCategory.setPrefSize(270, 40);
        chooseImageButton.setPrefSize(224, 35);
        addButton.setPrefSize(270, 35);
        
        /*
        productImage.setTranslateX(36);
        productImage.setTranslateY(40);
        nameLabel.setTranslateX(300);
        nameLabel.setTranslateY(30);
        nameField.setTranslateX(300);
        nameField.setTranslateY(65);
        priceLabel.setTranslateX(300);
        priceLabel.setTranslateY(110);
        priceField.setTranslateX(300);
        priceField.setTranslateY(145);
        categoryLabel.setTranslateX(300);
        categoryLabel.setTranslateY(190);
        comboCategory.setTranslateX(300);
        comboCategory.setTranslateY(225);
        chooseImageButton.setTranslateX(36);
        chooseImageButton.setTranslateY(280);
        addButton.setTranslateX(300);
        addButton.setTranslateY(280);
        */
        productImage.setTranslateX(36);
        productImage.setTranslateY(40);
        
        nameLabel.setTranslateX(50);
        nameLabel.setTranslateY(30);
        nameField.setTranslateX(50);
        nameField.setTranslateY(65);
        priceLabel.setTranslateX(50);
        priceLabel.setTranslateY(110);
        priceField.setTranslateX(50);
        priceField.setTranslateY(145);
        categoryLabel.setTranslateX(50);
        categoryLabel.setTranslateY(190);
        comboCategory.setTranslateX(50);
        comboCategory.setTranslateY(225);
        addButton.setTranslateX(50);
        addButton.setTranslateY(280);
        
        productImage.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        productImage.setAlignment(Pos.CENTER);
        
        
        //pane.getChildren().add(productImage);
        //pane.getChildren().add(chooseImageButton);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(priceLabel);
        pane.getChildren().add(nameField);
        pane.getChildren().add(priceField);
        pane.getChildren().add(categoryLabel);
        pane.getChildren().add(comboCategory);
        pane.getChildren().add(addButton);
        
        
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        this.setTitle("Add New Product");
        this.getDialogPane().setContent(pane);
        nameField.requestFocus();
        
        fillTheComboCategory();
        
        addButton.setOnAction(Action ->{insertProduct();});
        
        
        

    } 
    
     // الدالة التالية سنستخدمها لإظهار و إخفاء النافذة المنبثقة
    public void display(boolean value)
    {
        // سيتم إظهار النافذة المنبثقة مع مسح أي قيم موضوعة فيها سابقاً value للباراميتر true إذا قمنا بتمرير القيمة
        if(value == true) {
            
            this.showAndWait();
        }
        // سيتم إخفاء النافذة المنبثقة value للباراميتر false إذا قمنا بتمرير القيمة
        else {
            this.hide();
        }
    }
    
    private boolean checkInputs()
    {
        if (nameField.getText().equals("") && priceField.getText().equals("")) {
            alert.show("Oops!", "Name and Price fields cannot be empty!", AlertType.INFORMATION);
            return false;
        }
        else if (nameField.getText().equals("")) {
            alert.show("Oops!", "Please enter product name", AlertType.INFORMATION);
            return false;
        }
        else if (priceField.getText().equals("")) {
            alert.show("Oops!", "Please enter product price", AlertType.INFORMATION);
            return false;
        }
        
        else if(comboCategory.getSelectionModel().getSelectedItem() == null){
        alert.show("Oops!", "Please the category of the product", AlertType.INFORMATION);
            return false;
        }
        
       
        try {
            Float.parseFloat(priceField.getText());
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "Price should be a decimal number (40, 10.5)", AlertType.ERROR);
            return false;
        }
        
        
    }
    
    public void fillTheComboCategory(){
    for(int i = 0; i < categoryManager.getAll().size(); i++){
    dataComboCategory.add(categoryManager.getAll().get(i).getName());
    }
}
    
    public void insertProduct(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = dateFormat.format(new Date());
    if(checkInputs()){
    alert.show("", "Are you sure to create the product ?", AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    int categoryId = categoryManager.getAll().get(comboCategory.getSelectionModel().getSelectedIndex()).getId();    
    productManager.insert(new Product(0,nameField.getText(), Float.parseFloat(priceField.getText()), addDate, categoryId));
    alert.show("done", "product created !", AlertType.INFORMATION);
    showProductInTheTable();
    clearData();
    display(false);
    
    }    
    
    }
    
    }
    
    private void  showProductInTheTable(){
     data.add(productManager.getAll().get(productManager.getAll().size() - 1));
    }
    
    private void clearData(){
    nameField.clear();
    priceField.clear();
    comboCategory.getSelectionModel().select(-1);
    }

}
