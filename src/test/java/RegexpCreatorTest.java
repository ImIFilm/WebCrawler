

import static org.junit.Assert.assertEquals;

import Utilities.RegexpCreator;
import Utilities.SearchPattern;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpCreatorTest {
    @Test
    public void creatorTest() throws Exception{

        SearchPattern searchPattern1 = new SearchPattern(RegexpCreator.getSearchExpr("siema * kocie"));
        assertEquals(true,searchPattern1.matches("siema ladny kocie"));
        assertEquals(false,searchPattern1.matches("siema kocie"));

        SearchPattern searchPattern2 = new SearchPattern(RegexpCreator.getSearchExpr("ala *** psa"));
        assertEquals(true,searchPattern2.matches("ala ma kota i psa"));
        assertEquals(false,searchPattern2.matches("ala ma psa"));

    }

    @Test
    public void createdRegexpTest(){
        Pattern pattern = Pattern.compile("^(?!(.*pies)).*(ala)");
        Matcher matcher = pattern.matcher("ala ma pies");
        assertEquals(matcher.find(),false);

        pattern = Pattern.compile("^(?!(.*)).*(kielce)");
        matcher = pattern.matcher("lol kielce lol");
        assertEquals(matcher.find(),false);

    }

}
