package Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser{
    public String where;
    public String what;

    public Parser (String www, String word){
        where=www;
        what=word;
    }

    public String getTitle (){
        Document doc = null;
        try {
            doc = Jsoup.connect(where).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();
        return title;
    }

    public void showLinks(int howMany){
        Document doc = null;
        try {
            doc = Jsoup.connect(where).get();
//            File input = new File("test.html");
//            doc = Jsoup.parse(input, "UTF-8");
            Elements links = doc.select("a[href]");
            Element link;

            for(int j=0;j<howMany;j++){
                link=links.get(j);
                System.out.println("a= " + link.attr("abs:href").toString() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("nie ma tyle linków, ile kazałeś pokazać");
        }
        return;
    }

    public List<String> findWords(int howMany){
        Document doc = null;
        try {
            doc = Jsoup.connect(where).get();
            Elements links = doc.select("div");
            Element link;
            List<String> newest = new ArrayList<>();
            for(int j=0;j<links.size();j++){
                link=links.get(j);
                Formater sentence = new Formater(link.text());
                newest.addAll(sentence.showOnlySentenceWithWord(what));
            }
            return newest;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("\nnie ma tyle linków, ile kazałeś pokazać");
        }
        return new ArrayList<>();
    }

    public List<String> findWordsFromHtmlFile(String fileName, int howMany){
        Document doc = null;
        try {
            File input = new File(fileName);
            doc = Jsoup.parse(input, "UTF-8");
            Elements links = doc.select("div");
            Element link;

            for(int j=0;j<howMany;j++){
                link=links.get(j);
                Formater sentence = new Formater(link.text());
                List<String> newest = sentence.showOnlySentenceWithWord(what);
                return newest;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("\nnie ma tyle linków, ile kazałeś pokazać");
        }
        return new ArrayList<>();
    }
}
