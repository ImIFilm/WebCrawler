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
            System.out.println(query.getUrl());
            RegexpCreator regexp = new RegexpCreator(query.getSentencePattern(),query.getForbiddenWords());
            if(!regexp.getValid()){
                continue;
            }
            System.out.println(regexp.getSearchExpr());
            query.setRegexp(regexp.getSearchExpr());

            HtmlParser htmlParser = new HtmlParser(query.getUrl());
            Elements downloadedWebsite;
            downloadedWebsite=htmlParser.parseToText();
            TextParser textParser = new TextParser(downloadedWebsite);
            List<String> allSentences = null;
            allSentences =textParser.makeSentences();
            SearchPattern pattern = new SearchPattern(query.getRegexp());


            for(String sentence: allSentences){
                if (pattern.matches(sentence)){
                    urlPerSentences.add(new UrlPerSentence(query.getUrl(),sentence));
                }

            }
            System.out.println("Stoped crawling");
        }
    }
}
