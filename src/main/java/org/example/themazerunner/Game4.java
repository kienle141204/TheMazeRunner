package org.example.themazerunner;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Main2 mainGame = new Main2(Data.mazeData1,Links.G3);
            mainGame.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
