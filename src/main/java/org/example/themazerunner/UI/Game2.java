package org.example.themazerunner.UI;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.example.themazerunner.Maze.Links;
import org.example.themazerunner.Maze.MazeDisplayer;
import org.example.themazerunner.Maze.Data;


public class Game2 extends Application {
    private MazeDisplayer maze2;
    private Scene scene;
    private Audio audio;
    @Override
    public void start(Stage primaryStage) {
        Image icon = new Image(Links.MAZEICON);

        primaryStage.getIcons().add(icon);
        try {
            int RECT_SIZE = 36; // kích thước
            int[][] mazeData = Data.mazeData2;
            int characterX = RECT_SIZE* (mazeData[0].length/2 ); // vị trí ban đầu character

            Pane root = new Pane() ;
            // thêm cốt truyện vào từng game
            Image coverImage = new Image(Links.G1);
            ImageView coverImageView = new ImageView(coverImage);
            coverImageView.setFitWidth(1000); // Đặt kích thước phù hợp với cửa sổ trò chơi
            coverImageView.setFitHeight(750); // Đặt kích thước phù hợp với cửa sổ trò chơi

            maze2 = new MazeDisplayer(root, mazeData,RECT_SIZE,characterX);
            scene = maze2.getSceneMaze2(1000, 750) ;
            root.getChildren().add(coverImageView);
            scene.setOnMouseClicked(event -> {
                // Biến mất cốt truyện
                coverImageView.setVisible(false);
            });
            //MazeDisplayer maze1 = new MazeDisplayer(root, mazeData,RECT_SIZE,characterX) ;
            //Scene scene = maze1.getSceneMaze2(1000, 750) ;
            audio.playSoundTrack();
            primaryStage.setTitle("Maze2");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}