package finishedbook;
/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class FXMLController implements Initializable {

    @FXML
    private Label countLabel;
    @FXML
    private ListView<BorderPane> listView;

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

    @SuppressWarnings("exports")
    public Label getCountLabel() {
        return countLabel;
    }

    @SuppressWarnings("exports")
    public ListView<BorderPane> getListView() {
        return listView;
    }

    public void addItem(Book book) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 20, 0, 0));
        
        borderPane.setCenter(new Text(book.getTitle()));
        if(book.getDate() != null)
            borderPane.setRight(new Text(book.getDate().toString()));
        else
            borderPane.setRight(new Text("-"));

        listView.getItems().add(borderPane);
    }
}
