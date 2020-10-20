/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import product.ProductPane;

/**
 *
 * @author x
 */
public class PaymentDisplay extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        PaymentPane PaymentPane = new PaymentPane();
        
        // scene في الكائن Root Node أي كأننا وضعناه كـ .scene مباشرةً في الكائن allProductsPane هنا قمنا بوضع الكائن
        Scene scene = new Scene(PaymentPane, 800, 500);
        // هنا وضعنا عنوان للنافذة
        stage.setTitle("Payments Manager");
        stage.getIcons().add(new Image("/icons/store.png"));
        // أي وضعنا محتوى النافذة الذي قمنا بإنشائه للنافذة .stage في كائن الـ scene هنا وضعنا كائن الـ
        stage.setScene(scene);
        
        // هنا قمنا بإظهار النافذة
        stage.show();

    }
    
}
