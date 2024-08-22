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

public class ModifyViewController implements Initializable{
    @FXML private TextField titleTextField;
    @FXML private DatePicker datePicker;
    @FXML private Button applyButton;
    @FXML private Button cancelButton;
    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void applyButtonFunc(@SuppressWarnings("exports") ActionEvent e){
        mainApp.listViewController.modifyItem(new Book(getTitleInput(),getDateInput()));
        mainApp.hideView(mainApp.modifyView);
    }
    @FXML
    public void cancelButtonFunc(@SuppressWarnings("exports") ActionEvent e){
        mainApp.hideView(mainApp.modifyView);
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    public String getTitleInput(){
        return titleTextField.getText();
    }
    public void setTitleInput(String oldTitle){
        titleTextField.setText(oldTitle);
    }
    public LocalDate getDateInput(){
        return datePicker.getValue();
    }
    public void setDateInput(LocalDate oldDate){
        this.datePicker.setValue(oldDate);
    }
}
