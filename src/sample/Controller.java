package sample;

import eu.hansolo.medusa.Gauge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.IOException;

public class Controller {

    @FXML
    private Gauge Speedometer;

    @FXML
    private Gauge tachometer;

    @FXML
    private Button Gas;

    @FXML
    private BorderPane comp_bg;

    @FXML
    private AnchorPane comp_display;

    int speedValue = 0, flag_engine = 0;
    double tachoValue = 0;

    @FXML
    void onPressed(ActionEvent event) throws InterruptedException {
        if (flag_engine == 1) {
            Speedometer.setAnimated(true);
            speedValue += 15;
            if (speedValue >= 200) {
                speedValue = 195;
            }
            Speedometer.setValue(speedValue);
            tachometer.setAnimated(true);
            if (tachoValue >= 6) {
                tachoValue = 1;
            }
            tachoValue += 2.5;
            if (speedValue == 195) {
                tachoValue = 8;
            }
            tachometer.setValue(tachoValue);
        }
    }

    @FXML
    void onRelease(ActionEvent event) {
        if (flag_engine == 1) {
            Speedometer.setAnimated(true);
            speedValue -= 25;
            if (speedValue < 0) {
                speedValue = 0;
            }
            Speedometer.setValue(speedValue);
            tachometer.setAnimated(true);
            tachoValue = -3;
            if (tachoValue < 0) {
                tachoValue = 1;
            }
            tachometer.setValue(tachoValue);
        }
    }

    @FXML
    void startEngine() {
        tachometer.setAnimated(true);
        tachoValue = 1;
        tachometer.setValue(tachoValue);
        flag_engine = 1;
        Media media = new Media("file:///F:/Semestr5/Java/VirtualPanel/src/sample/engine_sound.mp3");
        MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    void right_navigation(ActionEvent event) throws IOException {
        loadPage("page1");
    }

    @FXML
    void left_navigation(ActionEvent event) throws IOException {
        loadPage("page2");
    }

    private void loadPage(String page) throws IOException {
        Parent root = null;

        root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        comp_bg.setCenter(root);
    }
}