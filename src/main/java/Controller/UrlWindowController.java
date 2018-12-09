package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlWindowController {
    private AppController appController;

    @FXML
    private TextField urlTextField;


    @FXML
    private void initialize() {

    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        appController.endTransactionEditDialog();

    }

    @FXML
     private void handleAdd(ActionEvent event) {


         if(urlTextField.getText().isEmpty()){
             appController.showAddUrlDialog();
         }

     }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}



