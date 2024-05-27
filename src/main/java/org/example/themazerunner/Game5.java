package org.example.themazerunner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;


public class Game5 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Main2 mainGame = new Main2(Data.mazeData2,Links.G4);
            mainGame.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}