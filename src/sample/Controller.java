package sample;

import eu.hansolo.medusa.Gauge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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
    private BorderPane computerBG;

    int speedValue = 0;
    int flag_engine = 0;
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

    @FXML
    void startEngine() throws IOException {
        setStatus(1);
        loadPage("page");
        tachometer.setAnimated(true);
        tachoValue = 1;
        tachometer.setValue(tachoValue);
        flag_engine = 1;
//    Media media =
//        new Media(
//            "file:///sample/engine_sound.mp3");
//        MediaPlayer mediaPlayer=new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
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

    public void loadPage(String page) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource( "fxmls/" + page + ".fxml"));
        computerBG.setCenter(root);
    }

    private void setStatus(int status){
        this.status = status;
    }

    private int getStatus(){
        return status;
    }

}