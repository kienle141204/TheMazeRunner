package org.example.themazerunner.UI;


import javafx.application.Application; // thư viện bắt buộc phải có
import javafx.application.Platform;
import javafx.geometry.Pos; // để điều chỉnh các box theo tọa độ
import javafx.scene.layout.StackPane; //tạo stackpane , giúp căn chỉnh nút (một phần nhỏ)
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button; // tạo nút và điều khiển nút
import javafx.scene.layout.HBox; // box ngang
import javafx.scene.layout.VBox;//box dọc
import javafx.scene.image.Image;// quản lý ảnh , nhưng không hiển thị ra giao diện người dùng
import javafx.scene.image.ImageView; // đưa ảnh ra giao diện người dùng
import javafx.animation.ScaleTransition; // hiệu ứng khi trỏ vào nút
import javafx.util.Duration; // điều khiển các hoạt động liên quan đến thời gian
import javafx.geometry.Insets; // chỉnh css ,lề , linh tinh
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.themazerunner.Maze.Links;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


// Kiên : máy tôi chẳng hiểu sao không chạy được liên kết file theo đường dẫn tương đối
// ae pull về thì đổi lại đường dẫn của các file rồi chạy nhé

public class MenuGame extends Application {
    public Stage primaryStage;
    public Scene scene1;
    private List<MediaPlayer> mediaPlayers = new ArrayList<>();
    private Audio audio;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Image icon = new Image(Links.MAZEICON);

        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Menu Game");
        playBackgroundMediaPlayer();
        setButton();

        // Mặc định khi mở chương trình là chạy scene1
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    private void setButton(){
        Button button = new Button();
        button.getStyleClass().add("button");
        Image name = new Image(Links.GAME_NAME_PATH);
        ImageView nameView = new ImageView(name);
        button.setGraphic(nameView);
        nameView.setFitWidth(500);
        nameView.setFitHeight(250);


        Button button1 = new Button();
        button1.getStyleClass().add("play-button");
        // Tải biểu tượng "play" từ tệp hình ảnh
        Image playIconImage = new Image(Links.PLAYBUTTON_PATH);
        ImageView playIconImageView = new ImageView(playIconImage);
        // Đặt biểu tượng "play" làm đồng hành của nút
        button1.setGraphic(playIconImageView);
        playIconImageView.setFitWidth(150); // Đặt chiều rộng tối đa
        playIconImageView.setFitHeight(75); // Đặt chiều cao tối đa

        Button button2 = new Button();
        button2.getStyleClass().add("help-button");
        Image helpIconImage = new Image(Links.HELPBUTTON_PATH);
        ImageView helpIconImageView = new ImageView(helpIconImage);
        button2.setGraphic(helpIconImageView);
        helpIconImageView.setFitWidth(150);
        helpIconImageView.setFitHeight(75);

        Button button3 = new Button();
        button3.getStyleClass().add("exit-button");
        Image exitIconImage = new Image(Links.EXITBUTTON_PATH);
        ImageView exitIconImageView = new ImageView(exitIconImage);
        button3.setGraphic(exitIconImageView);
        exitIconImageView.setFitWidth(150);
        exitIconImageView.setFitHeight(75);


        VBox vbox = new VBox(button,button1, button2, button3);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        StackPane.setMargin(vbox, new Insets(100, 0, 0, 0));

        StackPane layout1 = new StackPane();

        scene1 = new Scene(layout1, 1000, 750);

        scene1.getStylesheets().add(Links.CSS_PATH);
        Image image1 = new Image(Links.BACKGROUND_game);
        ImageView backgroundImage = new ImageView(image1);
        layout1.getChildren().add(backgroundImage);
        backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());// làm như này thì nền mới full cửa sổ được
        backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());
        layout1.getChildren().add(vbox);// 3 nút được hiển thị theo chiều dọc


        // Hiệu ứng ấn nút , to lên khi trỏ vào , nhỏ lại khi ra xa
        ButtonScaleEffect.addScaleEffect(button);
        ButtonScaleEffect.addScaleEffect(button1);
        ButtonScaleEffect.addScaleEffect(button2);
        ButtonScaleEffect.addScaleEffect(button3);


        button3.setOnAction(event -> {
            audio.playClickSound();
            Platform.exit(); // kết thúc chương trình
        });
        //Khởi tạo scene2 , đây là cửa sổ mở ra khi ấn help
        // cài đặt một vào thứ cho scene2
        HelpScene helpScene = new HelpScene(primaryStage, scene1);
        //Scene scene2 = helpScene.getScene();
        // Hiệu ứng khi ấn nút help==button2 và exit==button3
        button2.setOnAction(event ->{
            audio.playClickSound();
            primaryStage.setScene(helpScene.getScene());
        });
        //ấn và tên game hiện ra tên thành viên
        Member member = new Member(primaryStage, scene1);

        button.setOnAction(event->{
            audio.playClickSound();
            primaryStage.setScene(member.getScene());
        });

        button1.setOnAction(event -> {
            // Tạo một Stage mới cho video
            Stage videoStage = new Stage();

            // Phát video
            Media videoMedia = new Media(new File(Links.VIDEO_PATH).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(videoMedia);
            MediaView mediaView = new MediaView(mediaPlayer);

            // Khi video kết thúc, đóng Stage chứa video và hiển thị lại primaryStage
            mediaPlayer.setOnEndOfMedia(() -> {
                primaryStage.show();
                videoStage.close();
                StackPane layout = new StackPane();
                newPlayLevel playLevel = new newPlayLevel(primaryStage, scene1,layout);
                playLevel.setPlayGameButton();
                playLevel.setCharacter(primaryStage);
                primaryStage.setScene(playLevel.getScene());
            });

            // Thêm MediaView vào Scene và hiển thị Stage chứa video
            StackPane videoLayout = new StackPane(mediaView);
            Scene videoScene = new Scene(videoLayout, 1000, 750);
            videoStage.setScene(videoScene);
            videoStage.show();
            mediaPlayer.play();
            primaryStage.hide();

            // Tắt tất cả các phương tiện truyền thông khác nếu có
            stopAllMediaPlayers();

             //Chơi âm thanh khi ấn nút
            audio.playVideo();
            audio.playClickSound();
        });

    }
    public void playBackgroundMediaPlayer(){
        String filePath = Links.SOUNDTRACK_PATH;
        Media sound = new Media(new File(filePath).toURI().toString());

        // Tạo một đối tượng MediaPlayer từ Media
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayers.add(mediaPlayer);
        // Sự kiện khi âm thanh kết thúc
        mediaPlayer.setOnEndOfMedia(() -> {
            // Đặt thời gian của mediaPlayer về thời điểm bắt đầu của âm thanh
            mediaPlayer.seek(mediaPlayer.getStartTime());
            // Phát lại âm thanh
            mediaPlayer.play();
        });

        // Bắt đầu phát âm thanh
        mediaPlayer.play();
    }
    public void stopAllMediaPlayers() {
        for (MediaPlayer mediaPlayer : mediaPlayers) {
            if (mediaPlayer != null) {
                System.out.println("Stopping media player");
                mediaPlayer.stop();
            }
        }
        mediaPlayers.clear(); // Clear the list after stopping all media players
    }

    public static void main(String[] args) {
        launch(args);
    }
}
