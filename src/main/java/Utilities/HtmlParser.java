package Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class HtmlParser {
    public String url;

    public HtmlParser(String www){
        url=www;
    }

    public String getTitle (){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();
        return title;
    }

    public void findLinksInHtml(int howMany){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            Element link;
            ArrayList<Element> linksList = new ArrayList<Element>();

            for(int j=0;j<howMany;j++){
                link=links.get(j);
                linksList.add(link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("the is no enought links");
        }
        return;
    }

    public Elements parseToText() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements divs = doc.select("div");
        return divs;
    }
}
