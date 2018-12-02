package Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class HtmlParser {
    private String url;
    private List<String> linksList;

    public HtmlParser(String url){
        this.url=url;
        findLinksInHtmlUrl();
    }

    public String getTitle (){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.title();
    }

    public String getUrl() {
        return url;
    }

    public List<String> findLinksInHtmlFile(String fileName){
        linksList = new ArrayList<>();
        try {
            File input = new File(fileName);
            Document doc = Jsoup.parse(input, "UTF-8");
            findLinksInHtml(doc);
        }catch (IOException e){
            e.printStackTrace();
        }
        return linksList;
    }

    private void findLinksInHtmlUrl(){
        try {
            findLinksInHtml(Jsoup.connect(url).get());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void findLinksInHtml(Document doc){
        Elements links = doc.select("a[href]");
        linksList = new ArrayList<>();
        for(Element link: links){
            linksList.add(link.attr("abs:href"));
        }
    }

    public Elements parseToTextFromFile(String fileName){
        Document doc = null;
        try {
            File input = new File(fileName);
            doc = Jsoup.parse(input, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return parseToTextFromDoc(doc);
    }

    public Elements parseToText(){
        try{
            return parseToTextFromDoc(Jsoup.connect(url).get());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private Elements parseToTextFromDoc(Document doc) {
        return doc.select("div");
    }

    public List<String> getLinksList() {
        return linksList;
    }
}
