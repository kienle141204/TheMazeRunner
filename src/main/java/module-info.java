module org.example.themazerunner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.almasb.fxgl.all;

    opens org.example.themazerunner to javafx.fxml;
    exports org.example.themazerunner;
}