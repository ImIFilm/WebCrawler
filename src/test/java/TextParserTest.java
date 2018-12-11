import Utilities.HtmlParser;
import Utilities.TextParser;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.bind.Element;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TextParserTest {
    @Test
    public void parserTest() {

        String url = "http://onet.pl/";
        HtmlParser htmlParser1 = new HtmlParser(url);
        assertEquals(url,htmlParser1.getUrl());

        String fileName = "src/test/java/test.html";
        HtmlParser htmlParser2 = new HtmlParser(url);
        TextParser textParser2 = new TextParser(htmlParser2.parseToTextFromFile(fileName));

        List<String> sentences = textParser2.getSentences();
        assertEquals(sentences,
                Arrays.asList("Pies i ala ma kota. ", "Ladna dzis pogoda. ", "Ladnego ala ma kota. ", "Ala i ladny pies."));

    }
}
