package sample;

import eu.hansolo.medusa.Gauge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Gauge Speedometer;

    @FXML
    private Gauge tachometer;

    @FXML
    private Button Gas;

    @FXML
    void handleButtonAction(ActionEvent event) {
        Speedometer.setAnimated(true);
        Speedometer.setValue(30);
        tachometer.setAnimated(true);
        tachometer.setValue(5);
    }

}
