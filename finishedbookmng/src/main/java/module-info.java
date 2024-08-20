module finishedbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    opens finishedbook to javafx.fxml;
    exports finishedbook;
}