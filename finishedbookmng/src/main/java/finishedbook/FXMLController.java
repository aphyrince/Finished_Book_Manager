package finishedbook;
/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label countLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @SuppressWarnings("exports")
    public Label getCountLabel(){
        return countLabel;
    }
}
