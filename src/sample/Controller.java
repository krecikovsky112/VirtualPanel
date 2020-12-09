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

    @FXML
    void handleButtonAction(ActionEvent event) {
        Speedometer.setAnimated(true);
        Speedometer.setValue(30);
        tachometer.setAnimated(true);
        tachometer.setValue(5);
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
