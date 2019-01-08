package Dao;

import Model.GivenQuery;
import Model.Query;
import Model.Result;
import Model.StoredQuery;
import Session.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultDaoTest {

    private final StoredQueryDao storedQueryDao = new StoredQueryDao();
    private final ResultDao resultDao = new ResultDao();

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
        String sentence1 = "ala";
        String sentence2 = "ala ma kota";
        Result result1 = resultDao.create(storedQuery1,sentence1);
        Result result2 = resultDao.create(storedQuery1,sentence2);

        assertEquals(true,resultDao.exists(result1));
        assertEquals(false,resultDao.exists(storedQuery2,sentence1));
        assertEquals(false,resultDao.exists(storedQuery2,sentence2));
        assertEquals(true,resultDao.exists(storedQuery1,sentence2));
    }

    @Test
    public void createTest(){
        Query query1 = new GivenQuery("http://onet.pl", "Polska", "", 0, true);
        StoredQuery storedQuery1 = storedQueryDao.create(query1);
        String sentence1 = "ala";
        Result result1 = resultDao.create(storedQuery1,sentence1);
        assertEquals(true,resultDao.exists(result1));
    }

    @Test
    public void equalTest(){
        Query query1 = new GivenQuery("http://onet.pl", "Polska", "", 0, true);
        StoredQuery storedQuery1 = storedQueryDao.create(query1);
        String sentence1 = "ala";
        Result result1 = resultDao.create(storedQuery1,sentence1);
        Result result2 = resultDao.create(storedQuery1, sentence1);
        List<Result> results = new ArrayList<>();
        results.add(result2);
        assertEquals(true, results.contains(result2));
    }
}
