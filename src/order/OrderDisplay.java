
package order;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class OrderDisplay extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        OrderPane orderPane = new OrderPane();
        Scene scene = new Scene(orderPane, 1070, 640);
        stage.setTitle("Orders Manager");
        stage.getIcons().add(new Image("/icons/store.png"));
        stage.setScene(scene);
        stage.show();
    }
    
}
