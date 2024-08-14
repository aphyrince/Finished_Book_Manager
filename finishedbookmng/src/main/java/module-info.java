module finishedbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens finishedbook to javafx.fxml;
    exports finishedbook;
}