package com.example.vlc;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;


public class HelloController implements Initializable {
    //layout from scene builder

    private MediaPlayer player;
    private HBox MediaBar;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button Play;

    @FXML
    private Button Pause;

    @FXML
    private Button Stop;


    @FXML
    private Slider progressBar;

    @FXML
    private Slider volume;

    @FXML
    private MediaView mediaview;
    @FXML
    private Label currentDuration;
    @FXML
    private Label fullDuration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //declaring source for video
        String src = getClass().getResource("/Baba.mp4").toExternalForm();
        Media media = new Media(src);
        player=new MediaPlayer(media);
        mediaview.setMediaPlayer(player);
        mediaview.setFitWidth(400);
        mediaview.setFitHeight(400);

        bindCurrentTimeLabel();
        //volume slider
        volume.valueProperty().addListener(observable -> player.setVolume(volume.getValue()));

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                progressBar.setValue(t1.toSeconds());
            }
        });


    }
    // video time duration
    public void bindCurrentTimeLabel(){
        currentDuration.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(player.getCurrentTime());
            }
        },player.currentTimeProperty()));
    }
    //Time calculation
    public String getTime(Duration time){
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (hours > 59) hours = hours % 60;

        if(hours > 0 ) return String.format("%d:%02d%02d",
                hours, minutes, seconds);

        else return String.format("%02d:%02d",
                minutes,seconds);
    }
    //Buttons mouse events when clicked

    @FXML
    void pausebtn(MouseEvent event) {

        player.pause();
    }

    @FXML
    void playbtn(MouseEvent event) {

        player.play();
    }

    @FXML
    void stopbtn(MouseEvent event) {

        player.stop();
    }

}