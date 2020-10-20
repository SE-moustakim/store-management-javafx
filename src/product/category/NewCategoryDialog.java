
package product.category;

import alert.NewAlert;
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


public class NewCategoryDialog extends Dialog {

NewAlert alert = new NewAlert();    
CategoryManager categoryManager = new CategoryManager();
    
Pane pane = new Pane();

Label nameLabel = new Label("Name");
TextField nameField = new TextField();

Button saveButton = new Button("Save");
Button cancelButton = new Button("Cancel", new ImageView(new Image(getClass().getResourceAsStream("/icons/exit.png"))));

public NewCategoryDialog(){

    pane.setPrefSize(300, 150);
    
    nameLabel.setPrefSize(80, 40);
    nameField.setPrefSize(180, 40);
    saveButton.setPrefSize(80, 40);
    cancelButton.setPrefSize(80, 30);
    
    
    nameLabel.setTranslateX(20);
    nameLabel.setTranslateY(20);
    nameField.setTranslateX(20);
    nameField.setTranslateY(60);
    saveButton.setTranslateX(210);
    saveButton.setTranslateY(60);
    cancelButton.setTranslateX(210);
    cancelButton.setTranslateY(125);
    
    
    saveButton.setOnAction(Action -> {createCategory();});
    cancelButton.setOnAction(Action -> {this.hide();});
    
    this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
    closeButton.managedProperty().bind(closeButton.visibleProperty());
    closeButton.setVisible(false);
    this.setTitle("New Category");
    
    pane.getChildren().addAll(nameField, nameLabel, saveButton, cancelButton);
    this.getDialogPane().setContent(pane);

}

public void display(boolean value)
    {
        if(value == true) {this.showAndWait();}
        else {this.hide();}
    }



public boolean checkInput(){
if(nameField.getText().equals("")){
alert.show("Oops!", "Please enter the name of the category", Alert.AlertType.ERROR);
return false;
}
return true;
}



public void createCategory(){
if(checkInput()){
categoryManager.insert(new Category(0, nameField.getText()));
nameField.clear();
alert.show("", "Category saved !", Alert.AlertType.CONFIRMATION);
this.hide();

}
}
    
}
