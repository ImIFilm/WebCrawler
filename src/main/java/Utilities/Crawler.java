package Utilities;

import Controller.UrlPerSentence;
import Model.Query;
import javafx.collections.ObservableList;
import org.jsoup.select.Elements;

import java.util.List;

public class Crawler{
    private ObservableList<Query> queries;
    private ObservableList<UrlPerSentence> urlPerSentences;

    public Crawler(ObservableList<Query> queries,
                   ObservableList<UrlPerSentence> urlPerSentences){
        this.queries = queries;
        this.urlPerSentences = urlPerSentences;
    }

    public void startCrawling() {
        for(Query query: queries){
            Elements downloadedWebsite = new HtmlParser(query.getUrl()).parseToText();
            List<String> allSentences = new TextParser(downloadedWebsite).makeSentences();

            for(String sentence: allSentences){
                if (query.matches(sentence)){
                    urlPerSentences.add(new UrlPerSentence(query.getUrl(),sentence));
                }
            }
            System.out.println("Stoped crawling");
        }
    }
}
