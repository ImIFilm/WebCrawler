package Controller;

import Model.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryWindowController {
    private AppController appController;
    private ObservableList<String> urls;

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
    VBox pane;

    @FXML
    ListView<String> urlList;

    @FXML
    void getTexts() {
        String current = urlTextField.getText();
        System.out.println(current);
        Pattern pattern = Pattern.compile("^http[s]?:\\/{2}(www\\.)?\\w+(\\.\\w+)+(\\/\\S*)*$");
        Matcher matcher1 = pattern.matcher(current);
        String url = "https://" + current;
        Matcher matcher2 = pattern.matcher(url);
        if (!matcher1.matches() && !matcher2.matches()) {
            System.out.println("There is wrong url");
            printWarning("It is wrong url");
        } else if (matcher2.matches()) {
            if (this.urls.contains(url)) {
                printWarning("This url has already been added");
                return;
            }
            this.urls.add(url);
        } else {
            this.urls.add(current);
        }
        urlList.setItems(urls);
        urlTextField.setText(null);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        if (urls.isEmpty()) {
            printWarning("There is empty url");
            return;
        }
        for (String url : urls) {
            try {
                new Query(url,
                        queryTextField.getText(),
                        forbiddenWordsTextField.getText(),
                        Integer.decode(deepSpinner.getValue().toString()),
                        Boolean.valueOf(SubdomainsText.getSelectionModel().getSelectedItem().toString()));

                appController.addQueryDialog(
                        url,
                        queryTextField.getText(),
                        forbiddenWordsTextField.getText(),
                        Integer.decode(deepSpinner.getValue().toString()),
                        Boolean.valueOf(SubdomainsText.getSelectionModel().getSelectedItem().toString()));

            } catch (IllegalArgumentException e) {
                printWarning("There is bad query");
                return;
            }
        }
        urlTextField.setText(null);
        queryTextField.setText(null);
        forbiddenWordsTextField.setText(null);
        appController.endTransactionEditDialog();
    }

    private void printWarning(String warning) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new File("src/main/java/View/WarningWindow.fxml").toURL());
            BorderPane page = (BorderPane) loader.load();
            WarningController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Warning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(appController.getDialogStage());
            controller.setWarning(warning);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setAppController(AppController appController) {
        this.appController = appController;
        urls = FXCollections.observableArrayList();
    }
}

