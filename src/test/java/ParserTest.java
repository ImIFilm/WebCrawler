import Utilities.Parser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void parserTest() {
        String fileName = "src/test/java/test.html";
        int deep = 1;
        Parser parser1 = new Parser("www", "^(?!(.*pies)).*(ala)");
        List<String> results1 = parser1.findWordsFromHtmlFile(fileName, deep);
        assertEquals(results1, Arrays.asList("Ladnego ala ma kota. "));

        Parser parser2 = new Parser("www", "^(?!(.*kot)).*(ala ([a-zA-Z\\p{L}]+ ){2}pies)");
        List<String> results2 = parser2.findWordsFromHtmlFile(fileName, deep);
        assertEquals(results2, Arrays.asList("Ala i ladny pies."));

        Parser parser3 = new Parser("www", "(ala)");
        List<String> results3 = parser3.findWordsFromHtmlFile(fileName, deep);
        assertEquals(results3, Arrays.asList("Pies i ala ma kota. ","Ladnego ala ma kota. ","Ala i ladny pies."));
    }
}
