package Utilities;

import Controller.AppController;
import Model.GivenQuery;
import Model.Result;
import Model.StoredQuery;
import Session.SessionService;
import javafx.util.Pair;
import org.jsoup.select.Elements;
import Dao.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler implements Runnable {

    private AppController appController;
    private Map<String, Pair<HtmlParser, TextParser>> webPages = new HashMap<>();
    private Map<String, Integer> visitedUrls = new HashMap<>();
    private List<Result> results = Collections.<Result>emptyList();
    private StoredQueryDao storedQueryDao = new StoredQueryDao();
    private ResultDao resultDao = new ResultDao();
    private StoredQuery storedQuery;

    public Crawler(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void run() {
        startCrawling();
    }

    private void startCrawling() {
        SessionService.openSession();
        for (GivenQuery givenQuery : appController.getQueries()) {
            if(storedQueryDao.exists(givenQuery)) {
                storedQuery = new StoredQuery(storedQueryDao.getQuery(givenQuery));
                results = storedQueryDao.downloadAllResults(storedQuery);
            }
            else {
                storedQuery = storedQueryDao.create(givenQuery);
            }

            evalQuery(givenQuery);
            visitedUrls.clear();
        }
        System.out.println("Stoped crawling");
        SessionService.closeSession();
    }


    private void evalQuery(GivenQuery givenQuery) {
        HtmlParser htmlParser;
        TextParser textParser;
        if (!webPages.containsKey(givenQuery.getUrl())) {
            htmlParser = new HtmlParser(givenQuery.getUrl());
            Elements downloadedWebsite = htmlParser.parseToText();
            textParser = new TextParser(downloadedWebsite);
            webPages.put(givenQuery.getUrl(), new Pair(htmlParser, textParser));
        } else {
            Pair<HtmlParser, TextParser> pair = webPages.get(givenQuery.getUrl());
            htmlParser = pair.getKey();
            textParser = pair.getValue();
        }
        List<String> matchedSentences = findMatchedSentences(givenQuery, textParser.getSentences());
        for (String matchedSentence : matchedSentences) {
            if(results.isEmpty() || !results.contains(new Result(storedQuery, matchedSentence))){
                appController.addResult(givenQuery.getUrl(), matchedSentence);
                resultDao.create(storedQuery, matchedSentence);
            }
        }
        try {
            visitedUrls.put(getDomain(givenQuery.getUrl()), givenQuery.getDepth());
        } catch (IllegalStateException e) {
            return;
        }
        if (givenQuery.getDepth() != 0) {
            List<String> linksInUrl = htmlParser.getLinksList();
            for (String linkInUrl : linksInUrl) {
                String key = getDomain(linkInUrl);
                if(!visitedUrls.containsKey(key) || visitedUrls.get(key) < givenQuery.getDepth()){
                    if (givenQuery.validateSublink(linkInUrl)) {
                        visitedUrls.put(key, givenQuery.getDepth());
                        GivenQuery tmp = givenQuery.clone();
                        tmp.setDepth(givenQuery.getDepth() - 1);
                        tmp.setUrl(linkInUrl);
                        evalQuery(tmp);
                    }
                }
            }
        }
    }

    private String getDomain(String url) {
        Pattern pattern = Pattern.compile("(https?://w?w?w?[.]?)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        if (matcher.group(4) == null || matcher.group(4).isEmpty()) {
            return matcher.group(2) + "/";
        }
        return matcher.group(2) + matcher.group(4);
    }

    public String getDomainTest(String url) {
        return getDomain(url);
    }

    private List<String> findMatchedSentences(GivenQuery givenQuery, List<String> sentences) {
        List<String> matchedSentences = new ArrayList<>();
        for (String sentence : sentences) {
            if (givenQuery.matches(sentence)) {
                matchedSentences.add(sentence);
            }
        }
        return matchedSentences;


    }
}
