//<<<<<<< HEAD
//
//
//import static org.junit.Assert.assertEquals;
//
//import Controller.AppController;
//=======
import Controller.AppController;
import Session.SessionService;
//>>>>>>> ready
import Utilities.Crawler;
import org.junit.Test;
import org.mockito.Mockito;

//<<<<<<< HEAD
//=======
import static org.junit.Assert.assertEquals;

//>>>>>>> ready

public class CrawlerTest {
    @Test
    public void getDomainTest() throws Exception{

//<<<<<<< HEAD
//=======
        SessionService.openSession();
//>>>>>>> ready
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
