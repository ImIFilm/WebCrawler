package Controller;

import Model.Page;
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
    private ObservableList<UrlPerSentence> pages;

    private ObservableList<Query> queries = FXCollections.observableArrayList();

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
    private TableColumn<Query,Boolean> ifMineColumn;


    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        urlColumn.setCellValueFactory(dataValue -> dataValue.getValue().getUrl());
        sentenceColumn.setCellValueFactory(dataValue -> dataValue.getValue().getSentence());

        queryUrlColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getUrl()));
        sentencePatternColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getSentencePattern()));
        forbiddenWordsColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getForbiddenWords()));
        deepColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getDeep()));
        ifMineColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getIfMine()));
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        appController.showAddQueryDialog();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setPages(ObservableList<Page> inputPages){
        this.pages = FXCollections.observableArrayList();
        for( Page page: inputPages){
            for( String url: page.getSentencesProperty()){
                this.pages.add(new UrlPerSentence(page.getUrlProperty(),new SimpleStringProperty(url)));
            }
        }
        pageTableView.setItems(pages);
    }

    public void setQueries(ObservableList<Query> inputQueries){
        queryTableView.setItems(inputQueries);
    }

    private class UrlPerSentence{
        private SimpleStringProperty sentence;
        private SimpleStringProperty url;
        public UrlPerSentence(SimpleStringProperty url, SimpleStringProperty sentence){
            this.url = url;
            this.sentence = sentence;
        }

        public SimpleStringProperty getSentence(){
            return sentence;
        }

        public SimpleStringProperty getUrl(){
            return url;
        }
    }

    public void addQuery(Query query){
        queries.add(query);

    }
}
