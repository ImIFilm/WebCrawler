package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WarningController {

    @FXML
    Label warningLabel;


    @FXML
    private void initialize() {
    }


    public void setWarning(String warning){
        warningLabel.setText(warning);
    }
}
