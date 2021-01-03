package sample;

import eu.hansolo.medusa.Gauge;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Gauge Speedometer;

    @FXML
    private Gauge tachometer;

    @FXML
    private Button Gas;

    @FXML
    private ImageView leftSignalImage;

    @FXML
    private ImageView rightSignalImage;

    @FXML
    private ImageView check_engine;

    @FXML
    private ImageView acumulator;

    @FXML
    private ImageView reserve;

    @FXML
    private ImageView airbag;

    @FXML
    private ImageView oil;

    @FXML
    private ImageView coolant;

    @FXML
    private ImageView abs;

    @FXML
    private Timeline loop;

    @FXML
    private Timeline loop2;

    @FXML
    private Timeline loop3;

    @FXML
    private Timeline loop4;

    @FXML
    private BorderPane computerBG;

    @FXML
    private ToggleButton emergencySignal;

    @FXML
    private ToggleButton leftSignal;

    @FXML
    private ToggleButton rightSignal;

    int speedValue = 0, flag_engine = 0;
    double tachoValue = 0;
    int status = -1;

    @FXML
    void onPressed(ActionEvent event) throws InterruptedException, IOException {
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
            if(getStatus() == 1){
                //setStatus(1);
                loadPage("page1");
            }
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

    void setVisible() {
        check_engine.setVisible(true);
        acumulator.setVisible(true);
        reserve.setVisible(true);
        oil.setVisible(true);
        coolant.setVisible(true);
        airbag.setVisible(true);
        abs.setVisible(true);
    }

    void setInvisible() {
        check_engine.setVisible(false);
        acumulator.setVisible(false);
        reserve.setVisible(false);
        oil.setVisible(false);
        coolant.setVisible(false);
        airbag.setVisible(false);
        abs.setVisible(false);
    }

    @FXML
    void startEngine() throws IOException, InterruptedException {
        setStatus(1);
        loadPage("page");
        tachometer.setAnimated(true);
        tachoValue = 1;
        tachometer.setValue(tachoValue);
        flag_engine = 1;
//          Media media = new Media("file:///C:/Users/Mati/Desktop/Semestr 5/KCK/Laby/carPanel/VirtualPanel/src/sample/engine_sound.mp3");
//          MediaPlayer mediaPlayer=new MediaPlayer(media);
//          mediaPlayer.setAutoPlay(true);
        setVisible();
        new Timeline(new KeyFrame(Duration.millis(4000),actionEvent -> setInvisible())).play();
    }

    @FXML
    void rightNavigation(ActionEvent event) throws IOException {
        if(getStatus() == 0 || getStatus() == 1) {
            setStatus(2);
            loadPage("page2");
        } else if(getStatus() == 2){
            setStatus(3);
            loadPage("page3");
        } else if(getStatus() == 3){
            setStatus(4);
            loadPage("page4");
        } else if(getStatus() == 4){
            setStatus(1);
            if(speedValue > 0){
                loadPage("page1");
            } else {
                loadPage("page");
            }
        }
    }

    @FXML
    void leftNavigation(ActionEvent event) throws IOException {
        if(getStatus() == 4){
            setStatus(3);
            loadPage("page3");
        } else if(getStatus() == 3) {
            setStatus(2);
            loadPage("page2");
        } else if(getStatus() == 2){
            setStatus(1);
            if(speedValue > 0){
                loadPage("page1");
            } else {
                loadPage("page");
            }
        } else if(getStatus() == 1 || getStatus() == 0){
            setStatus(4);
            loadPage("page4");
        }
    }

    void setVisibleSignalLeft() {
        leftSignalImage.setVisible(true);
    }

    void blinkLeft() {
        leftSignalImage.setVisible(false);
        loop2 = new Timeline(new KeyFrame(Duration.seconds(1), e->setVisibleSignalLeft()));
        loop2.play();
    }

    void setVisibleSignalRight() {
        rightSignalImage.setVisible(true);
    }

    void blinkRight() {
        rightSignalImage.setVisible(false);
        loop4 =new Timeline(new KeyFrame(Duration.seconds(1), e->setVisibleSignalRight()));
        loop4.play();
    }

    @FXML
    void flashingLeftSignal() {
        setVisibleSignalLeft();
        loop = new Timeline(new KeyFrame(Duration.seconds(2), e->blinkLeft()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

//    @FXML
//    void stopFlashingLeft() {
//        leftSignalImage.setVisible(false);
//        loop.stop();
//        loop2.stop();
//    }

    @FXML
    void leftFlashing(){
        if(leftSignal.isSelected()){
            flashingLeftSignal();
        } else {
            leftSignalImage.setVisible(false);
            loop.stop();
            loop2.stop();
        }
    }

    @FXML
    void flashingRightSignal() {
        setVisibleSignalRight();
        loop3 =new Timeline(new KeyFrame(Duration.seconds(2), e->blinkRight()));
        loop3.setCycleCount(Timeline.INDEFINITE);
        loop3.play();
    }

//    @FXML
//    void stopFlashingRight() {
//        rightSignalImage.setVisible(false);
//        loop3.stop();
//        loop4.stop();
//    }

    @FXML
    void rightFlashing(){
        if(rightSignal.isSelected()){
            flashingRightSignal();
        } else {
            rightSignalImage.setVisible(false);
            loop3.stop();
            loop4.stop();
        }
    }

//    @FXML
//    void flashingEmergencyLights() {
//        flashingRightSignal();
//        flashingLeftSignal();
//    }
//
//    @FXML
//    void stopFlashing() {
//        rightSignalImage.setVisible(false);
//        leftSignalImage.setVisible(false);
//        loop.stop();
//        loop3.stop();
//    }

    @FXML
    void emergencyFlashing() {
        if(emergencySignal.isSelected()){
            flashingRightSignal();
            flashingLeftSignal();
        } else {
            rightSignalImage.setVisible(false);
            leftSignalImage.setVisible(false);
            loop.stop();
            loop2.stop();
            loop3.stop();
            loop4.stop();
        }
    }

    private void loadPage(String page) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/" + page + ".fxml"));
        computerBG.setCenter(root);
    }

    private void setStatus(int status){
        this.status = status;
    }

    private int getStatus(){
        return status;
    }
}