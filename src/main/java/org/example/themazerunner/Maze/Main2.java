package org.example.themazerunner.Maze;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.example.themazerunner.UI.Audio;



public class Main2 extends Application {
    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private Character character;
    static Pane gamePane;
    private int[][] mazeData;
    private double canvasWidth = 1000;
    private double canvasHeight = 750;
    private MazeDrawer mazeDrawer;
    private List<Item> items = new ArrayList<>();
    private GraphicsContext gc;
    public static ArrayList<Block> walls = new ArrayList<>();
    private String path;
    public static ArrayList<Rectangle> telegates = new ArrayList<>();
    private ImageView imageView;
    private boolean levelCompleted = false;
    private AnimationTimer timer;
    private Rectangle telegate;
    private List<ImageView> blackholds = new ArrayList<>();
    private Audio audio;

    public Main2(int [][] mazeData, String path){
        this.mazeData = mazeData;
        this.path = path;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dark Maze");
        Image icon = new Image(Links.MAZEICON);
        primaryStage.getIcons().add(icon);

        // Tạo scene giới thiệu
        Pane introPane = new Pane();
        Image introBackgroundImage = new Image(path);
        ImageView introBackgroundView = new ImageView(introBackgroundImage);
        introBackgroundView.setFitWidth(canvasWidth);
        introBackgroundView.setFitHeight(canvasHeight);
        introBackgroundView.setPreserveRatio(false);
        introPane.getChildren().add(introBackgroundView);
        Scene introScene = new Scene(introPane, canvasWidth, canvasHeight);

        // Kiểm tra sự kiện
        introScene.setOnMouseClicked(event -> {
            primaryStage.setScene(createGameScene(primaryStage));
            primaryStage.show();
        });

        primaryStage.setScene(introScene);
        primaryStage.show();
    }

    private Scene createGameScene(Stage primaryStage) {
        gamePane = new Pane();
        gamePane.getChildren().clear();

        Pane backgroundPane = new Pane();

        gamePane.getChildren().addAll(backgroundPane);

        Image icon = new Image(Links.MAZEICON);

        primaryStage.getIcons().add(icon);

        Image backgroundImage = new Image(Links.bgJUNGLEMAZE1);
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundPane.getChildren().add(backgroundView);

        // Đặt kích thước phù hợp cho ImageView
        backgroundView.setFitWidth(1000);
        backgroundView.setFitHeight(750);
        backgroundView.setPreserveRatio(false);


        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        gc = canvas.getGraphicsContext2D();
        mazeDrawer = new MazeDrawer(mazeData, canvasWidth, canvasHeight);


        ImageView characterImage = new ImageView(Links.CHARACTER);
        character = new Character(characterImage, mazeDrawer);

        gamePane.getChildren().addAll(canvas, character);
        Image teleImage = new Image(Links.TELE);

        for (int i = 0; i < mazeDrawer.getRows(); i++) {
            for (int j = 0; j < mazeDrawer.getColumns(); j++) {
                if (mazeData[i][j] == 0) {
                    Block wall = new Block(j * mazeDrawer.getCellSizeWidth(), i * mazeDrawer.getCellSizeHeight(),mazeDrawer);

                }
                else if (mazeData[i][j] == 3){
                    telegate =new Rectangle(j * mazeDrawer.getCellSizeWidth(), i * mazeDrawer.getCellSizeHeight(), mazeDrawer.getCellSizeWidth(), mazeDrawer.getCellSizeHeight());
                    gamePane.getChildren().add(telegate);
                    telegates.add(telegate);
                    ImageView blackhold = new ImageView(teleImage);
                    blackhold.setFitWidth(mazeDrawer.getCellSizeWidth());
                    blackhold.setFitHeight(mazeDrawer.getCellSizeHeight());
                    gamePane.getChildren().add(blackhold);
                    blackholds.add(blackhold);
                    blackhold.setTranslateX(j * mazeDrawer.getCellSizeWidth());
                    blackhold.setTranslateY(i * mazeDrawer.getCellSizeHeight());

                }
            }
        }


        Item revealItem = new Item(420 , 300,30,30); // Initialize the item position correctly
        gamePane.getChildren().add(revealItem);
        items.add(revealItem);

        Item revealItem1 = new Item(100 , 100,30,30); // Initialize the item position correctly
        gamePane.getChildren().add(revealItem1);
        items.add(revealItem1);

        Image gateImage = new Image(Links.GATE_PATH);
        imageView = new ImageView(gateImage);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        gamePane.getChildren().add(imageView);
        imageView.setTranslateX(500);
        imageView.setTranslateY(718);


        Scene scene = new Scene(gamePane, canvasWidth, canvasHeight);

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(canvas.getGraphicsContext2D());
                if(!revealItem.isTimerRunning() && !revealItem1.isTimerRunning()) mazeDrawer.highlightAreaAroundCharacter(gc, character.getX(), character.getY(), 100);
                else mazeDrawer.drawFullMap(gc);
                checkItemCollision();
                updateItemVisibility() ;
                checktelegateCollision();
                updatetelegateVisibility();
                checkLevelCompletion(primaryStage);
            }
        };
        timer.start();
        Scene gameScene = primaryStage.getScene();
        scene.setRoot(gamePane);
        gameScene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        gameScene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        return gameScene;
    }



    public void update(GraphicsContext gc) {
        if (isPressed(KeyCode.W)) {

            character.animation.play();
            character.animation.setOffsetY(96);
            character.moveY(-1);

        } else if (isPressed(KeyCode.S)) {

            character.animation.play();
            character.animation.setOffsetY(0);
            character.moveY(1);

        } else if (isPressed(KeyCode.D)) {

            character.animation.play();
            character.animation.setOffsetY(64);
            character.moveX(1);

        } else if (isPressed(KeyCode.A)) {

            character.animation.play();
            character.animation.setOffsetY(32);
            character.moveX(-1);

        } else {
            character.animation.stop();
        }
    }

    private void updateItemVisibility() {
        for (Item item : items) {
            if (character.getVisionBox().getBoundsInParent().intersects(item.getHitbox().getBoundsInParent())
                    && !(character.getHitbox().getBoundsInParent().intersects(item.getHitbox().getBoundsInParent()))) {
                item.setItemVisible(true);
            } else {
                item.setItemVisible(false);
            }
        }
    }

    private void checkItemCollision() {
        for (Item item : items) {
            if (character.getHitbox().getBoundsInParent().intersects(item.getHitbox().getBoundsInParent())) {
                System.out.println(character.getBoundsInParent());
                if (item.getItemVisible()) {
                    item.disappear();
                    item.startTimer();
                }
            }
        }
    }
    private void checktelegateCollision() {
        for (Rectangle telegate : telegates) {
            if (character.getHitbox().getBoundsInParent().intersects(telegate.getBoundsInParent())) {
                spawnCharacterRandomly();
            }
        }
    }

    private void spawnCharacterRandomly() {
        // Lặp cho đến khi tìm được một vị trí hợp lệ cho spawn
        while (true) {
            // Random vị trí
            int min = 0;
            int max = mazeData.length;

            // Tạo số ngẫu nhiên trong phạm vi từ min đến max (bao gồm cả max)
            int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

            // Kiểm tra xem vị trí có hợp lệ không (không nằm trên tường)
            if (mazeData[randomNumber][randomNumber]==1) {
                character.setTranslateX(randomNumber *mazeDrawer.getCellSizeWidth());
                character.setTranslateY(randomNumber *mazeDrawer.getCellSizeHeight());
                character.setX(randomNumber*mazeDrawer.getCellSizeWidth());
                character.setY(randomNumber*mazeDrawer.getCellSizeHeight());
                character.updateHitbox();
                character.updateVisionBox();
                break; // Thoát khỏi vòng lặp khi tìm được vị trí hợp lệ
            }
        }
    }
    private void updatetelegateVisibility() {
        for (ImageView blackhold : blackholds) {
            if (character.getVisionBox().getBoundsInParent().intersects(blackhold.getBoundsInParent())
                    && !(character.getHitbox().getBoundsInParent().intersects(blackhold.getBoundsInParent()))) {
                blackhold.setVisible(true);
            } else {
                blackhold.setVisible(false);
            }
        }
    }
    private void checkLevelCompletion(Stage primaryStage) {
        if (character.getHitbox().getBoundsInParent().intersects(imageView.getBoundsInParent())) {
            levelCompleted = true;
            timer.stop();

            //showWinMessage(primaryStage);
            if(mazeData == Data.mazeData1){
                showWinMessage(primaryStage);
            }
            else{
                showWinFinal();
            }
        }
    }


    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    private void showWinMessage(Stage primaryStage) {
        // Tạo một Stage mới cho cửa sổ thông báo tùy chỉnh
        Stage alertStage = new Stage();
        alertStage.initStyle(StageStyle.UNDECORATED);
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initOwner(primaryStage);

        // Tạo thanh tiêu đề tùy chỉnh
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #2C3E50; -fx-padding: 5px;");
        titleBar.setAlignment(Pos.CENTER_RIGHT);

        // Tạo nội dung thông báo
        Label messageLabel = new Label();
        Button okButton = new Button();
        Image okImage = new Image(Links.OKBUTTON_PATH);
        ImageView okImageView = new ImageView(okImage);
        okImageView.setFitWidth(100);  // Điều chỉnh kích thước hình ảnh nếu cần
        okImageView.setFitHeight(50);
        okButton.setGraphic(okImageView);

        VBox contentLayout = new VBox(110, messageLabel, okButton);
        contentLayout.setAlignment(Pos.CENTER);
        okButton.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-padding: 0;");
        okButton.setOnAction(e -> {
            primaryStage.close();
            alertStage.close();
        });
        contentLayout.setPadding(new Insets(20));

        // Tạo hình ảnh nền và thiết lập nền cho ô thông báo
        Image backgroundImage = new Image(Links.CONGRAT);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        contentLayout.setBackground(background);

        // Tạo bố cục chính và thêm các phần tử vào đó
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(contentLayout);

        Scene scene = new Scene(root, 400, 200);
        alertStage.setScene(scene);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        alertStage.setX((screenBounds.getWidth() - 300) / 2);  // Căn giữa theo chiều ngang
        alertStage.setY((screenBounds.getHeight() - 200) / 2);

        alertStage.show();
    }


    private void showWinFinal(){
        Stage currentStage = (Stage) gamePane.getScene().getWindow();
        Stage videoStage = new Stage();

        // Phát video
        Media videoMedia = new Media(new File(Links.END).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(videoMedia);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Khi video kết thúc, đóng Stage chứa video và hiển thị lại primaryStage
        mediaPlayer.setOnEndOfMedia(() -> {
            videoStage.close();

        });
        // Thêm MediaView vào Scene và hiển thị Stage chứa video
        StackPane videoLayout = new StackPane(mediaView);
        Scene videoScene = new Scene(videoLayout, 1000, 750);
        videoStage.setScene(videoScene);
        videoStage.show();
        mediaPlayer.play();
        currentStage.close();
    }

}