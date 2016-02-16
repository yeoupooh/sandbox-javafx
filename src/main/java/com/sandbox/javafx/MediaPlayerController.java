package com.sandbox.javafx;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by yeoupooh on 2/16/16.
 */
public class MediaPlayerController implements Initializable {
    private MediaPlayer mediaPlayer;
    private Duration mediaDuration;

    public MediaPlayerController(String message) {

    }


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="textFieldUrl"
    private TextField textFieldUrl; // Value injected by FXMLLoader

    @FXML // fx:id="buttonStop"
    private Button buttonStop; // Value injected by FXMLLoader

    @FXML // fx:id="buttonPlay"
    private Button buttonPlay; // Value injected by FXMLLoader


    @FXML // fx:id="labelStatus"
    private Label labelStatus; // Value injected by FXMLLoader


    @FXML // fx:id="sliderCurrentTime"
    private Slider sliderCurrentTime; // Value injected by FXMLLoader

    @FXML // fx:id="labelCurrentTime"
    private Label labelCurrentTime; // Value injected by FXMLLoader


    @FXML // fx:id="mediaView"
    private MediaView mediaView; // Value injected by FXMLLoader

    @FXML // fx:id="buttonClose"
    private Button buttonClose; // Value injected by FXMLLoader

    @FXML
    void buttonCloseClicked(ActionEvent event) {
        stopMediaPlayer();
        buttonClose.getScene().getWindow().hide();
    }

    @FXML
    void buttonPlayClicked(ActionEvent event) {
        playMediaPlayer();
    }

    @FXML
    void buttonStopClicked(ActionEvent event) {
        stopMediaPlayer();
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert textFieldUrl != null : "fx:id=\"textFieldUrl\" was not injected: check your FXML file 'media-player.fxml'.";
        assert buttonStop != null : "fx:id=\"buttonStop\" was not injected: check your FXML file 'media-player.fxml'.";
        assert buttonPlay != null : "fx:id=\"buttonPlay\" was not injected: check your FXML file 'media-player.fxml'.";
        assert mediaView != null : "fx:id=\"mediaView\" was not injected: check your FXML file 'media-player.fxml'.";
        assert buttonClose != null : "fx:id=\"buttonClose\" was not injected: check your FXML file 'media-player.fxml'.";
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                mediaPlayer.stop();
            }
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }


    private void playMediaPlayer() {
        if (mediaPlayer != null) {
            stopMediaPlayer();
        }

        // create media player
        Media media = new Media(textFieldUrl.getText());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateCurrentTime();
                updateSlider();
            }
        });
        mediaPlayer.setOnPlaying(new Runnable() {
            @Override
            public void run() {
                updateStatus("Playing");
            }
        });
        mediaPlayer.setOnStopped(new Runnable() {
            @Override
            public void run() {
                updateStatus("Stopped");
            }
        });
        mediaPlayer.setOnError(new Runnable() {
            @Override
            public void run() {
                updateStatus("Error");
            }
        });
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                updateStatus("Ended");
            }
        });
        mediaPlayer.setOnHalted(new Runnable() {
            @Override
            public void run() {
                updateStatus("Halted");
            }
        });
        mediaPlayer.setOnPaused(new Runnable() {
            @Override
            public void run() {
                updateStatus("Paused");
            }
        });
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaDuration = mediaPlayer.getMedia().getDuration();
                updateStatus("Ready");
            }
        });
        mediaPlayer.setOnRepeat(new Runnable() {
            @Override
            public void run() {
                updateStatus("Repeat");
            }
        });
        mediaPlayer.setOnStalled(new Runnable() {
            @Override
            public void run() {
                updateStatus("Stalled");
            }
        });

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    private void updateSlider() {
        Platform.runLater(new Runnable() {
            public void run() {
                Duration currentTime = mediaPlayer.getCurrentTime();
                sliderCurrentTime.setDisable(mediaDuration.isUnknown());
                if (!sliderCurrentTime.isDisabled()
                        && mediaDuration.greaterThan(Duration.ZERO)
                        && !sliderCurrentTime.isValueChanging()) {
                    sliderCurrentTime.setValue(currentTime.divide(mediaDuration.toMillis()).toMillis()
                            * 100.0);
                }
            }
        });
    }

    private void updateStatus(String status) {
        labelStatus.setText(status);
    }

    private void updateCurrentTime() {
        labelCurrentTime.setText(String.format("%02d:%02d:%02d.%03d",
                (int) mediaPlayer.getCurrentTime().toHours(),
                (int) mediaPlayer.getCurrentTime().toMinutes() % 60,
                (int) mediaPlayer.getCurrentTime().toSeconds() % 60,
                (int) mediaPlayer.getCurrentTime().toMillis() % 1000));

    }


}
