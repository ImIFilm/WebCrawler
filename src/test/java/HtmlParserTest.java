import Utilities.HtmlParser;
import Utilities.TextParser;
import org.junit.Test;

import javax.xml.bind.Element;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HtmlParserTest {
    @Test
    public void parserTest() {
        //int deep = 1;
        //HtmlParser htmlParser1 = new HtmlParser("www", "^(?!(.*pies)).*(ala)");
        //List<String> results1 = htmlParser1.findWordsFromHtmlFile(fileName, deep);
        //assertEquals(results1, Arrays.asList("Ladnego ala ma kota. "));

        //HtmlParser htmlParser2 = new HtmlParser("www", "^(?!(.*kot)).*(ala ([a-zA-Z\\p{L}]+ ){2}pies)");
        //List<String> results2 = htmlParser2.findWordsFromHtmlFile(fileName, deep);
        //assertEquals(results2, Arrays.asList("Ala i ladny pies."));

        //HtmlParser htmlParser3 = new HtmlParser("www", "(ala)");
        //List<String> results3 = htmlParser3.findWordsFromHtmlFile(fileName, deep);
        //assertEquals(results3, Arrays.asList("Pies i ala ma kota. ","Ladnego ala ma kota. ","Ala i ladny pies."));



        String url = "http://onet.pl/";
        HtmlParser htmlParser1 = new HtmlParser(url);
        assertEquals(url,htmlParser1.getUrl());

        String fileName = "src/test/java/test.html";
        HtmlParser htmlParser2 = new HtmlParser(url);
        TextParser textParser2 = new TextParser(htmlParser2.parseToTextFromFile(fileName));
        List<String> linksInHtmlFile = htmlParser2.findLinksInHtmlFile(fileName);

        assertEquals(linksInHtmlFile,Arrays.asList("http://deep1.html/"));
        assertEquals(textParser2.getSentences(),
                Arrays.asList("Pies i ala ma kota. ", "Ladna dzis pogoda. ", "Ladnego ala ma kota. ", "Ala i ladny pies."));
    }
}
