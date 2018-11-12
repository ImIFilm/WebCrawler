import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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

    public void showWords(int howMany){
        Document doc = null;
        try {
            doc = Jsoup.connect(where).get();
            String lookFor = String.format("div:contains(%s)", what);
            Elements links = doc.select(lookFor);
            Element link;

            for(int j=0;j<howMany;j++){
                link=links.get(j);
                System.out.println("marsz= " + link.text() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("nie ma tyle linków, ile kazałeś pokazać");
        }
        return;
    }
}
