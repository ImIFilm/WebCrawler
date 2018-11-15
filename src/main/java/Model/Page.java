package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class Page {
    private SimpleStringProperty url;
    private ObservableList<String> sentences = FXCollections.observableArrayList();

    public Page(String url){
        this.url = new SimpleStringProperty(url);
    }

    public String getUrl() {
        return url.getValue();
    }

    public final SimpleStringProperty getUrlProperty() {
        return url;
    }

    public final ObservableList<String> getSentencesProperty(){
        return sentences;
    }

    public final void addSentence(String sentence){
        sentences.add(sentence);
    }
}
