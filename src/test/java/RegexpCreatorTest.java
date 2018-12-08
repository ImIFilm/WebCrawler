

import static org.junit.Assert.assertEquals;

import Utilities.SearchPattern;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpCreatorTest {
    @Test
    public void creatorTest() throws Exception{

        SearchPattern searchPattern = new SearchPattern("");
        assertEquals(false,searchPattern.matches("siema"));
       /* RegexpCreator r1 =new RegexpCreator("ala/.llj", "kot");
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
        assertEquals(r11.getValid(),true);

        RegexpCreator r12 =new RegexpCreator("kielce", "");
        assertEquals(r12.getValid(),true); */

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
