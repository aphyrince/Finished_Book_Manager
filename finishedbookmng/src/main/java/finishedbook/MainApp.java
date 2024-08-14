package finishedbook;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.List;

import java.io.IOException;


public class MainApp extends Application {
    private static Stage stage;

    private DataLoader dataLoader;
    private List<Book> bookList;

    @FXML
    private Label countLabel;

    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        stage=s;
        dataLoader = new DataLoader(System.getProperty("user.dir")+"/finishedbookmng/src/main/java/finishedbook/BookList.txt");
        dataLoader.loadData();
        bookList = dataLoader.getBookList();

        Platform.runLater(()->{
            countLabel.setText(bookList.size()+" 권");
        });

        setRoot("layout","읽은 책 목록 관리");
    }

    @Override
    public void stop(){
        dataLoader.saveData();
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
