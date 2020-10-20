
package product;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ProductDisplay extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        ProductPane ProductPane = new ProductPane();
        
        // scene في الكائن Root Node أي كأننا وضعناه كـ .scene مباشرةً في الكائن allProductsPane هنا قمنا بوضع الكائن
        Scene scene = new Scene(ProductPane, 1000, 620);
        // هنا وضعنا عنوان للنافذة
        stage.setTitle("Products Manager");
        stage.getIcons().add(new Image("/icons/store.png"));
        // أي وضعنا محتوى النافذة الذي قمنا بإنشائه للنافذة .stage في كائن الـ scene هنا وضعنا كائن الـ
        stage.setScene(scene);
        
        // هنا قمنا بإظهار النافذة
        stage.show();

    }
    
}
