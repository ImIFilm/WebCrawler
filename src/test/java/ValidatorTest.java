import Utilities.Validator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {
    @Test
    public void isContinuationTest() {
        Validator validator1 = new Validator("https://onet.pl",false);

        assertEquals(validator1.validateSublink("http://onet.pl"),true);
        assertEquals(validator1.validateSublink("http://onet.pl/cos.html"),true);
        assertEquals(validator1.validateSublink("http://sub.onet.pl"),false);
        assertEquals(validator1.validateSublink("http://onet.pl/"),true);
    }

    @Test
    public void isSubdomainTest(){
        Validator validator1 = new Validator("https://onet.pl",true);

        assertEquals(validator1.validateSublink("http://sub.onet.pl"),true);
        assertEquals(validator1.validateSublink("http://sub.onet.pl/cos.html"),true);
        assertEquals(validator1.validateSublink("http://onet.pl/cos.html"),true);
        assertEquals(validator1.validateSublink("http://onet.pl"),true);
        assertEquals(validator1.validateSublink("http://onet.pl"),true);

        Validator validator2 = new Validator("https://super.onet.pl",true);
        assertEquals(validator2.validateSublink("http://sub.onet.pl"),false);
        assertEquals(validator2.validateSublink("http://sub.super.onet.pl/cos.html"),true);
        assertEquals(validator2.validateSublink("http://super.onet.pl/cos.html"),true);
        assertEquals(validator2.validateSublink("http://onet.pl"),false);
    }
}

