package org.example.themazerunner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo một nút để phát âm thanh
        Button playButton = new Button("Phát âm thanh");

        // Tạo đường dẫn tới file âm thanh
        String filePath = "E:\\code\\TheMazeRunner\\src\\main\\java\\sound\\soundtrack.mp3";
        Media sound = new Media(new File(filePath).toURI().toString());

        // Tạo một đối tượng MediaPlayer từ Media
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // Sự kiện khi nhấn vào nút phát âm thanh
        playButton.setOnAction(event -> mediaPlayer.play());

        // Tạo layout
        VBox root = new VBox(playButton);
        Scene scene = new Scene(root, 300, 200);

        // Thiết lập cửa sổ ứng dụng
        primaryStage.setScene(scene);
        primaryStage.setTitle("Phát Âm Thanh trong JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



