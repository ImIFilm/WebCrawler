import Utilities.WebPages;
import Utilities.TextParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WebPagesTest {
    @Test
    public void parserTest() {

        String url = "http://onet.pl/";
        WebPages webPages1 = new WebPages(url);
        assertEquals(url, webPages1.getUrl());

        String fileName = "src/test/java/test.html";
        WebPages webPages2 = new WebPages(url);
        TextParser textParser2 = new TextParser(webPages2.parseToTextFromFile(fileName));
        List<String> linksInHtmlFile = webPages2.findLinksInHtmlFile(fileName);

        assertEquals(linksInHtmlFile,Arrays.asList("http://deep1.html/"));
        assertEquals(textParser2.getSentences(),
                Arrays.asList("Pies i ala ma kota. ", "Ladna dzis pogoda. ", "Ladnego ala ma kota. ", "Ala i ladny pies."));

    }
}
