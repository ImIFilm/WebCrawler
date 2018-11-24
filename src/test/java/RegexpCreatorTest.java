

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RegexpCreatorTest {
    @Test
    public void creatorTest() throws Exception{

        RegexpCreator r1 =new RegexpCreator("ala/.llj", "kot");
        assertEquals(r1.getValid(),false);

        RegexpCreator r2 =new RegexpCreator("ala * kota", "kota");
        assertEquals(r2.getValid(),true);

        RegexpCreator r3 =new RegexpCreator("ala", "kot");
        assertEquals(r3.getValid(),true);

        RegexpCreator r4 =new RegexpCreator("", "");
        assertEquals(r4.getValid(),false);

        RegexpCreator r5 =new RegexpCreator("ala *", "kot");
        assertEquals(r5.getValid(),false);

        RegexpCreator r6 =new RegexpCreator("*ala", "kot");
        assertEquals(r6.getValid(),false);

        RegexpCreator r7 =new RegexpCreator("ala", "");
        assertEquals(r7.getValid(),true);

        RegexpCreator r8 =new RegexpCreator("ala", "*kok");
        assertEquals(r8.getValid(),false);

        RegexpCreator r9 =new RegexpCreator("ala,pies", "kot");
        assertEquals(r9.getValid(),true);

    }
}
