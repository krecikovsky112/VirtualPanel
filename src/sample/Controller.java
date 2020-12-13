package sample;

import eu.hansolo.medusa.Gauge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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

    int speedValue=0;
    int tachoValue=0;
    @FXML
    void onPressed(ActionEvent event) throws InterruptedException {
        Speedometer.setAnimated(true);
        speedValue+=15;
        if(speedValue>=200)
        {
            speedValue=195;
        }
        Speedometer.setValue(speedValue);
        tachometer.setAnimated(true);
        if(tachoValue==8)
        {
            tachoValue=0;
        }
            tachoValue += 4;
        if(speedValue==195)
        {
            tachoValue=8;
        }
        tachometer.setValue(tachoValue);
    }

    @FXML
    void onRelease(ActionEvent event)
    {
        Speedometer.setAnimated(true);
        speedValue-=25;
        Speedometer.setValue(speedValue);
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

        root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        comp_bg.setCenter(root);
    }
}