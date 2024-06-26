package org.example.themazerunner.UI;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Member {
    private Scene scene;
    private Audio audio;

    public Member(Stage primaryStage, Scene scene1){

        StackPane layout = new StackPane();
        Image background = new Image("file:E:/code/TheMazeRunner/src/main/java/image/member.png");
        ImageView backgroundView = new ImageView(background);
        layout.getChildren().add(backgroundView);

        scene = new Scene(layout, 1000, 750);

        // Tạo nút Back
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image image = new Image("file:E:/code/TheMazeRunner/src/main/java/image/back.png");
        ImageView imageView = new ImageView(image);
        backButton.setGraphic(imageView);
        layout.getChildren().add(backButton);
        scene.getStylesheets().add("file:E:/code/TheMazeRunner/src/main/java/style.css");
        imageView.setFitHeight(75);
        imageView.setFitWidth(150);

        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));

        // Hiệu ứng cho nút Back
        ButtonScaleEffect.addScaleEffect(backButton);

        backButton.setOnAction(event -> {
            audio.playClickSound();
            primaryStage.setScene(scene1);
        });
    }
    public Scene getScene() {
        return scene;
    }
}
