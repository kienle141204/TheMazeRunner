module org.example.themazerunner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens org.example.themazerunner to javafx.fxml;
    exports org.example.themazerunner.UI to javafx.graphics;
}