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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
<<<<<<< HEAD
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
=======
import javafx.scene.control.*;
>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
<<<<<<< HEAD
import javafx.stage.Stage;
=======
import javafx.util.Duration;
>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Gauge Speedometer;

    @FXML
    private Gauge tachometer;

    @FXML
    private Button Gas;

    @FXML
<<<<<<< HEAD
    private BorderPane computerBG;

    int speedValue = 0;
    int flag_engine = 0;
=======
    private ImageView leftSignalImage;

    @FXML
    private ImageView rightSignalImage;

    @FXML
    private Button leftSignal;

    @FXML
    private BorderPane comp_bg;

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
    private TextField display_speed;

    @FXML
    private Timeline loop;

    @FXML
    private Timeline loop2;

    @FXML
    private Timeline loop3;

    @FXML
    private Timeline loop4;






//    @FXML
//    private Label kmh;
//
//    @FXML
//    public void initialize(){
//        kmh.setText("15km/h");
//    }
//    page1Controller page1controller = new page1Controller();


    int speedValue = 0, flag_engine = 0;
>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b
    double tachoValue = 0;
    int status = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void onPressed(ActionEvent event) throws InterruptedException, IOException {
        if (flag_engine == 1) {
            Speedometer.setAnimated(true);
            speedValue += 15;
            if (speedValue >= 200) {
                speedValue = 195;
            }
            Speedometer.setValue(speedValue);
//            display_speed.setText(speedValue+" Km/h");
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

    void setVisible()
    {
        check_engine.setVisible(true);
        acumulator.setVisible(true);
        reserve.setVisible(true);
        oil.setVisible(true);
        coolant.setVisible(true);
        airbag.setVisible(true);
        abs.setVisible(true);
    }

    void setInvisible()
    {
        check_engine.setVisible(false);
        acumulator.setVisible(false);
        reserve.setVisible(false);
        oil.setVisible(false);
        coolant.setVisible(false);
        airbag.setVisible(false);
        abs.setVisible(false);
    }
    @FXML
<<<<<<< HEAD
    void startEngine() throws IOException {
        setStatus(1);
        loadPage("page");
=======
    void startEngine() throws IOException, InterruptedException {
        loadPage("fxmls/page1");
        setStatus(1);
//        display_speed.setText("0 Km/h");
>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b
        tachometer.setAnimated(true);
        tachoValue = 1;
        tachometer.setValue(tachoValue);
        flag_engine = 1;
<<<<<<< HEAD
//    Media media =
//        new Media(
//            "file:///sample/engine_sound.mp3");
//        MediaPlayer mediaPlayer=new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
=======
        Media media = new Media("file:///F:/Semestr5/Java/VirtualPanel/src/sample/engine_sound.mp3");
        MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        setVisible();
        new Timeline(new KeyFrame(Duration.millis(4000),actionEvent -> setInvisible())).play();
>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b
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

<<<<<<< HEAD
    public void loadPage(String page) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource( "fxmls/" + page + ".fxml"));
        computerBG.setCenter(root);
=======
    void setVisibleSignalLeft()
    {
        leftSignalImage.setVisible(true);

    }
    void blinkLeft()
    {
        leftSignalImage.setVisible(false);
        loop2 =new Timeline(new KeyFrame(Duration.seconds(1),e->setVisibleSignalLeft()));
        loop2.play();
    }

    @FXML
    void flashingLeftSignal()
    {
            setVisibleSignalLeft();
            loop =new Timeline(new KeyFrame(Duration.seconds(2),e->blinkLeft()));
            loop.setCycleCount(Timeline.INDEFINITE);
            loop.play();
    }

    void setVisibleSignalRight()
    {
        rightSignalImage.setVisible(true);

    }
    void blinkRight()
    {
        rightSignalImage.setVisible(false);
        loop4 =new Timeline(new KeyFrame(Duration.seconds(1),e->setVisibleSignalRight()));
        loop4.play();
    }

    @FXML
    void flashingRightSignal()
    {
        setVisibleSignalRight();
        loop3 =new Timeline(new KeyFrame(Duration.seconds(2),e->blinkRight()));
        loop3.setCycleCount(Timeline.INDEFINITE);
        loop3.play();
    }

    @FXML
    void flashingEmergencyLights()
    {
        flashingRightSignal();
        flashingLeftSignal();
    }

    @FXML
    void stopFlashingLeft()
    {
        leftSignalImage.setVisible(false);
        loop.stop();
    }

    @FXML
    void stopFlashingRight()
    {
        rightSignalImage.setVisible(false);
        loop3.stop();
    }

    @FXML
    void stopFlashing()
    {

        rightSignalImage.setVisible(false);
        leftSignalImage.setVisible(false);
        loop.stop();
        loop3.stop();
    }
    private void loadPage(String page) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        comp_bg.setCenter(root);
>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b
    }

    private void setStatus(int status){
        this.status = status;
    }

    private int getStatus(){
        return status;
    }

<<<<<<< HEAD
=======

>>>>>>> 32bdd704077c18eb5a893fdf2d89ebfd35eabd8b
}