package finishedbook;
/*
Put header here


 */

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.time.LocalDate;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ListViewController implements Initializable {

    @SuppressWarnings("unused")
    private List<Book> bookList;
    private MainApp mainApp;

    @FXML
    private ListView<BorderPane> listView;
    @FXML
    private Label countLabel;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button delButton;

    private BorderPane targetItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // listView의 CellFactory 설정
        listView.setCellFactory(new Callback<ListView<BorderPane>, ListCell<BorderPane>>() {
            @Override
            public ListCell<BorderPane> call(ListView<BorderPane> param) {
                return new ListCell<BorderPane>() {
                    @Override
                    protected void updateItem(BorderPane item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null)
                            setGraphic(null);
                        else
                            setGraphic(item);
                    }
                };
            }

        });

    }

    @FXML
    public void addButtonFunc(@SuppressWarnings("exports") ActionEvent e) {
        mainApp.showView(mainApp.addView);
    }

    @FXML
    public void modifyButtonFunc(@SuppressWarnings("exports") ActionEvent e) {
        BorderPane selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            targetItem = selectedItem;
            String title = ((Text) selectedItem.getCenter()).getText();
            LocalDate date;
            if(((Text)selectedItem.getRight()).getText().equals("-")){
                date = LocalDate.now();
            }
            else{
                date = LocalDate.parse(((Text) selectedItem.getRight()).getText());
            }
            mainApp.showView(mainApp.modifyView);
            mainApp.modifyViewController.setTitleInput(title);
            mainApp.modifyViewController.setDateInput(date);
        }
    }

    @FXML
    public void delButtonFunc(@SuppressWarnings("exports") ActionEvent e) {
        BorderPane selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listView.getItems().remove(selectedItem);
            String title = ((Text) selectedItem.getCenter()).getText();
            LocalDate date;
            if(((Text)selectedItem.getRight()).getText().equals("-")){
                date = LocalDate.now();
            }
            else{
                date = LocalDate.parse(((Text) selectedItem.getRight()).getText());
            }
            mainApp.removeBook(new Book(title,date));
            countingSize();
        }
    }

    public void cstInit(List<Book> bookList) {
        this.bookList = bookList;
        Platform.runLater(() -> bookList.stream().forEach(book -> addItem(book)));
        Platform.runLater(() -> {
            countLabel.setText(bookList.size() + " 권");
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void addItem(Book book) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 20, 0, 0));

        borderPane.setCenter(new Text(book.getTitle()));
        if (book.getDate() != null)
            borderPane.setRight(new Text(book.getDate().toString()));
        else
            borderPane.setRight(new Text("-"));
        listView.getItems().add(borderPane);
    }

    public void modifyItem(Book book) {
        ((Text) targetItem.getCenter()).setText(book.getTitle());
        ((Text) targetItem.getRight()).setText(book.getDate().toString());
    }

    public void countingSize(){
        Platform.runLater(() -> {
            countLabel.setText(bookList.size() + " 권");
        });
    }
}
