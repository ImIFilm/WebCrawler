package Controller;

import Model.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AppController {


    private Stage primaryStage;
    private Stage dialogStage;
    private ObservableList<Query> queries;
    private ObservableList<Page> pages;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
//        this.queries = queries;
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

            //test values
            pages = FXCollections.observableArrayList();
            Page page = new Page("onet");
            page.addSentence("ala ma kota");
            page.addSentence("bardzo fajnego");
            pages.add(page);
            queries = FXCollections.observableArrayList();
            queries.add(new Query("onet","ala * kot","pies",1,true));

            controller.setPages(pages);
            controller.setQueries(queries);

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

    public void endTransactionEditDialog(){

        dialogStage.close();
    }

    public void addQueryDialog(String url,
                               String sentencePattern,
                               String forbiddenWords,
                               Integer deep,
                               boolean ifMine){

        queries.add(new Query(url,sentencePattern,forbiddenWords,deep,ifMine));
    }


}
