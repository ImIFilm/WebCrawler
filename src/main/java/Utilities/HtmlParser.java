package Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
    private String url;
    private List<String> linksList;

    public HtmlParser(String url) {
        this.url = url;
        findLinksInHtmlUrl();
    }

    public String getUrl() {
        return url;
    }

    public List<String> findLinksInHtmlFile(String fileName) {
        linksList = new ArrayList<>();
        try {
            File input = new File(fileName);
            Document doc = Jsoup.parse(input, "UTF-8");
            findLinksInHtml(doc);
        } catch (IOException e) {
            linksList = new ArrayList<>();
        }

        return linksList;
    }

    private void findLinksInHtmlUrl() {
        try {
            findLinksInHtml(Jsoup.connect(url).get());
        } catch (IOException e) {
            linksList = new ArrayList<>();
        }
    }

    private void findLinksInHtml(Document doc) {
        Elements links = doc.select("a[href]");
        linksList = new ArrayList<>();
        for (Element link : links) {
            if (!link.attr("abs:href").isEmpty())
                linksList.add(link.attr("abs:href"));
        }
    }

    public Elements parseToTextFromFile(String fileName) {
        Document doc;
        try {
            File input = new File(fileName);
            doc = Jsoup.parse(input, "UTF-8");
        } catch (IOException e) {
            return new Elements();
        }
        return parseToTextFromDoc(doc);
    }

    public Elements parseToText() {
        try {
            return parseToTextFromDoc(Jsoup.connect(url).get());
        } catch (IOException e) {
            return new Elements();
        }
    }

    private Elements parseToTextFromDoc(Document doc) {
        return doc.select("html");
    }

    public List<String> getLinksList() {
        return linksList;
    }
}
