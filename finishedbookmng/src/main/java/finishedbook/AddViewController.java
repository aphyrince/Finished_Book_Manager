package finishedbook;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddViewController implements Initializable{
    @FXML private TextField titleTextField;
    @FXML private DatePicker datePicker;
    @FXML private Button addButton;
    @FXML private Button cancelButton;
    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void addButtonFunc(@SuppressWarnings("exports") ActionEvent e){
        if(!getTitleInput().equals("") && getDateInput() != null){
            mainApp.listViewController.addItem(new Book(getTitleInput(), getDateInput()));
            mainApp.hideAddView();
        }
    }
    @FXML
    public void cancelButtonFunc(@SuppressWarnings("exports") ActionEvent e){
        mainApp.hideAddView();
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    public String getTitleInput(){
        return titleTextField.getText();
    }
    public LocalDate getDateInput(){
        return datePicker.getValue();
    }
}
