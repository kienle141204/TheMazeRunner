package org.example.themazerunner;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Audio {
    private static Media clickSoundMedia;

    static {
        loadClickSound();
    }

    private static void loadClickSound() {
        String clickSoundFilePath = "E:\\code\\TheMazeRunner\\src\\main\\java\\sound\\clicksound.wav";
        clickSoundMedia = new Media(new File(clickSoundFilePath).toURI().toString());
    }

    public static void playClickSound() {
        if (clickSoundMedia != null) {
            MediaPlayer clickMediaPlayer = new MediaPlayer(clickSoundMedia);
            clickMediaPlayer.play();
            clickMediaPlayer.setOnEndOfMedia(clickMediaPlayer::dispose);
        }
    }

}
