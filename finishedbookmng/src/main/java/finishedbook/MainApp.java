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
    public ModifyViewController modifyViewController;

    @SuppressWarnings("exports")
    public Parent listView;
    @SuppressWarnings("exports")
    public Parent addView;
    @SuppressWarnings("exports")
    public Parent modifyView;


    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        loadListView("listView");
        loadAddView("addView");
        loadModifyView("modifyView");
        loadDataLoader("bookList");

        
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
    private void loadModifyView(String modifyViewName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + modifyViewName + ".fxml"));
        this.modifyView = fxmlLoader.load();
        this.modifyViewController = fxmlLoader.getController();
        this.modifyViewController.setMainApp(this);
    }
    private void loadDataLoader(String fileName){
        this.dataLoader = new DataLoader(System.getProperty("user.dir") + "/src/data/"+fileName+".txt");
        this.dataLoader.loadData();
        this.bookList = dataLoader.getBookList();
    }

    public void showView(@SuppressWarnings("exports") Parent view){
        Platform.runLater(()->rootPane.getChildren().add(view));
    }
    public void hideView(@SuppressWarnings("exports") Parent view){
        Platform.runLater(()->rootPane.getChildren().remove(view));
    }

    public void addBook(Book book){
        bookList.add(book);
    }
    public void removeBook(Book book){
        Book targetBook = null;
        for(Book tmpBook : bookList){
            if(tmpBook.getTitle().equals(book.getTitle())){
                targetBook = tmpBook;
                break;
            }
        }
        if(targetBook!= null){
            bookList.remove(targetBook);
        }
    }

    @Override
    public void stop() {
        dataLoader.saveData();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
