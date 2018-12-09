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
