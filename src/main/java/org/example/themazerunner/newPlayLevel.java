package org.example.themazerunner;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;

import java.io.File;
import java.util.Locale;

public class newPlayLevel {
    private Scene sceneNewPlatlevel;
    private ImageView character;
    private static double SPEED = 8;
    private int currentFrame = 0;
    private Button game1;
    private Button game2;
    private Button game3;
    private Button game4;
    private Button game5;
    private Timeline timeline;
    private StackPane layout = new StackPane();
    ImageView presseView;
    private boolean pressed = false;
    private Stage GamePlaystage;
    private MenuGame menuGame = new MenuGame();
    private Audio audio;



    public newPlayLevel(Stage primaryStage, Scene scene1,StackPane layout)  {
        this.layout = layout;
        GamePlaystage = new Stage();
        Image background = new Image("file:E:/code/TheMazeRunner/src/main/java/image/newplaylv1.png");
        ImageView backgroundView = new ImageView(background);
        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());// làm như này thì nền mới full cửa sổ được
        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());
        layout.getChildren().add(backgroundView);

        sceneNewPlatlevel = new Scene(layout, 1000, 750);

        // Tạo nút Back
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image image = new Image("file:E:/code/TheMazeRunner/src/main/java/image/back.png");
        ImageView imageView = new ImageView(image);
        backButton.setGraphic(imageView);
        layout.getChildren().add(imageView);
        layout.getChildren().add(backButton);

        sceneNewPlatlevel.getStylesheets().add("file:E:/code/TheMazeRunner/src/main/java/style.css");
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));
        /////////////////////////////
        //MenuGame menuGame = new MenuGame();
        //menuGame.pauseMediaPlayer();
        // Hiệu ứng cho nút Back
        ButtonScaleEffect.addScaleEffect(backButton);

        backButton.setOnAction(event -> {
            audio.playClickSound();
            menuGame.playBackgroundMediaPlayer();
            menuGame.start(primaryStage);
            //primaryStage.setScene(scene1);
        });
    }

    public void setCharacter(Stage primaryStage){
        character = new ImageView(new Image("file:E:/code/TheMazeRunner/src/main/java/image/hold.png"));
        SpriteAnimation animation = new SpriteAnimation(character,
                Duration.millis(1000), 3, 64, 64);
        animation.setCycleCount(javafx.animation.Animation.INDEFINITE);
        animation.play();
        character.setFitWidth(64);
        character.setFitHeight(64);
        StackPane.setAlignment(character, Pos.BOTTOM_LEFT);
        StackPane.setMargin(character,new Insets(0, 0, 240,100 ));
        layout.getChildren().add(character);

        Game1 playgame1 = new Game1();
        Game2 playgame2 = new Game2();
        Game3 playgame3 = new Game3();
        Game4 playgame4 = new Game4();
        Game5 playgame5 = new Game5();
        sceneNewPlatlevel.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    moveCharacter(0,-SPEED);
                    updateFrame(0,1,2,3);
                    break;
                case S:
                    moveCharacter(0,SPEED);
                    updateFrame(4,5,6,7);
                    break;
                case A:
                    moveCharacter(-SPEED,0);
                    updateFrame(8,9,10,11);
                    break;
                case D:
                    moveCharacter(SPEED,0);
                    updateFrame(12,13,14,15);
                    break;
                case E:
                    audio.playClickSound();
                    if (character.getBoundsInParent().intersects(game1.getBoundsInParent())) {
                        // Thực hiện hành động khi nhân vật giao với nút game
                        playgame1.start(GamePlaystage);
                        primaryStage.hide();
                        GamePlaystage.setOnHidden(e -> {
                            // Hiển thị lại primaryStage
                            primaryStage.show();
                        });
                    }
                    else if(character.getBoundsInParent().intersects(game2.getBoundsInParent())) {
                        playgame2.start(GamePlaystage);
                        primaryStage.hide();
                        GamePlaystage.setOnHidden(e -> {
                            // Hiển thị lại primaryStage
                            primaryStage.show();
                        });
                    }
                    else if(character.getBoundsInParent().intersects(game3.getBoundsInParent())) {
                        playgame3.start(GamePlaystage);
                        primaryStage.hide();
                        GamePlaystage.setOnHidden(e -> {
                            // Hiển thị lại primaryStage
                            primaryStage.show();
                        });
                    }
                    else if(character.getBoundsInParent().intersects(game4.getBoundsInParent())) {
                        playgame4.start(GamePlaystage);
                        primaryStage.hide();
                        GamePlaystage.setOnHidden(e -> {
                            // Hiển thị lại primaryStage
                            primaryStage.show();
                        });
                    }
                    else if(character.getBoundsInParent().intersects(game5.getBoundsInParent())) {
                        playgame5.start(GamePlaystage);
                        primaryStage.hide();
                        GamePlaystage.setOnHidden(e -> {
                            // Hiển thị lại primaryStage
                            primaryStage.show();
                        });
                    }
                    else{
                        System.out.println("khong the chon");
                    }
                    break;
                default:
                    updateFrame(16);
                    break;
            }
        });
        timeline = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
            updateFrame(16);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        /////

        Image presse = new Image("file:E:/code/TheMazeRunner/src/main/java/image/pressE.png");
        presseView = new ImageView(presse);
        presseView.setVisible(false);
        layout.getChildren().add(presseView);
        presseView.setTranslateX(0);
        presseView.setTranslateY(350);
    }
    public void setPlayGameButton() {
        game1 = new Button();
        game2 = new Button();
        game3 = new Button();
        game4 = new Button();
        game5 = new Button();
        Button[] games = {game1, game2, game3, game4, game5};
        for (Button game : games) {
            game.setPrefSize(100, 100);
        }
        Image gamebutton = new Image("file:E:/code/TheMazeRunner/src/main/java/image/gamebutton.png");
        Image flag = new Image("file:E:/code/TheMazeRunner/src/main/java/image/flag.png");
        ImageView flagView = new ImageView(flag);
        for (Button game : games) {
            ImageView gameView = null;
            if( game != game5){
                gameView = new ImageView(gamebutton);
            } else {
                gameView = new ImageView(flag);
            }
            gameView.setFitHeight(100);
            gameView.setFitWidth(100);
            game.setGraphic(gameView);
            StackPane.setAlignment(game, Pos.BOTTOM_LEFT);
            layout.getChildren().add(game);
        }

        StackPane.setMargin(game1, new Insets(0, 0, 140,300 ));
        StackPane.setMargin(game2, new Insets(0, 0, 370,300 ));
        StackPane.setMargin(game3, new Insets(0, 0, 240,455 ));
        StackPane.setMargin(game4, new Insets(0, 0, 130,630 ));
        StackPane.setMargin(game5, new Insets(0, 0, 250,800 ));
    }
    private void moveCharacter(double dx, double dy) {
        double newTranslateX = character.getTranslateX() + dx;
        double newTranslateY = character.getTranslateY() + dy;

        // Kiểm tra giới hạn di chuyển của nhân vật (không cho nhân vật di chuyển ra khỏi ranh giới của bản đồ)
        if (newTranslateX >= 0 && newTranslateX <= 800 && newTranslateY >= -190 && newTranslateY <= 150) {
            character.setTranslateX(newTranslateX);
            character.setTranslateY(newTranslateY);
        }
        Button[] games = {game1, game2, game3, game4, game5};
        boolean isPress = false;
        for(Button game : games){
            double gameCenterX = game.getBoundsInParent().getMinX() + game.getBoundsInParent().getWidth() / 2;
            double gameCenterY = game.getBoundsInParent().getMinY() + game.getBoundsInParent().getHeight() / 2 -20;

            // Kiểm tra xem tâm của nút game có nằm trong giới hạn của nhân vật không
            if (character.getBoundsInParent().contains(gameCenterX, gameCenterY)) { //  hàm contains kiểm tra gameCenterX Y có nằm trong bound của character không
                // Nhân vật đang đứng trên button
                game.setScaleX(1.2);
                game.setScaleY(1.2);
                isPress = true;
            } else {
                game.setScaleX(1.0);
                game.setScaleY(1.0);

            }


        }
        if(isPress){
            presseView.setVisible(true);
        }
        else{
            presseView.setVisible(false);
        }
    }
    public void updateFrame(int... frameIndices) {
        currentFrame = (currentFrame + 1) % frameIndices.length;
        int frameIndex = frameIndices[currentFrame];
        String filePath = "E:\\code\\TheMazeRunner\\src\\main\\java\\sound\\footstep.wav";
        Media soundFootstep = new Media(new File(filePath).toURI().toString());
        MediaPlayer footstep = new MediaPlayer(soundFootstep);

        // Tạo đường dẫn tới tập tin ảnh mới dựa trên hành động
        String imagePath = "";
        switch (frameIndex) {
            case 0: // Di chuyển lên
                imagePath = "file:E:/code/TheMazeRunner/src/main/java/image/up.png";
                footstep.play();
                break;
            case 4: // Di chuyển xuống
                imagePath = "file:E:/code/TheMazeRunner/src/main/java/image/down.png";
                footstep.play();
                break;
            case 8: // Di chuyển qua trái
                imagePath = "file:E:/code/TheMazeRunner/src/main/java/image/left.png";
                footstep.play();
                break;
            case 12: // Di chuyển qua phải
                imagePath = "file:E:/code/TheMazeRunner/src/main/java/image/right.png";
                footstep.play();
                break;
            case 16:
                imagePath = "file:E:/code/TheMazeRunner/src/main/java/image/hold.png";
                break;
            default:
                break;
        }

        // Nếu đường dẫn hợp lệ, cập nhật hình ảnh của characterImageView
        if (!imagePath.isEmpty()) {
            Image spriteSheet = new Image(imagePath);
            character.setImage(spriteSheet);
        }
    }

    public Scene getScene() {
        return sceneNewPlatlevel;
    }
}