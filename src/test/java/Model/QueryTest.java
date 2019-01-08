package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class QueryTest {

    @Test
    public void equalsTest(){
        Query query1 = new GivenQuery("http://onet.pl", "Polska", "", 0, true);
        Query query2 = new GivenQuery("http://onet.pl", "Polska", "", 0, true);
        Query query3 = new GivenQuery("http://nicfajnego.pl", "Polska", "", 0, true);

        Query query4 = new StoredQuery(query1);
        Query query5 = new StoredQuery(query1);
        Query query6 = new StoredQuery(query2);
        Query query7 = new StoredQuery(query3);

        assertEquals(true,query1.equals(query2));
        assertEquals(false,query1.equals(query3));
        assertEquals(true,query1.equals(query4));
        assertEquals(true,query1.equals(query4));
        assertEquals(true,query4.equals(query5));
        assertEquals(true,query4.equals(query6));
        assertEquals(false,query4.equals(query7));

    }
}
