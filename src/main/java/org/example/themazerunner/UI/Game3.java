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


public class Game3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Image icon = new Image(Links.MAZEICON);

        primaryStage.getIcons().add(icon);
        try {
            int RECT_SIZE = 32; // kích thước
            int[][] mazeData = {
                    {9,9,9,9,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,1,1,1,1,1,1,1,1,3,1,0,1,0,1,0,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,1,0,0,0,1,0,1,-3,1,0,1,0,0,0,0,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},

            };
            //int[][] mazeData = Data.mazeData1;
            int characterX = RECT_SIZE* (mazeData[0].length/2); // vị trí ban đầu

            Pane root = new Pane() ;
            // thêm cốt truyện vào từng game
            Image coverImage = new Image(Links.G2);
            ImageView coverImageView = new ImageView(coverImage);
            coverImageView.setFitWidth(1000); // Đặt kích thước phù hợp với cửa sổ trò chơi
            coverImageView.setFitHeight(750); // Đặt kích thước phù hợp với cửa sổ trò chơi

            MazeDisplayer maze3 = new MazeDisplayer(root, mazeData,RECT_SIZE,characterX);
            Scene scene = maze3.getSceneMaze3(1000, 750) ;
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