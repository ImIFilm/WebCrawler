package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class QueryWindowController {
    private AppController appController;

//    @FXML
//    private Button addButton;

    @FXML
    private TextField urlTextField;

    @FXML
    private TextField queryTextField;

    @FXML
    private TextField forbiddenWordsTextField;

    @FXML
    private TextField deepTextField;

    @FXML
    private TextField ifMineTextField;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        appController.endTransactionEditDialog();

    }

    @FXML
    private void handleOk(ActionEvent event) {

        appController.addQueryDialog(
                urlTextField.getText(),
                queryTextField.getText(),
                forbiddenWordsTextField.getText(),
                Integer.decode(deepTextField.getText()),
                Boolean.valueOf(ifMineTextField.getText()));
        urlTextField.setText(null);
        queryTextField.setText(null);
        forbiddenWordsTextField.setText(null);
        deepTextField.setText(null);
        ifMineTextField.setText(null);

    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}

