package com.example.view.music;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum Music {
    LOGIN_MENU ("login_menu"),
    HAPPY_TIMES ("happy_times"),
    SAD_TIMES ("sad_times"),
    STIX_STONE_MELDEY ("stix_stones_medley");

    private final MediaPlayer mediaPlayer;

    private Music(String name) {
        URL url = getClass().getResource("/music/" + name + ".mp3");
        Media media = new Media(url.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}