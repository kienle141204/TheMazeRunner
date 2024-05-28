package org.example.themazerunner.UI;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import org.example.themazerunner.Maze.Links;
import org.example.themazerunner.Maze.MazeDisplayer;


public class Game2 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Image icon = new Image(Links.MAZEICON);

        primaryStage.getIcons().add(icon);
        try {
            int RECT_SIZE = 36; // kích thước
            int[][] mazeData = {
                    {9,9,9,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,9,9,9,9},
                    {9,9,9,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,3,0,9,9,9,9},
                    {9,9,9,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,1,4,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,0,1,0,0,0,1,-2,1,0,1,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,9,9,9,9},
                    {9,9,9,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,9,9,9,9},
                    {9,9,9,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,9,9,9,9},
                    {9,9,9,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9}
            };
            int characterX = RECT_SIZE* (mazeData[0].length/2 ); // vị trí ban đầu character

            Pane root = new Pane() ;
            // thêm cốt truyện vào từng game
            Image coverImage = new Image(Links.G1);
            ImageView coverImageView = new ImageView(coverImage);
            coverImageView.setFitWidth(1000); // Đặt kích thước phù hợp với cửa sổ trò chơi
            coverImageView.setFitHeight(750); // Đặt kích thước phù hợp với cửa sổ trò chơi

            MazeDisplayer maze2 = new MazeDisplayer(root, mazeData,RECT_SIZE,characterX);
            Scene scene = maze2.getSceneMaze2(1000, 750) ;
            root.getChildren().add(coverImageView);
            scene.setOnMouseClicked(event -> {
                // Biến mất cốt truyện
                coverImageView.setVisible(false);
            });
            //MazeDisplayer maze1 = new MazeDisplayer(root, mazeData,RECT_SIZE,characterX) ;
            //Scene scene = maze1.getSceneMaze2(1000, 750) ;
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