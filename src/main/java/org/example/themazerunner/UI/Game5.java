package org.example.themazerunner.UI;


import javafx.application.Application;
import javafx.stage.Stage;
import org.example.themazerunner.Maze.Data;
import org.example.themazerunner.Maze.Links;
import org.example.themazerunner.Maze.Main2;


public class Game5 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Main2 mainGame = new Main2(Data.mazeData2, Links.G4);
            mainGame.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}