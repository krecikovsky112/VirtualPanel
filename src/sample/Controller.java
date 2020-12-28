package sample;

import eu.hansolo.medusa.Gauge;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


import java.io.File;
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
    private BorderPane comp_bg;

//    @FXML
//    private Label kmh;
//
//    @FXML
//    public void initialize(){
//        kmh.setText("15km/h");
//    }
//    page1Controller page1controller = new page1Controller();


    int speedValue = 0, flag_engine = 0;
    double tachoValue = 0;
    int status = 0;

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
    void startEngine() throws IOException {
        loadPage("fxmls/page1");
        setStatus(1);
        tachometer.setAnimated(true);
        tachoValue = 1;
        tachometer.setValue(tachoValue);
        flag_engine = 1;
        //Media media = new Media("file:///F:/Semestr5/Java/VirtualPanel/src/sample/engine_sound.mp3");
        //MediaPlayer mediaPlayer=new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(true);
    }

    @FXML
    void right_navigation(ActionEvent event) throws IOException {
        if(getStatus() == 1) {
            loadPage("fxmls/page2");
            setStatus(2);
        } else if(getStatus() == 2){
            loadPage("fxmls/page3");
            setStatus(3);
        } else if(getStatus() == 3){
            loadPage("fxmls/page4");
            setStatus(4);
        } else if(getStatus() == 4){
            loadPage("fxmls/page1");
            setStatus(1);
        }
    }

    @FXML
    void left_navigation(ActionEvent event) throws IOException {
        if(getStatus() == 4){
            loadPage("fxmls/page3");
            setStatus(3);
        } else if(getStatus() == 3) {
            loadPage("fxmls/page2");
            setStatus(2);
        } else if(getStatus() == 2){
            loadPage("fxmls/page1");
            setStatus(1);
        } else if(getStatus() == 1){
            loadPage("fxmls/page4");
            setStatus(4);
        }
    }

    private void loadPage(String page) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        comp_bg.setCenter(root);
    }

    private void setStatus(int status){
        this.status = status;
    }

    private int getStatus(){
        return status;
    }
}