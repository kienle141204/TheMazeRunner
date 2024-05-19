package org.example.themazerunner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.Map;

public class Game4 extends Application {
    private double canvasWidth = 1000;
    private double canvasHeight = 750;
    private MazeDrawer mazeDrawer;
    private GraphicsContext gc;
    @Override
    public void start(Stage primaryStage) {
        try {
            int RECT_SIZE = 32; // kích thước
            int[][] mazeData = {
                    {9,9,9,9,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,0,0,1,0,1,-4,1,0,1,0,0,0,0,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,9,9,9,9},
                    {9,9,9,9,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,9,9,9,9},
                    {9,9,9,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,9,9},
            };
            int characterX = RECT_SIZE* (mazeData[0].length/2); // vị trí ban đầu

            Pane root = new Pane() ;
            MazeDisplayer maze1 = new MazeDisplayer(root, mazeData,RECT_SIZE,characterX) ;
            Scene scene = maze1.getSceneMaze4(1000, 750) ;
            primaryStage.setTitle("Maze4");
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