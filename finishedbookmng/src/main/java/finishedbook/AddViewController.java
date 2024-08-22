package finishedbook;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddViewController implements Initializable {
    @FXML
    private TextField titleTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void addButtonFunc(@SuppressWarnings("exports") ActionEvent e) {
        if (!getTitleInput().equals("") && getDateInput() != null) {
            Book newBook = new Book(getTitleInput(), getDateInput());
            mainApp.addBook(newBook);
            mainApp.listViewController.addItem(new Book(getTitleInput(), getDateInput()));
            mainApp.hideView(mainApp.addView);
            mainApp.listViewController.countingSize();
        }
    }

    @FXML
    public void cancelButtonFunc(@SuppressWarnings("exports") ActionEvent e) {
        mainApp.hideView(mainApp.addView);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public String getTitleInput() {
        return titleTextField.getText();
    }

    public LocalDate getDateInput() {
        return datePicker.getValue();
    }
}
