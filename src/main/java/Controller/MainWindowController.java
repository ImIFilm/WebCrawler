package Controller;

import Model.Query;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class MainWindowController {

    private AppController appController;

    @SuppressWarnings("unused")
    private ObservableList<UrlPerSentence> urlPerSentences;

    private ObservableList<Query> queries;

    @FXML
    private TableView<UrlPerSentence> pageTableView;

    @FXML
    private TableColumn<UrlPerSentence, String> urlColumn;

    @FXML
    private TableColumn<UrlPerSentence, String> sentenceColumn;


    @FXML
    private TableView<Query> queryTableView;

    @FXML
    private TableColumn<Query,String> queryUrlColumn;

    @FXML
    private TableColumn<Query,String> sentencePatternColumn;

    @FXML
    private TableColumn<Query,String> forbiddenWordsColumn;

    @FXML
    private TableColumn<Query,Integer> deepColumn;

    @FXML
    private TableColumn<Query,Boolean> subdomainsColumn;


    @FXML
    private Button addButton;

    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        urlColumn.setCellValueFactory(dataValue -> dataValue.getValue().getUrl());
        sentenceColumn.setCellValueFactory(dataValue -> dataValue.getValue().getSentence());

        queryUrlColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getUrl()));
        sentencePatternColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getSentencePatternString()));
        forbiddenWordsColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getForbiddenPatternString()));
        deepColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getDeep()));
        subdomainsColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getSubdomains()));
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

    public void setTableViews(ObservableList<UrlPerSentence> urlPerSentences, ObservableList<Query> queries){
        this.urlPerSentences = urlPerSentences;
        this.queries= queries;
        pageTableView.setItems(urlPerSentences);
        queryTableView.setItems(queries);
    }

    public void addQuery(Query query){
        queries.add(query);
    }
}
