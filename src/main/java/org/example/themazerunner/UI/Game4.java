package org.example.themazerunner.UI;


import javafx.application.Application;
import javafx.stage.Stage;
import org.example.themazerunner.Maze.Data;
import org.example.themazerunner.Maze.Links;
import org.example.themazerunner.Maze.Main2;
import org.example.themazerunner.UI.Audio;

public class Game4 extends Application {
    private Audio audio;
    private Main2 mainGame;
    @Override
    public void start(Stage primaryStage) {
        try {
            audio.playSoundTrack();
            mainGame = new Main2(Data.mazeData4, Links.G3);
            mainGame.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
