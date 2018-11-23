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

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    private Spinner deepSpinner;

    @FXML
    private ChoiceBox SubdomainsText;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        appController.endTransactionEditDialog();

    }

    @FXML
    private void handleOk(ActionEvent event) {

        if(urlTextField.getText().isEmpty()){
            System.out.println("Jest pusty url");
            FXMLLoader loader = new FXMLLoader();
            try {
            loader.setLocation(new File("src/main/java/View/WarningWindow.fxml").toURL());

                BorderPane page = (BorderPane) loader.load();
            QueryWindowController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Warning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(appController.getDialogStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            appController.addQueryDialog(
                    urlTextField.getText(),
                    queryTextField.getText(),
                    forbiddenWordsTextField.getText(),
                    Integer.decode(deepSpinner.getValue().toString()),
                    Boolean.valueOf(SubdomainsText.getSelectionModel().getSelectedItem().toString()));
            urlTextField.setText(null);
            queryTextField.setText(null);
            forbiddenWordsTextField.setText(null);
        }

    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}

