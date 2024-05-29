package org.example.themazerunner.UI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import org.example.themazerunner.Maze.Links;

public class Audio {
    private static Media clickSoundMedia;
    private static Media videoMedia;// Thêm đối tượng Media cho video
    private static Media soundTrack;
    private static Media footStep;

    static {
        loadClickSound();
        loadVideo();
        loadSoundTrack();
        // Gọi phương thức tải video khi tải lớp Audio
    }
    public static void playFootStep(){
        String fs = Links.FOOTSTEP_PATH;
        footStep = new Media(new File(fs).toURI().toString());
        if (footStep != null) {
            MediaPlayer fsMediaPlayer = new MediaPlayer(footStep);
            fsMediaPlayer.play();
            fsMediaPlayer.setOnEndOfMedia(fsMediaPlayer::dispose);
        }
    }

    private static void loadClickSound() {
        String clickSoundFilePath = Links.CLICKSOUND_PATH;
        clickSoundMedia = new Media(new File(clickSoundFilePath).toURI().toString());
    }

    private static void loadVideo() {
        String videoFilePath = Links.VIDEO_PATH; // Đường dẫn đến file video
        videoMedia = new Media(new File(videoFilePath).toURI().toString());
    }

    public static void playClickSound() {
        if (clickSoundMedia != null) {
            MediaPlayer clickMediaPlayer = new MediaPlayer(clickSoundMedia);
            clickMediaPlayer.play();
            clickMediaPlayer.setOnEndOfMedia(clickMediaPlayer::dispose);
        }
    }

    // Phương thức để phát video
    public static void playVideo() {
        if (videoMedia != null) {
            MediaPlayer videoMediaPlayer = new MediaPlayer(videoMedia);
            videoMediaPlayer.play();
        }
    }

    private static  void loadSoundTrack(){
        String soundTrackPath = Links.SOUNDTRACK_PATH;
        soundTrack = new Media(new File(soundTrackPath).toURI().toString());
    }

    public static void playSoundTrack(){
        if ( soundTrack!= null) {
            MediaPlayer soundTrackMedia = new MediaPlayer(soundTrack);
            soundTrackMedia.play();
            soundTrackMedia.setOnEndOfMedia(() -> {
                // Đặt thời gian của mediaPlayer về thời điểm bắt đầu của âm thanh
                soundTrackMedia.seek(soundTrackMedia.getStartTime());
                // Phát lại âm thanh
                soundTrackMedia.play();
            });

        }
    }
}
