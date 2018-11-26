package Utilities;

import Controller.UrlPerSentence;
import Model.Query;
import javafx.collections.ObservableList;

import java.util.List;

public class Crawler {
    private ObservableList<Query> queries;
    private ObservableList<UrlPerSentence> urlPerSentences;

    public Crawler(ObservableList<Query> queries,
                   ObservableList<UrlPerSentence> urlPerSentences){
        this.queries = queries;
        this.urlPerSentences = urlPerSentences;
    }

    public void startCrawling(){
        for(Query query: queries){
            System.out.println(query.getUrl());
            RegexpCreator regexp = new RegexpCreator(query.getSentencePattern(),query.getForbiddenWords());
            if(!regexp.getValid()){
                continue;
            }
            System.out.println(regexp.getSearchExpr());
            query.setRegexp(regexp.getSearchExpr());
            Parser parser = new Parser(query.getUrl(),query.getRegexp());
            List<String> results = parser.findWords(2);
            for(String result: results){
                urlPerSentences.add(new UrlPerSentence(query.getUrl(),result));
            }
            System.out.println("Stoped crawling");
        }
    }
}
