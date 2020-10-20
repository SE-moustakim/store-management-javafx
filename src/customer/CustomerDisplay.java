
package customer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class CustomerDisplay extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CustomerPane customerPane = new CustomerPane();
        Scene scene = new Scene(customerPane, 1000, 620);
        stage.setTitle("Customers Manager");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/icons/store.png"));
        stage.show();
        
    }
    
}
