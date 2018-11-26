package Controller;

import javafx.beans.property.SimpleStringProperty;

public class UrlPerSentence{
    private SimpleStringProperty sentence;
    private SimpleStringProperty url;
    public UrlPerSentence(SimpleStringProperty url, SimpleStringProperty sentence){
        this.url = url;
        this.sentence = sentence;
    }
    public UrlPerSentence(String url, String sentence){
        this.url = new SimpleStringProperty(url);
        this.sentence = new SimpleStringProperty(sentence);
    }
    public SimpleStringProperty getSentence(){
        return sentence;
    }

    public SimpleStringProperty getUrl(){
        return url;
    }
}
