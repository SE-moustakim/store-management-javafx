
package alert;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class NewAlert {
     
    Alert alert = new Alert(AlertType.NONE);
    private Optional <ButtonType> action;
    
    public void show(String title, String message, AlertType alertType) {
        alert.setTitle(title);
        alert.setHeaderText(message);
        //alert.setContentText(message);
        alert.setAlertType(alertType);
        action = alert.showAndWait();
    }

    public Optional<ButtonType> getAction() {
        return action;
    }
    
    
}
