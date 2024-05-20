package org.example.themazerunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelpScene {
    private Scene scene;
    private Audio audio;


    public HelpScene(Stage primaryStage,Scene scene1)  {

        StackPane layout = new StackPane();

        // Load hình ảnh background và hiển thị nó trong ImageView
        Image background = new Image("file:E:/code/TheMazeRunner/src/main/java/image/helpscene4.png");
        ImageView backgroundView = new ImageView(background);
        layout.getChildren().add(backgroundView);

        // Tạo ImageView để hiển thị nhân vật và thêm vào layout
        ImageView characterImageView = new ImageView();
        layout.getChildren().add(characterImageView);

        // Tạo nút Back và thêm vào layout
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image backImage = new Image("file:E:/code/TheMazeRunner/src/main/java/image/back.png");
        ImageView backImageView = new ImageView(backImage);
        backButton.setGraphic(backImageView);
        layout.getChildren().add(backButton);

        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));

        // Đặt vị trí ban đầu của nhân vật
        characterImageView.setTranslateX(220); // Vị trí x
        characterImageView.setTranslateY(-30); // Vị trí y

        // Đặt kích thước cho nhân vật
        double characterWidth = 750; // Chiều rộng mới
        double characterHeight = 750; // Chiều cao mới
        characterImageView.setFitWidth(characterWidth);
        characterImageView.setFitHeight(characterHeight);

        // Hiệu ứng cho nút Back
        ButtonScaleEffect.addScaleEffect(backButton);

        backButton.setOnAction(event -> {
            audio.playClickSound();
            primaryStage.setScene(scene1);
        });


        scene = new Scene(layout, 1000, 750);
        scene.getStylesheets().add("file:E:/code/TheMazeRunner/src/main/java/style.css");
    }

    public Scene getScene() {
        return scene;
    }
}
