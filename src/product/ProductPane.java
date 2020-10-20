
package product;

import alert.NewAlert;
import database.Connect;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import javax.swing.ImageIcon;
import product.ProductManager;
import product.category.CategoryManager;
import product.category.NewCategoryDialog;

public class ProductPane extends Pane {
    
    
    
    ProductManager productManager = new ProductManager() ;
    CategoryManager categoryManager = new CategoryManager();
    NewCategoryDialog newCategoryDialog = new NewCategoryDialog(); 
    NewAlert alert = new NewAlert();
    
    VBox vboxLabel = new VBox();
    VBox vboxField = new VBox();
    
    
    ComboBox<String> comboCategory = new ComboBox<>();
    ComboBox<String> comboCategoryUpdate = new ComboBox<>();
    ObservableList<String> dataComboCategory= FXCollections.observableArrayList();
    ObservableList<String> dataComboCategoryUpdate= FXCollections.observableArrayList();

    TableView table = new TableView();
    TableColumn<Product, Integer> columnId = new TableColumn("ID");
    TableColumn<Product, String> columnName = new TableColumn("Name");
    TableColumn<Product, Float> columnPrice = new TableColumn("Price (MAD)");
    TableColumn<Product, String> columnAddedDate = new TableColumn("Add Date");
    Label productImage = new Label("No image found");
    Label idLabel = new Label("ID");
    Label nameLabel = new Label("Name");
    Label priceLabel = new Label("Price");
    Label dateLabel = new Label("Date");
    Label categoryLabel = new Label("Category");
    Label sortByLabel = new Label("Sort By");
    Label searchLabel = new Label("Search");
    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField priceField = new TextField();
    DatePicker datePicker = new DatePicker();
    TextField categoryField = new TextField();
    TextField searchField = new TextField();
    
    Button updateImageButton = new Button("Update Image");
    Button insertButton = new Button("Product", new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png"))));
    Button refreshButton = new Button("Refrech", new ImageView(new Image(getClass().getResourceAsStream("/icons/refresh.png"))));
    Button updateButton = new Button("Update", new ImageView(new Image(getClass().getResourceAsStream("/icons/update1.png"))));
    Button deleteButton = new Button("Delete", new ImageView(new Image(getClass().getResourceAsStream("/icons/cancel.png"))));
    Button exitButton = new Button("Exit", new ImageView(new Image(getClass().getResourceAsStream("/icons/quit.png"))));
    Button newCategoryButton = new Button("Category", new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png"))));
    Button searchButton = new Button("search", new ImageView(new Image(getClass().getResourceAsStream("/icons/search1.png"))));
     
    ObservableList<Product> data = FXCollections.observableArrayList();
    NewProductDialog productDialog = new NewProductDialog(data);
    
    
    public ProductPane(){
    
         datePicker.setConverter(dateFormatter());
        
        table.getColumns().addAll(columnId, columnName, columnPrice, columnAddedDate);
        
        table.setItems(data);
        
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        columnAddedDate.setCellValueFactory(new PropertyValueFactory<>("addDate"));
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        comboCategory.setItems(dataComboCategory);
        comboCategoryUpdate.setItems(dataComboCategoryUpdate);
        
        productImage.setPrefSize(270, 244);
        updateImageButton.setPrefSize(130, 35);
        
        idLabel.setPrefSize(50, 40);
        idField.setPrefSize(270, 40);
        nameLabel.setPrefSize(50, 40);
        nameField.setPrefSize(270, 40);
        priceLabel.setPrefSize(50, 40);
        priceField.setPrefSize(270, 40);
        dateLabel.setPrefSize(50, 40);
        datePicker.setPrefSize(270, 40);
        categoryLabel.setPrefSize(50, 40);
        categoryField.setPrefSize(270, 40);
        comboCategoryUpdate.setPrefSize(270, 40);
        deleteButton.setPrefSize(130, 30);
        updateButton.setPrefSize(130, 30);
        table.setPrefSize(480, 488);
        searchField.setPrefSize(255, 36);
        searchLabel.setPrefSize(115, 40);
        insertButton.setPrefSize(100, 30);
        refreshButton.setPrefSize(130, 40);
        newCategoryButton.setPrefSize(100, 30);
        sortByLabel.setPrefSize(80, 40);
        comboCategory.setPrefSize(100, 40);
        searchButton.setPrefSize(100, 20);
        exitButton.setPrefSize(130, 30);
        
        
       
        
        productImage.setTranslateX(80);
        productImage.setTranslateY(40);
        updateImageButton.setTranslateX(150);
        updateImageButton.setTranslateY(295);
        /*
        nameLabel.setTranslateX(20);
        nameLabel.setTranslateY(355);
        nameField.setTranslateX(80);
        nameField.setTranslateY(355);
        
        priceLabel.setTranslateX(20);
        priceLabel.setTranslateY(405);
        priceField.setTranslateX(80);
        priceField.setTranslateY(405);
        
        dateLabel.setTranslateX(20);
        dateLabel.setTranslateY(455);
        datePicker.setTranslateX(80);
        datePicker.setTranslateY(455);
        
        categoryLabel.setTranslateX(20);
        categoryLabel.setTranslateY(505);
        categoryField.setTranslateX(80);
        categoryField.setTranslateY(505);
        comboCategoryUpdate.setTranslateX(80);
        comboCategoryUpdate.setTranslateY(505);
        
        */
        
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
       
        
        
        
        sortByLabel.setTranslateX(10);
        sortByLabel.setTranslateY(20);
        comboCategory.setTranslateX(10);
        comboCategory.setTranslateY(50);
        searchButton.setTranslateX(10);
        searchButton.setTranslateY(95);
        insertButton.setTranslateX(10);
        insertButton.setTranslateY(150);
        newCategoryButton.setTranslateX(10);
        newCategoryButton.setTranslateY(190);
        exitButton.setTranslateX(770);
        exitButton.setTranslateY(478);
        
        productImage.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        productImage.setAlignment(Pos.CENTER);
        categoryField.setEditable(false);
        sortByLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        
                
        vboxLabel.setSpacing(10);
        vboxField.setSpacing(10);         
        
        
        this.getChildren().add(table);
        //this.getChildren().add(productImage);
        //this.getChildren().add(updateImageButton);
        //this.getChildren().add(idLabel);
        //this.getChildren().add(idField);
        vboxLabel.getChildren().addAll(idLabel, nameLabel, priceLabel, dateLabel, categoryLabel);
        vboxField.getChildren().addAll(idField, nameField, priceField, datePicker, comboCategoryUpdate);
        this.getChildren().addAll(vboxLabel , vboxField);
        
        
        
        this.getChildren().add(insertButton);
        //this.getChildren().add(refreshButton);
        this.getChildren().add(newCategoryButton);
        this.getChildren().add(sortByLabel);
        this.getChildren().add(comboCategory);
        this.getChildren().add(searchButton);
        this.getChildren().add(updateButton);
        this.getChildren().add(deleteButton);
        this.getChildren().add(searchField);
        this.getChildren().add(searchLabel);
        
        this.getChildren().add(exitButton);
        
        

       showProductsInTheTable();
       //table.getSelectionModel().select(0);
       showProduct(0);
       showSelectedProduct();
       fillTheComboCategory();
       fillTheComboCategoryUpdate();
       
       comboCategory.getSelectionModel().select(1);
       updateButton.setOnAction(Action ->{updateProduct();});
       deleteButton.setOnAction(Action ->{deleteProduct();});
       insertButton.setOnAction(Action -> {productDialog.display(true);});
       refreshButton.setOnAction(Action ->{table.refresh();});
       newCategoryButton.setOnAction(Action -> {newCategoryDialog.display(true);});
       exitButton.setOnAction(Action -> {System.exit(0);});
       searchField.textProperty().addListener((obs, oldText, newText) -> {
            search();
        });
       searchButton.setOnAction(Action -> {showSelectedCategory();});
       
    }
    
    
    
    
    private void showProductsInTheTable(){
        data.clear();
        for(int i = 0 ; i < productManager.getAll().size() ; i++){
          data.add(productManager.getAll().get(i));
        }
    
    }
    
    private void showProductsInTheTableByCategory(int categoryId){
        data.clear();
        for(int i = 0 ; i < productManager.getAllByCategory(categoryId).size() ; i++){
          data.add(productManager.getAllByCategory(categoryId).get(i));
        }
    
    }
    
    private void showSelectedCategory(){
    if(comboCategory.getSelectionModel().getSelectedItem() != null){
    
    if(comboCategory.getSelectionModel().getSelectedIndex() == 0)
    showProductsInTheTable();
    else
    showProductsInTheTableByCategory(categoryManager.getAll().get(comboCategory.getSelectionModel().getSelectedIndex() - 1).getId());
   
    }
    
    }
    
    
     private void search()
    {
        String keyword = searchField.getText();
        
        if (keyword.equals("")) {
            table.setItems(data);
        }
        
        else {
            ObservableList<Product> filteredData = FXCollections.observableArrayList();
            for (Product product : data) {
                if(product.getName().contains(keyword))
                    filteredData.add(product);
            }
            table.setItems(filteredData);
        }
    }

    
    private void showProduct(int index)
    {
        idField.setText(data.get(index).getId() + "");
        nameField.setText(data.get(index).getName());
        priceField.setText(data.get(index).getPrice() + "");
        datePicker.getEditor().setText(data.get(index).getAddDate());
        if(categoryManager.search(data.get(index).getCategoryId()) == null)
        categoryField.setText("");
        else
        categoryField.setText(categoryManager.search(data.get(index).getCategoryId()).getName() + "");
        comboCategoryUpdate.getSelectionModel().select(getIndexById(data.get(index).getCategoryId()));
        
           
    }
    
   
    
    private void showSelectedProduct(){
    table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showProduct(table.getSelectionModel().getSelectedIndex());
                //comboCategory.getSelectionModel().select(1+getIndexById(productManager.search(Integer.parseInt(idField.getText())).getCategoryId()));
            }
        });

    }
    
    private boolean checkInputs()
    {
        if (nameField.getText().equals("") && priceField.getText().equals("")) {
            alert.show("Oops!", "Name and Price fields cannot be empty!", Alert.AlertType.INFORMATION);
            return false;
        }
        else if (nameField.getText().equals("")) {
            alert.show("Oops!", "Please enter product name", Alert.AlertType.INFORMATION);
            return false;
        }
        else if (priceField.getText().equals("")) {
            alert.show("Oops!", "Please enter product price", Alert.AlertType.INFORMATION);
            return false;
        }
        try {
            Float.parseFloat(priceField.getText());
            return true;
        }
        catch (NumberFormatException ex) {
            alert.show("Oops!", "Price should be a decimal number (40, 10.5)", Alert.AlertType.ERROR);
            return false;
        }
    }
    
    private boolean isProductSelected(){
    if(table.getSelectionModel().getSelectedItem() == null)
        {
            alert.show("Oops!", "Select the product that you want to update", AlertType.INFORMATION);
            return false;
        }
    return true;
    }
    
    private void updateProduct(){
    if(isProductSelected() && checkInputs()){
    int categoryId = categoryManager.getAll().get(comboCategoryUpdate.getSelectionModel().getSelectedIndex()).getId();
    Product selectedProduct = (Product) table.getSelectionModel().getSelectedItem();
    alert.show("", "Are you sure to update product", AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    productManager.edit(Integer.parseInt(idField.getText()), nameField.getText(), Float.parseFloat(priceField.getText()), datePicker.getEditor().getText(), categoryId);
    selectedProduct.setName(nameField.getText());
    selectedProduct.setPrice(Float.parseFloat(priceField.getText()));
    selectedProduct.setAddDate(datePicker.getEditor().getText());
    selectedProduct.setCategoryId(categoryId);
    table.refresh();
    //showProductsInTheTable();
    alert.show("done", "product updated !", AlertType.INFORMATION);
    }
    
    }
        
    
    }
    
    private void deleteProduct(){
        
    if(isProductSelected()){
    alert.show("", "Are you sure to delete the product", AlertType.CONFIRMATION);
    if(alert.getAction().get() == ButtonType.OK){
    productManager.delete(Integer.parseInt(idField.getText()));
    data.remove(table.getSelectionModel().getSelectedItem());
    //showProductsInTheTable();
    table.refresh();
    alert.show("done", "product deleted !", AlertType.INFORMATION);
    }    
    
    }    
    
    }
    
    private StringConverter dateFormatter()
    {
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                }
                return "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                }
                return null;
            }
        };
        return converter;
    }
    
    private void fillTheComboCategory(){
    dataComboCategory.add("<All>");
    for(int i = 0; i < categoryManager.getAll().size(); i++){
    dataComboCategory.add(categoryManager.getAll().get(i).getName());
    }
}
    
     private void fillTheComboCategoryUpdate(){
    
    for(int i = 0; i < categoryManager.getAll().size(); i++){
    dataComboCategoryUpdate.add(categoryManager.getAll().get(i).getName());
    }
}
    
    private int getIndexById(int categoryId){
    for(int i = 0; i < categoryManager.getAll().size(); i++){
    if(categoryManager.getAll().get(i).getId() == categoryId)
    return i;
    }
    return 0;
    }


}
