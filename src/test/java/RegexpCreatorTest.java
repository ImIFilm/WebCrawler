

import static org.junit.Assert.assertEquals;

import Utilities.Parser;
import Utilities.RegexpCreator;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpCreatorTest {
    @Test
    public void creatorTest() throws Exception{

        RegexpCreator r1 =new RegexpCreator("ala/.llj", "kot");
        assertEquals(r1.getValid(),false);

        RegexpCreator r2 =new RegexpCreator("ala * kot", "kot");
        assertEquals(r2.getValid(),false);

        RegexpCreator r3 =new RegexpCreator("ala", "kot");
        assertEquals(r3.getValid(),true);
//
        RegexpCreator r4 =new RegexpCreator("", "");
        assertEquals(r4.getValid(),false);
//
        RegexpCreator r5 =new RegexpCreator("ala *", "kot");
        assertEquals(r5.getValid(),false);

        RegexpCreator r6 =new RegexpCreator("*ala", "kot");
        assertEquals(r6.getValid(),false);
//
        RegexpCreator r7 =new RegexpCreator("ala", "");
        assertEquals(r7.getValid(),true);
//
        RegexpCreator r8 =new RegexpCreator("ala", "*kok");
        assertEquals(r8.getValid(),false);
//
        RegexpCreator r9 =new RegexpCreator("ala,pies", "kot");
        assertEquals(r9.getValid(),true);

        RegexpCreator r10 =new RegexpCreator("ala**pies", "kot");
        assertEquals(r10.getValid(),true);

        RegexpCreator r11 =new RegexpCreator("ala**pięs", "kot");
        assertEquals(r11.getValid(),false);

    }

    @Test
    public void matchingTest(){
        Pattern pattern = Pattern.compile("^(?!(.*pies)).*(ala)");
        Matcher matcher = pattern.matcher("ala ma pies");
        assertEquals(matcher.find(),false);
    }

    @Test
    public void parserTest() {
        String fileName = "src/test/java/test.html";
        int deep = 1;
        Parser parser = new Parser("lololo", "^(?!(.*pies)).*(ala)");
        List<String> results = parser.findWordsFromHtmlFile(fileName, deep);
        assertEquals(results, Arrays.asList("Ladnego ala ma kota."));
    }

}
