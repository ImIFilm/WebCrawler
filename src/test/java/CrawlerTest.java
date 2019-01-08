import Controller.AppController;
import Session.SessionService;
import Utilities.Crawler;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class CrawlerTest {
    @Test
    public void getDomainTest() throws Exception{

        SessionService.openSession();
        AppController appController = Mockito.mock(AppController.class);
        Crawler crawler = new Crawler(appController);
        String url1 = "http://onet.pl";
        assertEquals("onet.pl/",crawler.getDomainTest(url1));

        String url2 = "http://onet.pl/";
        assertEquals("onet.pl/",crawler.getDomainTest(url2));

        String url3 = "http://onet.pl/siema.html";
        assertEquals("onet.pl/siema.html",crawler.getDomainTest(url3));


    }


}
