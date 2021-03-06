package Controller;

import Model.GivenQuery;
import Utilities.Crawler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;

import java.util.Set;


public class MainWindowController {

    private AppController appController;

    @SuppressWarnings("unused")
    private ObservableList<UrlPerSentence> urlPerSentences;

    private ObservableList<GivenQuery> queries;

    @FXML
    private TableView<UrlPerSentence> pageTableView;

    @FXML
    private TableColumn<UrlPerSentence, String> urlColumn;

    @FXML
    private TableColumn<UrlPerSentence, String> sentenceColumn;


    @FXML
    private TableView<GivenQuery> queryTableView;

    @FXML
    private TableColumn<GivenQuery, String> queryUrlColumn;

    @FXML
    private TableColumn<GivenQuery, String> sentencePatternColumn;

    @FXML
    private TableColumn<GivenQuery, String> forbiddenWordsColumn;

    @FXML
    private TableColumn<GivenQuery, Integer> deepColumn;

    @FXML
    private TableColumn<GivenQuery, Boolean> subdomainsColumn;


    @FXML
    private Button addButton;

    @FXML
    private Button startButton;

    @FXML
    private PieChart pie;

    @FXML
    private void initialize() {
        urlColumn.setCellValueFactory(dataValue -> dataValue.getValue().getUrl());
        sentenceColumn.setCellValueFactory(dataValue -> dataValue.getValue().getSentence());

        queryUrlColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getUrl()));
        sentencePatternColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getSentencePatternString()));
        forbiddenWordsColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getForbiddenPatternString()));
        deepColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getDepth()));
        subdomainsColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getSubdomains()));

        pie.setAnimated(false);
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        appController.showAddQueryDialog();
    }

    @FXML
    private void handleStartAction(ActionEvent event) {

        appController.startWebCrawling();
    }


    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setChartData(ObservableList<PieChart.Data> data){ pie.setData(data);}

    public void setTableViews(ObservableList<UrlPerSentence> urlPerSentences, ObservableList<GivenQuery> queries){
        this.urlPerSentences = urlPerSentences;
        this.queries = queries;
        pageTableView.setItems(urlPerSentences);
        queryTableView.setItems(queries);
    }

}
