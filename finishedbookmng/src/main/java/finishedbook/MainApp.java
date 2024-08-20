package finishedbook;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.List;

import java.io.IOException;

public class MainApp extends Application {
    
    private DataLoader dataLoader;
    private List<Book> bookList;
    
    
    private Stage stage;
    private Scene scene;
    private AnchorPane rootPane;
    public ListViewController listViewController;
    public AddViewController addViewController;

    @SuppressWarnings("exports")
    public Parent listView;
    @SuppressWarnings("exports")
    public Parent addView;


    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        loadListView("listView");
        loadAddView("addView");
        loadDataLoader("bookList");

        listViewController.setMainApp(this);
        addViewController.setMainApp(this);
        
        this.rootPane = new AnchorPane();
        rootPane.setPrefSize(500, 700);
        Platform.runLater(()->rootPane.getChildren().add(listView));
        
        scene = new Scene(rootPane);
        stage = s;

        listViewController.cstInit(bookList);

        stage.setTitle("읽은 책 목록 관리");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    private void loadListView(String listViewName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + listViewName + ".fxml"));
        this.listView = fxmlLoader.load();
        this.listViewController = fxmlLoader.getController();
        this.listViewController.setMainApp(this);
    }
    private void loadAddView(String addViewName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + addViewName + ".fxml"));
        this.addView = fxmlLoader.load();
        this.addViewController = fxmlLoader.getController();
        this.addViewController.setMainApp(this);
    }
    private void loadDataLoader(String fileName){
        this.dataLoader = new DataLoader(System.getProperty("user.dir") + "/src/data/"+fileName+".txt");
        this.dataLoader.loadData();
        this.bookList = dataLoader.getBookList();
    }

    public void showAddView(){
        Platform.runLater(()->rootPane.getChildren().add(addView));
    }
    public void hideAddView(){
        Platform.runLater(()->rootPane.getChildren().remove(addView));
    }

    @Override
    public void stop() {
        dataLoader.saveData();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
