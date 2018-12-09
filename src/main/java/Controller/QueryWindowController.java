package Controller;

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

    @FXML void getTexts(){
        String current = urlTextField.getText();
        System.out.println(current);
        this.urls.add(current);
        urlList.setItems(urls);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        if (urls.isEmpty()){
            System.out.println("There is empty url");
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
                dialogStage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Pattern pattern = Pattern.compile("^http[s]?:\\/{2}(www\\.)?\\w+(\\.\\w+)+(\\/\\S*)*$");
        for (int i=0; i<urls.size(); i++){
            Matcher matcher1 = pattern.matcher(urls.get(i));
            String url = "http://" + urls.get(i);
            Matcher matcher2 = pattern.matcher(url);
            if(!matcher1.find() && !matcher2.find()){
                System.out.println("There is wrong url");
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
                    dialogStage.show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else {
                if(matcher1.find()){
                    url = urls.get(i);
                }
                appController.addQueryDialog(
                        url,
                        queryTextField.getText(),
                        forbiddenWordsTextField.getText(),
                        Integer.decode(deepSpinner.getValue().toString()),
                        Boolean.valueOf(SubdomainsText.getSelectionModel().getSelectedItem().toString()));

            }
        }
        urlTextField.setText(null);
        queryTextField.setText(null);
        forbiddenWordsTextField.setText(null);
        appController.endTransactionEditDialog();
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
        urls = FXCollections.observableArrayList();
    }
}

