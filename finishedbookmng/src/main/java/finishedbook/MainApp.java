package finishedbook;

import javafx.application.Application;
import javafx.application.Platform;
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
    
    private Label countLabel;
    private FXMLController controller;

    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+"layout" + ".fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        controller = fxmlLoader.getController();
        stage=s;

        dataLoader = new DataLoader(System.getProperty("user.dir")+"/src/main/java/finishedbook/BookList.txt");
        dataLoader.loadData();
        bookList = dataLoader.getBookList();
  

        countLabel = controller.getCountLabel();
        Platform.runLater(()->{countLabel.setText(bookList.size()+" 권");});

        stage.setTitle("읽은 책 목록 관리");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        dataLoader.saveData();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
