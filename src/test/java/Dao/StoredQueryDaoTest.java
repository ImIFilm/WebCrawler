package Dao;

import Dao.StoredQueryDao;
import Model.GivenQuery;
import Model.Query;
import Model.QueryTest;
import Model.StoredQuery;
import Session.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoredQueryDaoTest {

    private final StoredQueryDao storedQueryDao = new StoredQueryDao();

    @Before
    public void before() {
        SessionService.openSession();
    }

    @After
    public void after() {
        SessionService.closeSession();
    }

    @Test
    public void existsTest(){
        Query query1 = new GivenQuery("http://onet.pl", "Polska", "", 0, true);
        Query query2 = new GivenQuery("http://nicfajnego.pl", "Polska", "", 0, true);
        StoredQuery storedQuery1 = storedQueryDao.create(query1);
        StoredQuery storedQuery2 = new StoredQuery(query2);
        assertEquals(true,storedQueryDao.exists(storedQuery1));
        assertEquals(false,storedQueryDao.exists(storedQuery2));
    }
}
