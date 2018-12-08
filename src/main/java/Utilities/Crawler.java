package Utilities;

import Controller.AppController;
import Model.Query;
import javafx.util.Pair;
import org.jsoup.select.Elements;

import java.util.*;

public class Crawler implements Runnable {

    private AppController appController;
    private Map<String, Pair<HtmlParser, TextParser>> webPages = new HashMap<>();
    private Set<String> visitedUrls = new HashSet<>();

    public Crawler(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void run() {
        startCrawling();
    }

    private void startCrawling() {
        for (Query query : appController.getQueries()) {
            System.out.println(query.getUrl());
            evalQuery(query);
            visitedUrls = new HashSet<>();
        }
        System.out.println("Stoped crawling");
    }


    private void evalQuery(Query query) {
        HtmlParser htmlParser;
        TextParser textParser;
        if (!webPages.containsKey(query.getUrl())) {
            htmlParser = new HtmlParser(query.getUrl());
            Elements downloadedWebsite = htmlParser.parseToText();
            textParser = new TextParser(downloadedWebsite);
            webPages.put(query.getUrl(), new Pair(htmlParser, textParser));
        } else {
            Pair<HtmlParser, TextParser> pair = webPages.get(query.getUrl());
            htmlParser = pair.getKey();
            textParser = pair.getValue();
        }
        List<String> matchedSentences = findMatchedSentences(query, textParser.getSentences());
        for (String matchedSentence : matchedSentences) {
            appController.addResult(query.getUrl(), matchedSentence);
        }
        System.out.println("Actual deep = " + query.getDeep());
        if (query.getDeep() != 0) {
            List<String> linksInUrl = htmlParser.getLinksList();
            for (String linkInUrl : linksInUrl) {
                if (!visitedUrls.contains(linkInUrl)) {
                    System.out.println(linkInUrl);
                    visitedUrls.add(linkInUrl);
//                    Query tmp = new Query(linkInUrl, "", "", query.getDeep() - 1, query.getSubdomains());
//                    tmp.setForbiddenPattern(query.getForbiddenPattern());
//                    tmp.setSentencePattern(query.getSentencePattern());
                    // to moze nie dzialac, trzeba potestowac
                    query.setDeep(query.getDeep()-1);
//                    evalQuery(tmp);
                    evalQuery(query);
                    query.setDeep(query.getDeep()+1);
                }
            }
        }
    }

    private List<String> findMatchedSentences(Query query, List<String> sentences) {
        List<String> matchedSentences = new ArrayList<>();
        for (String sentence : sentences) {
//            System.out.println(sentence);
            if (query.matches(sentence)) {
                System.out.println(sentence);
                matchedSentences.add(sentence);
            }
        }
        return matchedSentences;


    }
}
