
package storefx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import product.ProductPane;


public class MenuDisplay extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Menu menu = new Menu();
        
        Scene scene = new Scene(menu, 1000, 620);
        
        stage.setScene(scene);
        
        stage.show();

    }
    
}
