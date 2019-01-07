package Controller;

import Model.Query;
import Utilities.Crawler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppController {

    private Stage primaryStage;
    private Stage dialogStage;

    private ObservableList<Query> queries;
    private ObservableList<UrlPerSentence> urlPerSentences;
    private ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("Web Crawler");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("src/main/java/View/MainWindow.fxml").toURL());
            BorderPane rootLayout = (BorderPane) loader.load();

            // set initial data into controller
            MainWindowController controller = loader.getController();
            controller.setAppController(this);
            controller.setChartData(this.chartData);

            //test values
            queries = FXCollections.observableArrayList();
            urlPerSentences = FXCollections.observableArrayList();
            queries.add(new Query("https://onet.pl/", "polska", "", 0, false));
            queries.add(new Query("https://wp.pl/", "polska", "", 0, false));
            queries.add(new Query("https://o2.pl/", "polska", "", 1, false));

            controller.setTableViews(urlPerSentences, queries);
            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }

    }

    public void showAddQueryDialog() {
        try {
            // Load the fxml file and create a new stage for the dialog

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("src/main/java/View/AddQueryWindow.fxml").toURL());
            BorderPane page = (BorderPane) loader.load();
            QueryWindowController controller = loader.getController();
            controller.setAppController(this);
            // Create the dialog Stage.
            dialogStage = new Stage();
            dialogStage.setTitle("Add Query");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void endTransactionEditDialog() {

        dialogStage.close();
    }

    public void addQueryDialog(String url,
                               String sentencePattern,
                               String forbiddenWords,
                               Integer deep,
                               boolean ifMine) {

        queries.add(new Query(url, sentencePattern, forbiddenWords, deep, ifMine));
    }

    public ObservableList<Query> getQueries() {
        return queries;
    }

    public void addResult(String url, String sentence) {
        urlPerSentences.add(new UrlPerSentence(url, sentence));
    }

    public void addChartData(Map<String, Integer> data){
        Platform.runLater(()-> {
                chartData.clear();
                for(String val : data.keySet()){
                    chartData.add(new PieChart.Data(val, data.get(val)));
                }
            });
    }

    public void startWebCrawling() {
        System.out.println("Started WebCrawling!!");

        Crawler crawler = new Crawler(this);
        Thread thread = new Thread(crawler);
        thread.start();
    }
}
