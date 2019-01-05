package Utilities;

import Controller.AppController;
import Model.Query;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler implements Runnable {

    private AppController appController;
    private Map<String, Pair<HtmlParser, TextParser>> webPages = new HashMap<>();
    private Set<String> visitedUrls = new HashSet<>();
    public static Map<String, Integer> data = new HashMap<>();

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
            if (!data.containsKey(query.getUrl())) data.put(query.getUrl(), 0);
            evalQuery(query);
            visitedUrls = new HashSet<>();
            System.out.println(Arrays.asList(data));
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

            if (data.containsKey(query.getUrl())) {
                int counter = data.get(query.getUrl());
                data.replace(query.getUrl(), counter,counter+1);
            }
        }
        //bo raz mi sie wysypalo, bo zamiast linku byl jakis email
        try{
            visitedUrls.add(getDomain(query.getUrl()));
        }catch (IllegalStateException e){
            return;
        }
        System.out.println("Actual deep = " + query.getDeep());
        if (query.getDeep() != 0) {
            List<String> linksInUrl = htmlParser.getLinksList();
            for (String linkInUrl : linksInUrl) {
                String domain = getDomain(linkInUrl);
                if (!visitedUrls.contains(domain) && query.validateSublink(linkInUrl)) {
                    System.out.println(linkInUrl);
                    visitedUrls.add(domain);
                    Query tmp = new Query(linkInUrl, "nothing", "", query.getDeep() - 1, query.getSubdomains());
                    tmp.setForbiddenPattern(query.getForbiddenPattern());
                    tmp.setSentencePattern(query.getSentencePattern());
                    evalQuery(tmp);
                }
            }
        }
    }

    private String getDomain(String url){
        Pattern pattern = Pattern.compile("(https?://w?w?w?[.]?)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        return matcher.group(2) + matcher.group(4);
    }

    private List<String> findMatchedSentences(Query query, List<String> sentences) {
        List<String> matchedSentences = new ArrayList<>();
        for (String sentence : sentences) {
            if (query.matches(sentence)) {
                matchedSentences.add(sentence);

            }
        }
        return matchedSentences;


    }
}
